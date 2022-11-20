package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusPurchase;
import com.tz.warehouse.bus.entity.BusRequirement;
import com.tz.warehouse.bus.mapper.BusPurchaseMapper;
import com.tz.warehouse.bus.service.BusPurchaseService;
import com.tz.warehouse.bus.service.BusRepositoryService;
import com.tz.warehouse.bus.service.BusRequirementService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.bus.vo.PurchaseDoneVo;
import com.tz.warehouse.bus.vo.RepositoryDoneVo;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.common.utils.RRException;
import com.tz.warehouse.sys.common.utils.WareConstant;
import com.tz.warehouse.sys.entity.SysUser;
import com.tz.warehouse.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【bus_purchase(采购单)】的数据库操作Service实现
 * @createDate 2022-11-16 14:18:21
 */
@Service
public class BusPurchaseServiceImpl extends ServiceImpl<BusPurchaseMapper, BusPurchase>
        implements BusPurchaseService {

    @Autowired
    private BusRequirementService requirementService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private BusRepositoryService repositoryService;

    @Override
    public List<BusPurchase> unreceiveList() {
        LambdaQueryWrapper<BusPurchase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusPurchase::getStatus, 0).or().eq(BusPurchase::getStatus, 1);
        return list(queryWrapper);
    }

    @Override
    @Transactional
    public void merge(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null) {
            BusPurchase purchase = new BusPurchase();
            purchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            this.save(purchase);
            purchaseId = purchase.getId();
        }
        //确认采购单状态是0,1才可以合并
        BusPurchase purchase = this.getById(purchaseId);
        if (purchase.getStatus() != WareConstant.PurchaseStatusEnum.CREATED.getCode() && purchase.getStatus() != WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
            throw new RRException("只有" + WareConstant.PurchaseStatusEnum.CREATED.getMsg() + "和" + WareConstant.PurchaseStatusEnum.ASSIGNED.getMsg() + "才能合并");
        }

        List<Long> items = mergeVo.getItems();
        List<BusRequirement> requirements = requirementService.listByIds(items);
        for (BusRequirement requirement : requirements) {
            if (requirement.getStatus() != WareConstant.PurchaseDetailStatusEnum.CREATED.getCode() && requirement.getStatus() != WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode()) {
                throw new RRException("只有" + WareConstant.PurchaseDetailStatusEnum.CREATED.getMsg() + "和" + WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getMsg() + "才能合并");
            }
        }
        Long finalPurchaseId = purchaseId;
        List<BusRequirement> collect = items.stream().map(i -> {
            BusRequirement requirement = new BusRequirement();
            requirement.setId(i);
            requirement.setPurchaseId(finalPurchaseId);
            requirement.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return requirement;
        }).collect(Collectors.toList());
        requirementService.updateBatchById(collect);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusPurchase> queryWrapper = new LambdaQueryWrapper<>();
        String status = (String) params.get("status");
        if (StringUtils.isNotEmpty(status)) {
            queryWrapper.eq(BusPurchase::getStatus, status);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.and(item -> {
                item.eq(BusPurchase::getId, key).or().eq(BusPurchase::getAssigneeId, key).or().likeRight(BusPurchase::getAssigneeName, key);
            });
        }

        IPage<BusPurchase> page = page(new Query<BusPurchase>().getPage(params), queryWrapper);
        return new PageUtils(page);

    }

    @Override
    public void saveAndCheck(BusPurchase busPurchase) {
        //禁止外部改变时间
        busPurchase.setUpdateTime(null);
        busPurchase.setUpdateTime(null);
        if (busPurchase.getAssigneeId() != null) {
            busPurchase.setStatus(WareConstant.PurchaseStatusEnum.ASSIGNED.getCode());
            SysUser user = userService.getById(busPurchase.getAssigneeId());
            //判断用户是否存在
            if (ObjectUtils.isNotEmpty(user)) {
                busPurchase.setAssigneeName(user.getName());
                busPurchase.setPhone(user.getPhone());
            } else {
                throw new RRException("没有此用户");
            }
        }else {
            busPurchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
        }

        save(busPurchase);
    }

    @Override
    public void updateAndCheck(BusPurchase busPurchase) {
        LambdaUpdateWrapper<BusPurchase> updateWrapper = new LambdaUpdateWrapper<>();
        if (busPurchase.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                busPurchase.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
            if (busPurchase.getAssigneeId() != null) {
                busPurchase.setStatus(WareConstant.PurchaseStatusEnum.ASSIGNED.getCode());
            } else {
                busPurchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            }
        } else {
            //禁止在非创建和分配模式中修改状态
            throw new RRException("不能在非[" + WareConstant.PurchaseStatusEnum.CREATED.getMsg()
                    + "]和[" + WareConstant.PurchaseStatusEnum.ASSIGNED.getMsg() + "]中修改");
        }
        //禁止外部改变时间
        busPurchase.setUpdateTime(null);
        busPurchase.setUpdateTime(null);
        if (busPurchase.getAssigneeId() == null) {
            updateWrapper.set(BusPurchase::getPhone, null);
            updateWrapper.set(BusPurchase::getAssigneeId, null);
            updateWrapper.set(BusPurchase::getAssigneeName, null);
            updateWrapper.eq(BusPurchase::getId, busPurchase.getId());
        } else {
            SysUser user = userService.getById(busPurchase.getAssigneeId());
            //判断用户是否存在
            if (ObjectUtils.isNotEmpty(user)) {
                updateWrapper.eq(BusPurchase::getId, busPurchase.getId());
                busPurchase.setAssigneeName(user.getName());
                busPurchase.setPhone(user.getPhone());
            } else {
                throw new RRException("没有此用户");
            }
        }
        update(busPurchase, updateWrapper);
    }

    @Override
    @Transactional
    public void deleteAndCheck(List<Long> ids) {
        List<BusPurchase> purchases = listByIds(ids);
        //判断是否能被删除
        for (BusPurchase purchase : purchases) {
            if (purchase.getStatus() == WareConstant.PurchaseStatusEnum.RECEIVE.getCode()
            ) {
                throw new RRException("此" + WareConstant.PurchaseStatusEnum.RECEIVE.getMsg() + "不能被删除");
            }
        }

        //更新需求表
        LambdaQueryWrapper<BusRequirement> queryWrapper = new LambdaQueryWrapper<>();
        List<BusRequirement> requirementList = requirementService.list(queryWrapper.in(BusRequirement::getPurchaseId, ids));
        List<Long> collect = requirementList.stream().map(BusRequirement::getId).collect(Collectors.toList());
        LambdaUpdateWrapper<BusRequirement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(BusRequirement::getId, collect);
        updateWrapper.set(BusRequirement::getPurchaseId, null);
        requirementService.update(null, updateWrapper);
        //删除采购表数据
        removeBatchByIds(ids);
    }

    @Override
    @Transactional
    public void received(List<Long> ids) {
        //1、确认当前采购单是新建或者已分配状态
        List<BusPurchase> list = listByIds(ids);
        List<BusPurchase> collect = list.stream().filter(item -> item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                        item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode())
                .peek(item -> item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode()))
                .collect(Collectors.toList());
        //2、改变采购单的状态
        updateBatchById(collect);
        //3、改变采购项的状态
        List<Long> repositoryId = new ArrayList<>();


        collect.forEach(item -> repositoryId.add(item.getId()));
        List<BusRequirement> requirements = requirementService.listRequirementByPurchaseId(repositoryId);
        //统计金额
        for (Long id : repositoryId) {
            List<BusRequirement> collect1 = requirements.stream().filter(item -> Objects.equals(item.getPurchaseId(), id)).collect(Collectors.toList());
            BigDecimal amount = new BigDecimal("0.00");
            for (BusRequirement busRequirement : collect1) {
                amount = amount.add(busRequirement.getPrice());
            }
            BusPurchase purchase = new BusPurchase();
            purchase.setAmount(amount);
            purchase.setId(id);
            updateById(purchase);
        }
        List<BusRequirement> requirementList = requirements.stream().peek(item -> item.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode())).collect(Collectors.toList());

        requirementService.updateBatchById(requirementList);
    }

    @Override
    @Transactional
    public void done(PurchaseDoneVo doneVo) {
        Long id = doneVo.getId();

        //验证数据完整性
        BusPurchase purchase1 = getById(id);
        if (ObjectUtils.isEmpty(purchase1)) {
            throw new RRException("没有此数据");
        }
        if (purchase1.getStatus() != WareConstant.PurchaseStatusEnum.RECEIVE.getCode()) {
            throw new RRException("状态错误");
        }
        List<BusRequirement> list = requirementService.list(new LambdaQueryWrapper<BusRequirement>().in(BusRequirement::getPurchaseId, id));
        if (list.size() != doneVo.getItems().size()) {
            throw new RRException("数据错误");
        }
        for (BusRequirement requirement : list) {
            if (!Objects.equals(requirement.getPurchaseId(), doneVo.getId())) {
                throw new RRException("数据错误");
            }
        }
        //2、改变采购项的状态
        boolean flag = true;
        List<RepositoryDoneVo> items = doneVo.getItems();

        ArrayList<BusRequirement> details = new ArrayList<>();
        for (RepositoryDoneVo item : items) {
            BusRequirement purchaseDetail = new BusRequirement();
            if (item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
                flag = false;
                purchaseDetail.setStatus(item.getStatus());
            } else {
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                //3、将成功采购的进行入库
                BusRequirement entity = requirementService.getById(item.getItemId());
                repositoryService.addStock(entity.getGoodsId(), entity.getWareId(), entity.getNum());
            }

            purchaseDetail.setId(item.getItemId());
            details.add(purchaseDetail);
        }
        requirementService.updateBatchById(details);

        //1、改变采购单状态
        BusPurchase purchase = new BusPurchase();
        purchase.setId(id);
        purchase.setStatus(flag ? WareConstant.PurchaseStatusEnum.FINISH.getCode() : WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        this.updateById(purchase);
    }
}




