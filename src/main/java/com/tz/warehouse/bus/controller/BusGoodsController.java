package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusGoods;
import com.tz.warehouse.bus.service.BusGoodsService;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/11/13
 */
@RestController
@RequestMapping("/base/goods")
@Api("商品接口")
public class BusGoodsController {
    @Autowired
    private BusGoodsService busGoodsService;

    /**
     * 获商品获取列表
     * @param params
     * @return
     */
    @ApiOperation("获商品获取列表")
    @GetMapping("/list")
    public R getList(@RequestParam(required = false) Map<String,Object> params){
        PageUtils page = busGoodsService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
     * 删除(批量)商品信息
     * @param gids
     * @return
     */
    @ApiOperation("删除(批量)商品信息")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> gids){
        int count = busGoodsService.getBaseMapper().deleteBatchIds(gids);
        if(count>0){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 保存商品信息
     * @param busGoods
     * @return
     */
    @ApiOperation("保存商品信息")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) BusGoods busGoods){
        busGoodsService.saveAndCheckProvider(busGoods);
        return R.ok();
    }
    /**
     * 更新供应商信息
     * @param busGoods
     * @return
     */
    @ApiOperation("更新商品信息")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusGoods busGoods){
        busGoodsService.updateAndCheckProvider(busGoods);
        return R.ok();
    }

    /**
     * 批量更改供应商状态
     * @param gids
     * @param status
     * @return
     */
    @ApiOperation("批量更改商品状态")
    @PostMapping("/update/status")
    public R updateStatus(@RequestBody List<Long> gids,@RequestParam @Validated @FlagValidator(value = {0,1}) Integer status){
        List<BusGoods> collect = gids.stream().map(gid -> {
            BusGoods goods = new BusGoods();
            goods.setId(gid);
            goods.setAvailable(status);
            return goods;
        }).collect(Collectors.toList());
        busGoodsService.updateBatchById(collect);
        return R.ok();
    }
}
