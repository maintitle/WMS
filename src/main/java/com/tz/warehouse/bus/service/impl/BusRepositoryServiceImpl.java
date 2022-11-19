package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusRepository;
import com.tz.warehouse.bus.service.BusRepositoryService;
import com.tz.warehouse.bus.mapper.BusRepositoryMapper;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【bus_repository(商品库存)】的数据库操作Service实现
* @createDate 2022-11-16 14:19:02
*/
@Service
public class BusRepositoryServiceImpl extends ServiceImpl<BusRepositoryMapper, BusRepository>
    implements BusRepositoryService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusRepository> queryWrapper = new LambdaQueryWrapper<>();
        String wareId = (String) params.get("wareId");
        if(StringUtils.isNotEmpty(wareId)){
            queryWrapper.eq(BusRepository::getWareId, wareId);
        }
        String key = (String) params.get("key");
        if(StringUtils.isNotEmpty(key)){
            queryWrapper.likeRight(BusRepository::getWareName,key);
        }
        IPage<BusRepository> page = page(new Query<BusRepository>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public void deleteAndCheck(List<Long> ids) {
        removeBatchByIds(ids);
    }
}




