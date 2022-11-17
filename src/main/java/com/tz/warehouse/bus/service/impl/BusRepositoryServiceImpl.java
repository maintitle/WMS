package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusRepository;
import com.tz.warehouse.bus.service.BusRepositoryService;
import com.tz.warehouse.bus.mapper.BusRepositoryMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【bus_repository(商品库存)】的数据库操作Service实现
* @createDate 2022-11-16 14:19:02
*/
@Service
public class BusRepositoryServiceImpl extends ServiceImpl<BusRepositoryMapper, BusRepository>
    implements BusRepositoryService{

}




