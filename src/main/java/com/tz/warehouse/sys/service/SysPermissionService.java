package com.tz.warehouse.sys.service;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【sys_permission】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 查询权限
     * @param collect id列表
     * @return SysPermission
     */
    List<SysPermission> getPermission(List<Long> collect);

    /**
     * 根据条件获取菜单列表
     * @param collect
     * @return
     */
    List<SysPermission> getMenus(List<Long> collect);

    /**
     * 根据条件分页查询列表
     * @param params
     * @return
     */
    PageUtils queryPagePermission(Map<String, Object> params);

    /**
     * 获取全部菜单
     * @return
     */
    List<SysPermission> getMenusAll();

}
