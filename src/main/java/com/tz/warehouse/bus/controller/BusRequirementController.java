package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusRequirement;
import com.tz.warehouse.bus.service.BusRequirementService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
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
@RequestMapping("/ware/requirement")
@Api("采购需求接口")
public class BusRequirementController {
    @Autowired
    private BusRequirementService requirementService;

    @ApiOperation("根据条件筛选")
    @GetMapping("/list")
    public R getList(@RequestParam Map<String, Object> params) {
        PageUtils page = requirementService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("插入采购需求")
    @PostMapping("/save")
    public R save(@RequestBody @Validated BusRequirement busRequirement) {
        requirementService.saveAndCheck(busRequirement);
        return R.ok();
    }

    @ApiOperation("更新采购需求")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusRequirement busRequirement) {
        requirementService.updateAndCheck(busRequirement);
        return R.ok();
    }
    @ApiOperation("删除采购需求")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids){
        requirementService.deleteAndCheck(ids);
        return R.ok();
    }

}
