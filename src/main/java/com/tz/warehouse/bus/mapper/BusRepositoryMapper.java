package com.tz.warehouse.bus.mapper;

import com.tz.warehouse.bus.entity.BusRepository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author lenovo
* @description 针对表【bus_repository(商品库存)】的数据库操作Mapper
* @createDate 2022-11-16 14:19:02
* @Entity com.tz.warehouse.bus.entity.BusRepository
*/
public interface BusRepositoryMapper extends BaseMapper<BusRepository> {

    void addStock(@Param("goodsId") Long goodsId, @Param("wareId") Long wareId, @Param("num") Integer num);
}




