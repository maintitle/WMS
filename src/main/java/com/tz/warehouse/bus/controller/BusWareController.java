package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusWare;
import com.tz.warehouse.bus.service.BusWareService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TangZhen on 2022/11/17
 */
@RestController
@RequestMapping("/ware/site")
@Api("仓库站点")
public class BusWareController {
    @Autowired
    private BusWareService busWareService;

    @ApiOperation("根据条件获取站点列表")
    @GetMapping("/list")
    public R getList(@RequestParam Map<String, Object> params) {
        PageUtils page = busWareService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("获取全部站点")
    @GetMapping("/listAll")
    public R getListAll() {
        return R.ok().put("data", busWareService.list());
    }

    @ApiOperation("修改站点")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusWare busWare) {
        busWareService.updateById(busWare);
        return R.ok();
    }

    @ApiOperation("保存站点")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) BusWare busWare) {
        busWareService.save(busWare);
        return R.ok();
    }

    @ApiOperation("删除站点")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids) {
        busWareService.removeBatchByIds(ids);
        return R.ok();
    }

    @ApiOperation("获取站点名和id")
    @GetMapping("/listNameAndId")
    public R getListNameAndId() {
        List<HashMap<String, Object>> listNameAndId = busWareService.getListNameAndId();
        return R.ok().put("data", listNameAndId);
    }

}
