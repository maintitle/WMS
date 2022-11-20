package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusGoods;
import com.tz.warehouse.bus.entity.BusRepository;
import com.tz.warehouse.bus.entity.BusWare;
import com.tz.warehouse.bus.mapper.BusRepositoryMapper;
import com.tz.warehouse.bus.service.BusGoodsService;
import com.tz.warehouse.bus.service.BusRepositoryService;
import com.tz.warehouse.bus.service.BusWareService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
        implements BusRepositoryService {

    @Autowired
    private BusWareService wareService;
    @Autowired
    private BusGoodsService goodsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusRepository> queryWrapper = new LambdaQueryWrapper<>();
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotEmpty(wareId)) {
            queryWrapper.eq(BusRepository::getWareId, wareId);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.likeRight(BusRepository::getWareName, key);
        }
        IPage<BusRepository> page = page(new Query<BusRepository>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public void deleteAndCheck(List<Long> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void updateAndCheck(BusRepository busRepository) {
        //只能修改仓库id和阈值
        BusRepository repository = new BusRepository();
        repository.setId(busRepository.getId());
        if (busRepository.getThreshold() != null) {
            repository.setThreshold(busRepository.getThreshold());
        }
        if (busRepository.getWareId() != null) {
            BusWare busWare = wareService.getById(busRepository.getWareId());
            repository.setWareId(busWare.getId());
            repository.setWareName(busWare.getName());
        }
        updateById(repository);
    }

    @Override
    public void addStock(Long goodsId, Long wareId, Integer num) {
        long count = count(new LambdaQueryWrapper<BusRepository>().eq(BusRepository::getGoodsId, goodsId).eq(BusRepository::getWareId, wareId));
        if (count > 0) {
            this.baseMapper.addStock(goodsId, wareId, num);
        } else {
            BusWare ware = wareService.getById(wareId);
            BusGoods goods = goodsService.getById(goodsId);
            if (!(ObjectUtils.isNotEmpty(ware) && ObjectUtils.isNotEmpty(goods))) {
                throw new RuntimeException("没有此仓库站点和商品");
            }
            BusRepository repository = new BusRepository();
            repository.setStock(num);
            repository.setGoodsName(goods.getGoodsname());
            repository.setGoodsId(goods.getId());
            repository.setWareId(ware.getId());
            repository.setWareName(ware.getName());
            repository.setThreshold(0);
            save(repository);
        }
    }
}




