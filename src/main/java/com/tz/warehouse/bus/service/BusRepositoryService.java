package com.tz.warehouse.bus.service;

import com.tz.warehouse.bus.entity.BusRepository;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_repository(商品库存)】的数据库操作Service
* @createDate 2022-11-16 14:19:02
*/
public interface BusRepositoryService extends IService<BusRepository> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 删除库存
     * @param ids
     */
    void deleteAndCheck(List<Long> ids);
}
