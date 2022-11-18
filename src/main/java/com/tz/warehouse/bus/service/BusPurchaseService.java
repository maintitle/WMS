package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.bus.vo.MergeVo;

import java.util.List;

/**
* @author lenovo
* @description 针对表【bus_purchase(采购单)】的数据库操作Service
* @createDate 2022-11-16 14:18:21
*/
public interface BusPurchaseService extends IService<BusPurchase> {

    /**
     * 筛选状态为0新建，1已分配
     * @return
     */
    List<BusPurchase> unreceiveList();

    /**
     * 合并需求
     * @param mergeVo
     */
    void merge(MergeVo mergeVo);
}
