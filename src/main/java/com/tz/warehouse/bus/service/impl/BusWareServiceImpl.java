package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusWare;
import com.tz.warehouse.bus.service.BusWareService;
import com.tz.warehouse.bus.mapper.BusWareMapper;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_ware(仓库)】的数据库操作Service实现
* @createDate 2022-11-16 14:19:19
*/
@Service
public class BusWareServiceImpl extends ServiceImpl<BusWareMapper, BusWare>
    implements BusWareService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusWare> queryWrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");
        if(StringUtils.isNotEmpty(key)){
            queryWrapper.and(item->{
                item.likeRight(BusWare::getName, key).or().likeRight(BusWare::getAddress, key);
            });
        }
        String code = (String) params.get("code");
        if(StringUtils.isNotEmpty(code)){
            queryWrapper.eq(BusWare::getAreacode, code);
        }
        IPage<BusWare> page = page(new Query<BusWare>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }
}




