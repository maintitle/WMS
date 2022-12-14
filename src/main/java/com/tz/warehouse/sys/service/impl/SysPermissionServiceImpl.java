package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.mapper.SysPermissionMapper;
import com.tz.warehouse.sys.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @description 针对表【sys_permission】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
        implements SysPermissionService {

    @Override
    public List<SysPermission> getPermission(List<Long> collect) {
        return list(new LambdaQueryWrapper<SysPermission>()
                .and(item -> {
                    item.in(SysPermission::getId, collect).eq(SysPermission::getType, "permission");
                }));

    }

    @Override
    public List<SysPermission> getMenus(List<Long> collect) {
        return list(new LambdaQueryWrapper<SysPermission>()
                .and(item -> {
                    item.in(SysPermission::getId, collect).eq(SysPermission::getType, "menu");
                }));
    }

    @Override
    public PageUtils queryPagePermission(Map<String, Object> params) {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysPermission::getType, "permission");
        String title = (String) params.get("title");
        if (StringUtils.isNotEmpty(title)) {
            queryWrapper.likeRight(SysPermission::getTitle, title);
        }
        String percode = (String) params.get("percode");
        if (StringUtils.isNotEmpty(percode)) {
            queryWrapper.likeRight(SysPermission::getPercode, percode);
        }
        String pid = (String) params.get("pid");
        if(StringUtils.isNotEmpty(pid)){
            queryWrapper.eq(SysPermission::getPid, pid);
        }
        IPage<SysPermission> page = this.page(new Query<SysPermission>().getPage(params), queryWrapper);

        return new PageUtils(page);

    }

    @Override
    public List<SysPermission> getMenusAll() {
        return list(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getType, "menu"));
    }

    @Override
    public List<SysPermission> getPermissionByPid(List<Long> ids) {
        return list(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getPid, ids));
    }
}




