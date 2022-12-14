package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import com.tz.warehouse.sys.entity.SysRole;
import com.tz.warehouse.sys.service.SysRolePermissionService;
import com.tz.warehouse.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by TangZhen on 2022/12/5
 */
@Api("角色接口")
@RestController
@RequestMapping("/system/role")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRolePermissionService rolePermissionService;
    @GetMapping("/list")
    @ApiOperation("根据条件分页获取角色列表")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = roleService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) SysRole role) {
        roleService.updateById(role);
        return R.ok();
    }

    @ApiOperation("保存角色")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) SysRole role) {
        roleService.save(role);
        return R.ok();
    }

    @ApiOperation("删除角色")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids) {
        roleService.removeBatchByIds(ids);
        return R.ok();
    }

    @ApiOperation("获取权限ID")
    @GetMapping("/getRoleRelationPermission")
    public R getRoleRelationPermission(@RequestParam("id") Long id){
        List<Long> list = rolePermissionService.getRelationPermission(Arrays.asList(id));
        return R.ok().put("data", list);
    }
    @ApiOperation("更改权限")
    @PostMapping("/setRoleRelationPermission")
    public R setRoleRelationPermission(@RequestParam("id") Long id,@RequestBody List<Long> pids){
        rolePermissionService.setRelationPermission(id,pids);
        return R.ok();
    }
}
