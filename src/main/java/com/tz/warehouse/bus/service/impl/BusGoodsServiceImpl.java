package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.vo.BusGoodsDto;
import com.tz.warehouse.bus.entity.BusGoods;
import com.tz.warehouse.bus.entity.BusProvider;
import com.tz.warehouse.bus.mapper.BusGoodsMapper;
import com.tz.warehouse.bus.service.BusGoodsService;
import com.tz.warehouse.bus.service.BusProviderService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.common.utils.RRException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【bus_goods】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:26
 */
@Service
public class BusGoodsServiceImpl extends ServiceImpl<BusGoodsMapper, BusGoods>
        implements BusGoodsService {

    @Autowired
    private BusProviderService busProviderService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        LambdaQueryWrapper<BusGoods> queryWrapper = new LambdaQueryWrapper<>();
        String providerid = (String) params.get("providerid");
        if (StringUtils.isNotEmpty(providerid)) {
            queryWrapper.eq(BusGoods::getProviderid, providerid);
        }
        String goodsname = (String) params.get("goodsname");
        if (StringUtils.isNotEmpty(goodsname)) {
            queryWrapper.likeRight(BusGoods::getGoodsname, goodsname);
        }
        String productcode = (String) params.get("productcode");
        if (StringUtils.isNotEmpty(productcode)) {
            queryWrapper.eq(BusGoods::getProductcode, productcode);
        }
        String promitcode = (String) params.get("promitcode");
        if (StringUtils.isNotEmpty(promitcode)) {
            queryWrapper.eq(BusGoods::getPromitcode, promitcode);
        }

        IPage<BusGoods> page = page(new Query<BusGoods>().getPage(params), queryWrapper);
        List<BusGoods> records = page.getRecords();
        List<BusGoodsDto> collect = records.stream().map(item -> {
            BusGoodsDto goodsDto = new BusGoodsDto();
            BeanUtils.copyProperties(item, goodsDto);
            //查询供应商名
            BusProvider provider = busProviderService.getById(item.getProviderid());
            goodsDto.setProvidername(provider.getProvidername());
            return goodsDto;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public void saveAndCheckProvider(BusGoods busGoods) {
        BusProvider byId = busProviderService.getById(busGoods.getProviderid());
        if (ObjectUtils.isNotEmpty(byId)) {
            save(busGoods);
        } else {
            throw new RRException("没有该供应商");
        }
    }

    @Override
    public void updateAndCheckProvider(BusGoods busGoods) {
        if (busGoods.getProviderid() != null) {
            BusProvider byId = busProviderService.getById(busGoods.getProviderid());
            if (ObjectUtils.isEmpty(byId)) {
                throw new RRException("没有该供应商");
            }
        }
        updateById(busGoods);
    }

    @Override
    public List<HashMap<String, Object>> getListNameAndId() {
        LambdaQueryWrapper<BusGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusGoods::getAvailable, 1);
        return list(queryWrapper).stream().map(item -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("goodsId", item.getId());
            map.put("goodsName", item.getGoodsname());
            return map;
        }).collect(Collectors.toList());
    }
}




