package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.entity.SysNotice;
import com.tz.warehouse.sys.service.SysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by TangZhen on 2022/11/9
 */
@RestController
@RequestMapping("/other/notice")
@Api("公告版接口")
public class SysNoticeController {

    @Autowired
    private SysNoticeService noticeService;

    /**
     * 获取公告牌信息
     */
    @ApiOperation("获取公告牌信息")
    @GetMapping("/info")
    public R getInfo() {
        List<SysNotice> noticeList = noticeService.getInfo();
        //return R.error("asdsa");
        return R.ok().put("data", noticeList);
    }

    @GetMapping("/list")
    @ApiOperation("根据条件分页获取notice列表")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = noticeService.queryPage(params);
        return R.ok().put("data", page);
    }
}
