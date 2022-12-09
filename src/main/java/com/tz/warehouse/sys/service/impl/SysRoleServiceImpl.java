package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.entity.SysRole;
import com.tz.warehouse.sys.service.SysRoleService;
import com.tz.warehouse.sys.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    @Override
    public List<SysRole> getRoleList(List<Long> roleList) {
        return list(new LambdaQueryWrapper<SysRole>().in(SysRole::getId, roleList));
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.likeRight(SysRole::getName, key);
        }

        IPage<SysRole> page = page(new Query<SysRole>().getPage(params), queryWrapper);
        return new PageUtils(page);

    }
}




