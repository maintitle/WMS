package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusStockout;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_stockout】的数据库操作Service
* @createDate 2022-12-26 16:23:47
*/
public interface BusStockoutService extends IService<BusStockout> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
