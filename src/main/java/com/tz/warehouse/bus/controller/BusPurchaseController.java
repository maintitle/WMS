package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusPurchase;
import com.tz.warehouse.bus.service.BusPurchaseService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.bus.vo.PurchaseDoneVo;
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
@RequestMapping("/ware/purchase")
@Api("采购单接口")
public class BusPurchaseController {
    @Autowired
    private BusPurchaseService purchaseService;

    @ApiOperation("筛选状态为0新建，1已分配")
    @GetMapping("/unreceive/list")
    public R unreceiveList(){
        List<BusPurchase> list =purchaseService.unreceiveList();
        return R.ok().put("data", list);
    }
    @ApiOperation("合并需求")
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVo mergeVo){
        purchaseService.merge(mergeVo);
        return R.ok();
    }

    @ApiOperation("采购单列表")
    @GetMapping("list")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = purchaseService.queryPage(params);
        return R.ok().put("data", page);
    }

    @ApiOperation("添加采购单")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) BusPurchase busPurchase){
        purchaseService.saveAndCheck(busPurchase);
        return R.ok();
    }
    @ApiOperation("更新采购单")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusPurchase busPurchase){
        purchaseService.updateAndCheck(busPurchase);
        return R.ok();
    }

    @ApiOperation("删除采购单")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids){
        purchaseService.deleteAndCheck(ids);
        return R.ok();
    }
    /**
     * 领取采购单
     * @param ids
     * @return
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids){
        purchaseService.received(ids);
        return R.ok();
    }

    /**
     * 完成采购单
     * @param
     * @return
     */
    @PostMapping("/done")
    public R finish(@RequestBody PurchaseDoneVo doneVo){
        purchaseService.done(doneVo);
        return R.ok();
    }
}
