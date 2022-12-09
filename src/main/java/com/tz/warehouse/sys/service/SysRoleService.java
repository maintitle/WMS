package com.tz.warehouse.sys.service;

import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleList(List<Long> roleList);

    /**
     * 根据条件获取角色列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
