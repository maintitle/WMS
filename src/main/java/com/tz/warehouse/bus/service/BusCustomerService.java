package com.tz.warehouse.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.bus.entity.BusCustomer;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_customer】的数据库操作Service
* @createDate 2022-11-05 16:31:26
*/
public interface BusCustomerService extends IService<BusCustomer> {

    /**
     * 获顾客取列表
     * @return
     */
    PageUtils queryPage(Map<String,Object> params);

}
