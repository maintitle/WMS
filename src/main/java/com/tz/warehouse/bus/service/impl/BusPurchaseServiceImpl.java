package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusPurchase;
import com.tz.warehouse.bus.entity.BusRequirement;
import com.tz.warehouse.bus.mapper.BusPurchaseMapper;
import com.tz.warehouse.bus.service.BusPurchaseService;
import com.tz.warehouse.bus.service.BusRequirementService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.common.utils.RRException;
import com.tz.warehouse.sys.common.utils.WareConstant;
import com.tz.warehouse.sys.entity.SysUser;
import com.tz.warehouse.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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
        } else {
            SysUser user = userService.getById(busPurchase.getAssigneeId());

            //判断用户是否存在
            if (ObjectUtils.isNotEmpty(user)) {
                busPurchase.setAssigneeName(user.getName());
            } else {
                throw new RRException("没有此用户");
            }
        }
        save(busPurchase);
    }

    @Override
    public void updateAndCheck(BusPurchase busPurchase) {

        if (busPurchase.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                busPurchase.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
            if (busPurchase.getAssigneeId() != null) {
                busPurchase.setStatus(WareConstant.PurchaseStatusEnum.ASSIGNED.getCode());
            } else {
                busPurchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            }
        } else {
            //禁止在非创建和分配模式中修改状态
            throw new RRException("不能在非" + WareConstant.PurchaseStatusEnum.CREATED.getMsg()
                    + "和" + WareConstant.PurchaseStatusEnum.ASSIGNED + "修改");
        }
        //禁止外部改变时间
        busPurchase.setUpdateTime(null);
        busPurchase.setUpdateTime(null);
        if (busPurchase.getAssigneeId() == null) {
            busPurchase.setPhone(null);
            busPurchase.setAssigneeId(null);
            busPurchase.setAssigneeName(null);
        } else {
            SysUser user = userService.getById(busPurchase.getAssigneeId());
            //判断用户是否存在
            if (ObjectUtils.isNotEmpty(user)) {
                busPurchase.setAssigneeName(user.getName());
                busPurchase.setPhone(user.getPhone());
            } else {
                throw new RRException("没有此用户");
            }
        }
        updateById(busPurchase);
    }

    @Override
    @Transactional
    public void deleteAndCheck(List<Long> ids) {
        List<BusPurchase> purchases = listByIds(ids);
        //判断是否能被删除
        for (BusPurchase purchase : purchases) {
            if (!(purchase.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                    purchase.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode())
            ) {
                throw new RRException("只有" + WareConstant.PurchaseStatusEnum.CREATED.getMsg() + "和" + WareConstant.PurchaseStatusEnum.ASSIGNED.getMsg() + "才能删除");
            }
        }

        //更新需求表
        LambdaQueryWrapper<BusRequirement> queryWrapper = new LambdaQueryWrapper<>();
        List<BusRequirement> requirementList = requirementService.list(queryWrapper.in(BusRequirement::getPurchaseId, ids));
        List<BusRequirement> collect = requirementList.stream().peek(item -> item.setPurchaseId(null)).collect(Collectors.toList());
        requirementService.updateBatchById(collect);
        //删除采购表数据
        removeBatchByIds(ids);
    }
}




