package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据条件返回
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存检查数据安全
     * @param busPurchase
     */
    void saveAndCheck(BusPurchase busPurchase);

    /**
     * 更新检查数据安全
     * @param busPurchase
     */
    void updateAndCheck(BusPurchase busPurchase);

    /**
     * 删除并更新其他表
     * @param ids
     */
    void deleteAndCheck(List<Long> ids);
}
