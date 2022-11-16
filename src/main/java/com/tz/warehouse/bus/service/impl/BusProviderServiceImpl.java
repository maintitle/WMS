package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusProvider;
import com.tz.warehouse.bus.service.BusProviderService;
import com.tz.warehouse.bus.mapper.BusProviderMapper;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author lenovo
* @description 针对表【bus_provider】的数据库操作Service实现
* @createDate 2022-11-05 16:31:26
*/
@Service
public class BusProviderServiceImpl extends ServiceImpl<BusProviderMapper, BusProvider>
    implements BusProviderService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusProvider> queryWrapper = new LambdaQueryWrapper<>();
        String providername = (String) params.get("providername");
        if(StringUtils.isNotEmpty(providername)){
            queryWrapper.likeRight(BusProvider::getProvidername, providername);
        }
        String connectionpersion = (String) params.get("connectionpersion");
        if(StringUtils.isNotEmpty(connectionpersion)){
            queryWrapper.likeRight(BusProvider::getConnectionpersion, connectionpersion);
        }
        String phone = (String) params.get("phone");
        if(StringUtils.isNotEmpty(phone)){
            queryWrapper.likeRight(BusProvider::getPhone, phone);
        }
        IPage<BusProvider> page = page(new Query<BusProvider>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public List<HashMap<String, Object>> getListNameAndId() {
        LambdaQueryWrapper<BusProvider> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusProvider::getAvailable, 1);
        return list(queryWrapper).stream().map(item -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("producterid", item.getId());
            map.put("productername", item.getProvidername());
            return map;
        }).collect(Collectors.toList());
    }
}




