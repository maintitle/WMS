package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusRepository;
import com.tz.warehouse.bus.service.BusRepositoryService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by TangZhen on 2022/11/17
 */
@RestController
@RequestMapping("/ware/repository")
@Api("商品库存")
public class BusRepositoryController {
    @Autowired
    private BusRepositoryService repositoryService;
    @ApiOperation("库存列表")
    @GetMapping("list")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = repositoryService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("添加库存")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) BusRepository busRepository){
        //TODO
        //repositoryService.saveAndCheck(busRepository);
        return R.ok();
    }
    @ApiOperation("更新库存")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusRepository busRepository){
        //TODO
        //repositoryService.updateAndCheck(busRepository);
        return R.ok();
    }

    @ApiOperation("删除库存")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids){
        repositoryService.deleteAndCheck(ids);
        return R.ok();
    }
}
