package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.entity.SysLoginfo;
import com.tz.warehouse.sys.service.SysLoginfoService;
import com.tz.warehouse.sys.mapper.SysLoginfoMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lenovo
 * @description 针对表【sys_loginfo】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysLoginfoServiceImpl extends ServiceImpl<SysLoginfoMapper, SysLoginfo>
        implements SysLoginfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<SysLoginfo> queryWrapper = new LambdaQueryWrapper<>();
        String name = (String) params.get("name");
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.likeRight(SysLoginfo::getLoginname, name);
        }
        String ip = (String) params.get("ip");
        if(StringUtils.isNotEmpty(ip)){
            queryWrapper.eq(SysLoginfo::getLoginip, ip);
        }
        String startDate = (String) params.get("startDate");
        if(StringUtils.isNotEmpty(startDate)){
            queryWrapper.ge(SysLoginfo::getLogintime, startDate);
        }
        String endDate = (String) params.get("endDate");
        if(StringUtils.isNotEmpty(endDate)){
            queryWrapper.le(SysLoginfo::getLogintime, endDate);
        }
        queryWrapper.orderByDesc(SysLoginfo::getLogintime);
        IPage<SysLoginfo> page = page(new Query<SysLoginfo>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }
}




