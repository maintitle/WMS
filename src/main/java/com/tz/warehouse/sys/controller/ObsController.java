package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.component.ObsUtil;
import com.tz.warehouse.sys.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TangZhen on 2022/11/11
 */
@RestController
@RequestMapping("/huawei/obs")
@Api("华为云Obs")
public class ObsController {
    @Autowired
    private ObsUtil obsUtil;
    /**
     * 获取华为云obs policy
     * @param id
     * @return
     */
    @ApiOperation("获取华为云obs policy")
    @GetMapping("/policy/{id}")
    public R getPolicy(@PathVariable(value = "id",required = false) Long id){
        return R.ok().put("data",obsUtil.policy(id));
    }
}
