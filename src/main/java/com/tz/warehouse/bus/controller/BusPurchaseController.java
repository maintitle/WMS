package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusPurchase;
import com.tz.warehouse.bus.service.BusPurchaseService;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.sys.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
