package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusProvider;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_provider】的数据库操作Service
* @createDate 2022-11-05 16:31:26
*/
public interface BusProviderService extends IService<BusProvider> {

    /**
     * 分页 根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取供应商名和id
     *
     * @return
     */
    List<HashMap<String, Object>> getListNameAndId();
}
