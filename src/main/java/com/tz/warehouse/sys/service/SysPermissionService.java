package com.tz.warehouse.sys.service;

import com.tz.warehouse.sys.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lenovo
* @description 针对表【sys_permission】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 查询权限
     * @param collect id列表
     * @return SysPermission
     */
    List<SysPermission> getPermission(List<Long> collect);
}
