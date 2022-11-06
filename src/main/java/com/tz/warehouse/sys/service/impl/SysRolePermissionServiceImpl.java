package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.entity.SysRolePermission;
import com.tz.warehouse.sys.service.SysRolePermissionService;
import com.tz.warehouse.sys.mapper.SysRolePermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【sys_role_permission】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission>
        implements SysRolePermissionService {

    @Override
    public List<SysRolePermission> getRelationPermission(Long rid) {
        return list(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRid, rid));
    }
}




