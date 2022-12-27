package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusStockin;
import com.tz.warehouse.bus.mapper.BusStockinMapper;
import com.tz.warehouse.bus.service.BusStockinService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_stockin】的数据库操作Service实现
* @createDate 2022-12-26 16:23:47
*/
@Service
public class BusStockinServiceImpl extends ServiceImpl<BusStockinMapper, BusStockin>
    implements BusStockinService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusStockin> queryWrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");
        if(StringUtils.isNotEmpty(key)){
            queryWrapper.and(obj->{
                obj.eq(BusStockin::getWid, key);
            });
        }
        IPage<BusStockin> page = page(new Query<BusStockin>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }
}




