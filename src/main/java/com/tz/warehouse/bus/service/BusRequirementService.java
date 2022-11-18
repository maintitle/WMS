package com.tz.warehouse.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.bus.entity.BusRequirement;
import com.tz.warehouse.sys.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_requirement(采购需求)】的数据库操作Service
* @createDate 2022-11-16 19:02:14
*/
public interface BusRequirementService extends IService<BusRequirement> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 插入并检查
     * @param busRequirement
     */
    void saveAndCheck(BusRequirement busRequirement);

    /**
     * 更新并检查
     * @param busRequirement
     */
    void updateAndCheck(BusRequirement busRequirement);

    /**
     * 删除并检查
     * @param ids
     */
    void deleteAndCheck(List<Long> ids);

}
