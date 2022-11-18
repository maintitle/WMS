package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusProvider;
import com.tz.warehouse.bus.service.BusProviderService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.FlagValidator;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
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
 * Created by TangZhen on 2022/11/13
 */
@RestController
@RequestMapping("/base/provide")
@Api("供应商接口")
public class BusProviderController {
    @Autowired
    private BusProviderService busProviderService;

    /**
     * 获供应商获取列表
     *
     * @param params
     * @return
     */
    @ApiOperation("获供应商获取列表")
    @GetMapping("/list")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = busProviderService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 删除(批量)供应商信息
     *
     * @param pids
     * @return
     */
    @ApiOperation("删除(批量)供应商信息")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> pids) {
        int count = busProviderService.getBaseMapper().deleteBatchIds(pids);
        if (count > 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 保存供应商信息
     *
     * @param busProvider
     * @return
     */
    @ApiOperation("保存供应商信息")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) BusProvider busProvider) {
        busProviderService.save(busProvider);
        return R.ok();
    }

    /**
     * 更新供应商信息
     *
     * @param busProvider
     * @return
     */
    @ApiOperation("更新供应商信息")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusProvider busProvider) {
        busProviderService.updateById(busProvider);
        return R.ok();
    }

    /**
     * 批量更改供应商状态
     *
     * @param pids
     * @param status
     * @return
     */
    @ApiOperation("批量更改供应商状态")
    @PostMapping("/update/status")
    public R updateStatus(@RequestBody List<Long> pids, @RequestParam @Validated @FlagValidator(value = {0, 1}) Integer status) {
        List<BusProvider> collect = pids.stream().map(pid -> {
            BusProvider provider = new BusProvider();
            provider.setId(pid);
            provider.setAvailable(status);
            return provider;
        }).collect(Collectors.toList());
        busProviderService.updateBatchById(collect);
        return R.ok();
    }

    /**
     * 获取供应商名和id
     *
     * @return
     */
    @ApiOperation("获取供应商名和id")
    @GetMapping("/listNameAndId")
    public R getListNameAndId() {
        List<HashMap<String, Object>> listNameAndId = busProviderService.getListNameAndId();
        return R.ok().put("data", listNameAndId);
    }
}
