package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_goods】的数据库操作Service
* @createDate 2022-11-05 16:31:26
*/
public interface BusGoodsService extends IService<BusGoods> {

    /**
     * 分页，根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存并检查供应商是否正确
     * @param busGoods
     */
    void saveAndCheckProvider(BusGoods busGoods);

    /**
     * 更新并检查供应商是否正确
     * @param busGoods
     */
    void updateAndCheckProvider(BusGoods busGoods);
}
