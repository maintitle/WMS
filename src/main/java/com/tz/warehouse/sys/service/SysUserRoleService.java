package com.tz.warehouse.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.entity.SysUserRole;

import java.util.List;

/**
* @author lenovo
* @description 针对表【sys_user_role】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 获取角色与用户关系
     * @param userId 用户id
     * @return SysUserRole
     */
    List<SysUserRole> getRelationRole(Long userId);
}
