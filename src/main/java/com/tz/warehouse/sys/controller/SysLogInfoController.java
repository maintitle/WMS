package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.service.SysLoginfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by TangZhen on 2022/12/18
 */
@Api("日志接口")
@RestController
@RequestMapping("/other/logged")
public class SysLogInfoController {
    @Autowired
    private SysLoginfoService loginfoService;

    @GetMapping("/list")
    @ApiOperation("根据条件分页获取登入日志列表")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = loginfoService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("日志角色")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids) {
        loginfoService.removeBatchByIds(ids);
        return R.ok();
    }
}
