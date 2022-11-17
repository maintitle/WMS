package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusWare;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_ware(仓库)】的数据库操作Service
* @createDate 2022-11-16 14:19:19
*/
public interface BusWareService extends IService<BusWare> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
