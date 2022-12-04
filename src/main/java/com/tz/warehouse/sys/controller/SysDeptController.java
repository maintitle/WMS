package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
}
