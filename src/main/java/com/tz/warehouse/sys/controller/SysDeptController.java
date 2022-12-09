package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.FlagValidator;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import com.tz.warehouse.sys.entity.SysDept;
import com.tz.warehouse.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/12/4
 */
@RestController
@RequestMapping("/system/dept")
@Api("部门接口")
public class SysDeptController {
    @Autowired
    private SysDeptService deptService;

    @GetMapping("listAll")
    @ApiOperation("获取部门全部列表")
    public R getListAll() {
        return R.ok().put("data", deptService.list());
    }

    @GetMapping("list")
    @ApiOperation("根据条件分页获取部门列表")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = deptService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("修改部门")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) SysDept dept) {
        deptService.updateById(dept);
        return R.ok();
    }

    @ApiOperation("保存部门")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) SysDept dept) {
        deptService.save(dept);
        return R.ok();
    }

    @ApiOperation("删除部门")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids) {
        deptService.removeBatchByIds(ids);
        return R.ok();
    }

    @ApiOperation("批量更改部门状态")
    @PostMapping("/update/status")
    public R updateStatus(@RequestBody List<Long> ids,@RequestParam @Validated @FlagValidator(value = {0,1}) Integer status){
        List<SysDept> collect = ids.stream().map(id -> {
            SysDept dept = new SysDept();
            dept.setId(id);
            dept.setAvailable(status);
            return dept;
        }).collect(Collectors.toList());
        deptService.updateBatchById(collect);
        return R.ok();
    }
}
