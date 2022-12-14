package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.entity.SysRolePermission;
import com.tz.warehouse.sys.mapper.SysRolePermissionMapper;
import com.tz.warehouse.sys.service.SysPermissionService;
import com.tz.warehouse.sys.service.SysRolePermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【sys_role_permission】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission>
        implements SysRolePermissionService {

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public List<Long> getRelationPermission(List<Long> rid) {
        List<SysRolePermission> list = list(new LambdaQueryWrapper<SysRolePermission>().in(SysRolePermission::getRid, rid));
        HashSet<String> set = new HashSet<>();
        //把字符串转换成List集合，并且去重
        for (SysRolePermission a : list) {
            if (StringUtils.isNotEmpty(a.getPid())) {
                set.addAll(Arrays.asList(a.getPid().split(";")));
            }
        }
        return set.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public void setRelationPermission(Long id, List<Long> pids) {
        LambdaUpdateWrapper<SysRolePermission> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysRolePermission::getRid, id);
        //权限以";"分隔
        updateWrapper.set(SysRolePermission::getPid, StringUtils.join(pids, ";"));
        update(updateWrapper);
    }
}




