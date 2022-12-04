package com.tz.warehouse.sys.service;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【sys_dept】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysDeptService extends IService<SysDept> {

    /**
     * 根据条件分页获取部门列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
