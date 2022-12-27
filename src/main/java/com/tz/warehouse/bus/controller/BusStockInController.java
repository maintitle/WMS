package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.service.BusStockinService;
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
@RequestMapping("/ware/stockin")
@Api("入库接口")
public class BusStockInController {
    @Autowired
    private BusStockinService stockinService;
    @ApiOperation("根据条件获取")
    @GetMapping("/list")
    public R list(@RequestParam Map<String,Object> params){
         PageUtils page =stockinService.queryPage(params);
        return R.ok().put("data", page);
    }
}
