package com.tz.warehouse.sys.service;

import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
* @author lenovo
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysUserService extends IService<SysUser> {
    UserDetails loadUserByUsername(String username);
    List<SysPermission> getResourceList(Long adminId);

    String login(UserLoginParam user);

    /**
     * 保存用户信息
     * @param user
     */
    void insertUser(SysUser user);
    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(SysUser user);
}
