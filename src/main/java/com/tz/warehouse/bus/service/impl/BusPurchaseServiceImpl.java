package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusPurchase;
import com.tz.warehouse.bus.entity.BusRequirement;
import com.tz.warehouse.bus.mapper.BusPurchaseMapper;
import com.tz.warehouse.bus.service.BusPurchaseService;
import com.tz.warehouse.bus.service.BusRequirementService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.sys.common.utils.RRException;
import com.tz.warehouse.sys.common.utils.WareConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author lenovo
* @description 针对表【bus_purchase(采购单)】的数据库操作Service实现
* @createDate 2022-11-16 14:18:21
*/
@Service
public class BusPurchaseServiceImpl extends ServiceImpl<BusPurchaseMapper, BusPurchase>
    implements BusPurchaseService{

    @Autowired
    private BusRequirementService requirementService;
    @Override
    public List<BusPurchase> unreceiveList() {
        LambdaQueryWrapper<BusPurchase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusPurchase::getStatus, 0).or().eq(BusPurchase::getStatus, 1 );
        return list(queryWrapper);
    }

    @Override
    public void merge(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId==null){
            BusPurchase purchase = new BusPurchase();
            purchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            this.save(purchase);
            purchaseId = purchase.getId();
        }
        //确认采购单状态是0,1才可以合并
        BusPurchase purchase = this.getById(purchaseId);
        if(purchase.getStatus() !=WareConstant.PurchaseStatusEnum.CREATED.getCode() && purchase.getStatus() != WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()){
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
}




