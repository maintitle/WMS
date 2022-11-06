package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.entity.SysUserRole;
import com.tz.warehouse.sys.mapper.SysUserRoleMapper;
import com.tz.warehouse.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【sys_user_role】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Override
    public List<SysUserRole> getRelationRole(Long userId) {
        return list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUid, userId));
    }
}




