package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/12/5
 */
@Api("权限接口")
@RestController
@RequestMapping("/system/permission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService permissionService;

    @GetMapping("/list")
    @ApiOperation("根据条件分页获取权限列表")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = permissionService.queryPagePermission(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("修改权限")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) SysPermission dept) {
        permissionService.updateById(dept);
        return R.ok();
    }

    @ApiOperation("保存权限")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) SysPermission dept) {
        permissionService.save(dept);
        return R.ok();
    }

    @ApiOperation("删除权限")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids) {
        permissionService.removeBatchByIds(ids);
        return R.ok();
    }

    @GetMapping("/MenusListAll")
    @ApiOperation("获取部门全部列表")
    public R getListAll() {
        List<SysPermission> menus = permissionService.getMenusAll();
        List<HashMap<String, Object>> collect = menus.stream().map(obj -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", obj.getId());
            map.put("pid", obj.getPid());
            map.put("name", obj.getTitle());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", collect);
    }
}
