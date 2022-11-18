package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusGoods;
import com.tz.warehouse.bus.entity.BusPurchase;
import com.tz.warehouse.bus.entity.BusRequirement;
import com.tz.warehouse.bus.entity.BusWare;
import com.tz.warehouse.bus.mapper.BusRequirementMapper;
import com.tz.warehouse.bus.service.BusGoodsService;
import com.tz.warehouse.bus.service.BusRequirementService;
import com.tz.warehouse.bus.service.BusWareService;
import com.tz.warehouse.bus.vo.BusRequirementDto;
import com.tz.warehouse.bus.vo.MergeVo;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.common.utils.RRException;
import com.tz.warehouse.sys.common.utils.WareConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【bus_requirement(采购需求)】的数据库操作Service实现
 * @createDate 2022-11-16 19:02:14
 */
@Service
public class BusRequirementServiceImpl extends ServiceImpl<BusRequirementMapper, BusRequirement>
        implements BusRequirementService {

    @Autowired
    private BusWareService wareService;
    @Autowired
    private BusGoodsService goodsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusRequirement> queryWrapper = new LambdaQueryWrapper<>();
        //TODO key对采商品的筛选
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.and(item -> {
                item.eq(BusRequirement::getId, key)
                        .eq(BusRequirement::getGoodsId, key)
                        .eq(BusRequirement::getPurchaseId, key);
            });
        }
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotEmpty(wareId)) {
            queryWrapper.eq(BusRequirement::getWareId, wareId);
        }
        String status = (String) params.get("status");
        if (StringUtils.isNotEmpty(status)) {
            queryWrapper.eq(BusRequirement::getStatus, status);
        }
        IPage<BusRequirement> page = page(new Query<BusRequirement>().getPage(params), queryWrapper);

        //查询商品名
        List<BusGoods> goodsList = goodsService.list();
        Map<Long, String> goodsMap = new HashMap<>();
        goodsList.forEach(item -> {
            goodsMap.put(item.getId(), item.getGoodsname());
        });

        //查询仓库名
        List<BusWare> wareList = wareService.list();
        Map<Long, String> wareMap = new HashMap<>();
        wareList.forEach(item -> {
            wareMap.put(item.getId(), item.getName());
        });

        //对仓库名和商品进行封装
        List<BusRequirement> records = page.getRecords();
        List<BusRequirementDto> collect = records.stream().map(item -> {
            BusRequirementDto requirementDto = new BusRequirementDto();
            BeanUtils.copyProperties(item, requirementDto);
            if (requirementDto.getGoodsId() != null) {
                requirementDto.setGoodsName(goodsMap.get(requirementDto.getGoodsId()));
            }
            if (requirementDto.getWareId() != null) {
                requirementDto.setWareName(wareMap.get(requirementDto.getWareId()));
            }
            return requirementDto;
        }).collect(Collectors.toList());

        PageUtils requirementPage = new PageUtils(page);
        requirementPage.setList(collect);

        return requirementPage;
    }

    @Override
    public void saveAndCheck(BusRequirement busRequirement) {
        busRequirement.setUpdateTime(null);
        busRequirement.setUpdateTime(null);
        if (busRequirement.getWareId() != null) {
            BusWare byId = wareService.getById(busRequirement.getWareId());
            if (!ObjectUtils.isNotEmpty(byId)) {
                throw new RRException("仓库站点不存在");
            }
        }
        if (busRequirement.getGoodsId() != null) {
            BusGoods byId = goodsService.getById(busRequirement.getGoodsId());
            if (!ObjectUtils.isNotEmpty(byId)) {
                throw new RRException("商品不存在");
            }
        }
        //设置状态为新建
        busRequirement.setStatus(0);
        save(busRequirement);
    }

    @Override
    public void updateAndCheck(BusRequirement busRequirement) {
        busRequirement.setUpdateTime(null);
        busRequirement.setUpdateTime(null);
        if (busRequirement.getWareId() != null) {
            BusWare byId = wareService.getById(busRequirement.getWareId());
            if (!ObjectUtils.isNotEmpty(byId)) {
                throw new RRException("仓库站点不存在");
            }
        }
        if (busRequirement.getGoodsId() != null) {
            BusGoods byId = goodsService.getById(busRequirement.getGoodsId());
            if (!ObjectUtils.isNotEmpty(byId)) {
                throw new RRException("商品不存在");
            }
        }
        updateById(busRequirement);
    }

    @Override
    public void deleteAndCheck(List<Long> ids) {
        List<BusRequirement> busRequirements = listByIds(ids);
        //判断采购单id是否为空
        List<BusRequirement> collect = busRequirements.stream().filter(item -> item.getPurchaseId() != null).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(collect)) {
            throw new RRException("请先删除采购单");
        } else {
            removeBatchByIds(ids);
        }
    }




}




