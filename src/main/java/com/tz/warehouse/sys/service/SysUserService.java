package com.tz.warehouse.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.entity.SysUser;
import com.tz.warehouse.sys.vo.SysUserPwdVo;
import com.tz.warehouse.sys.vo.SysUserVo;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-11-05 16:31:58
*/
public interface SysUserService extends IService<SysUser> {
    UserDetails loadUserByUsername(String username);
    List<SysPermission> getResourceList(Long adminId);

    String login(UserLoginParam user, HttpServletRequest request);

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

    /**
     * 获取用户
     * @param name
     * @return
     */
    SysUser getUserByUsername(String name);

    /**
     * 获取菜单列表
     * @param id
     * @return
     */
    List<SysPermission> getMenus(Long id);

    /**
     * 获取角色
     * @param id
     * @return
     */
    List<Long> getRoleList(Long id);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    SysUserVo getUserById(Long id);

    /**
     * 查询用户ID和Name
     * @return
     */
    List<SysUserVo> getIdAndName();

    /**
     * 根据条件获取用户
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    void saveUser(SysUser sysUser);

    void updatePwdById(SysUserPwdVo userPwdVo);
}
