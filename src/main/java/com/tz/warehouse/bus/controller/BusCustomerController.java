package com.tz.warehouse.bus.controller;

import com.tz.warehouse.bus.entity.BusCustomer;
import com.tz.warehouse.bus.service.BusCustomerService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/11/12
 */
@RestController
@RequestMapping("/base/customer")
@Api("客户管理")
public class BusCustomerController {
    @Autowired
    private BusCustomerService busCustomerService;

    /**
     * 获客户取列表
     * @return
     */
    @ApiOperation("获客户取列表")
    @GetMapping("/list")
    public R getList(@RequestParam(required = false)Map<String,Object> params){
        PageUtils page = busCustomerService.queryPage(params);
        return R.ok().put("data",page);
    }

    /**
     * 获取客户详细信息
     * @param cid
     * @return
     */
    @ApiOperation("获取客户详细信息")
    @GetMapping("/info/{cid}")
    public R getInfo(@PathVariable("cid")Long cid){
        BusCustomer customer = busCustomerService.getById(cid);
        return R.ok().put("data", customer);
    }

    /**
     * 删除(批量)客户信息
     * @param cids
     * @return
     */
    @ApiOperation("删除(批量)客户信息")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> cids){
        boolean b = busCustomerService.removeBatchByIds(cids);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }

    }

    /**
     * 保存客户信息
     * @param busCustomer
     * @return
     */
    @ApiOperation("保存用户信息")
    @PostMapping("/save")
    public R save(@RequestBody @Validated(AddGroup.class) BusCustomer busCustomer){
        busCustomerService.save(busCustomer);
        return R.ok();
    }


    /**
     * 更改客户信息
     * @param busCustomer
     * @return
     */
    @ApiOperation("更改客户信息")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BusCustomer busCustomer){
        busCustomerService.updateById(busCustomer);
        return R.ok();
    }

    /**
     * 批量更改客户状态
     * @param cids
     * @param status
     * @return
     */
    @ApiOperation("批量更改客户状态")
    @PostMapping("/update/status")
    public R updateStatus(@RequestBody Long[] cids,@RequestParam @Validated @FlagValidator(value = {0,1}) Integer status){
        List<BusCustomer> collect = Arrays.stream(cids).map(cid -> {
            BusCustomer customer = new BusCustomer();
            customer.setId(cid);
            customer.setAvailable(status);
            return customer;
        }).collect(Collectors.toList());
        busCustomerService.updateBatchById(collect);
        return R.ok();
    }
}
