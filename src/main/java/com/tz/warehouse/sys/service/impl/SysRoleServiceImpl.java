package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.entity.SysRole;
import com.tz.warehouse.sys.service.SysRoleService;
import com.tz.warehouse.sys.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lenovo
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2022-11-05 16:31:58
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Override
    public List<SysRole> getRoleList(List<Long> roleList) {
        return list(new LambdaQueryWrapper<SysRole>().in(SysRole::getId,roleList));
    }
}




