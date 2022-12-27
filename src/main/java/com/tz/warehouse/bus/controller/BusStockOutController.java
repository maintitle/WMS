package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.service.BusStockoutService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by TangZhen on 2022/12/26
 */
@RestController
@RequestMapping("/ware/stockout")
@Api("出库接口")
public class BusStockOutController {
    @Autowired
    private BusStockoutService stockoutService;
    @ApiOperation("根据条件获取")
    @GetMapping("/list")
    public R list(@RequestParam Map<String,Object> params){
        PageUtils page =stockoutService.queryPage(params);
        return R.ok().put("data", page);
    }
}
