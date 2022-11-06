package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.service.SysPermissionService;
import com.tz.warehouse.sys.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【sys_permission】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
        implements SysPermissionService {

    @Override
    public List<SysPermission> getPermission(List<Long> collect) {
        return list(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getId, collect));
    }

    @Override
    public List<SysPermission> getMenus(List<Long> collect) {
        return list(new LambdaQueryWrapper<SysPermission>()
                .and(item -> {
                    item.in(SysPermission::getId, collect).eq(SysPermission::getType, "menu");
                }));
    }
}




