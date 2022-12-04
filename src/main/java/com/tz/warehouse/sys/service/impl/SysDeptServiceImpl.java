package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.entity.SysDept;
import com.tz.warehouse.sys.mapper.SysDeptMapper;
import com.tz.warehouse.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lenovo
 * @description 针对表【sys_dept】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept>
        implements SysDeptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.likeRight(SysDept::getName, key);
        }
        String id = (String) params.get("id");
        if (StringUtils.isNotEmpty(id)) {
            queryWrapper.eq(SysDept::getId, id);
        }
        IPage<SysDept> page = this.page(new Query<SysDept>().getPage(params), queryWrapper);

        return new PageUtils(page);
    }
}




