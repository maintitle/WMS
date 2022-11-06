package com.tz.warehouse.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.entity.SysRolePermission;

import java.util.List;

/**
* @author lenovo
* @description 针对表【sys_role_permission】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 获取权限与角色对应关系
     * @param rid 角色id
     * @return SysRolePermission
     */
    List<SysRolePermission> getRelationPermission(Long rid);
}
