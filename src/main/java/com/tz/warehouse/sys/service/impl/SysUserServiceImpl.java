package com.tz.warehouse.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.JwtTokenUtil;
import com.tz.warehouse.sys.dto.AdminUserDetails;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.*;
import com.tz.warehouse.sys.mapper.SysUserMapper;
import com.tz.warehouse.sys.service.SysPermissionService;
import com.tz.warehouse.sys.service.SysRolePermissionService;
import com.tz.warehouse.sys.service.SysUserRoleService;
import com.tz.warehouse.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:58
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysRolePermissionService rolePermissionService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getLoginname, username);
        SysUser user = this.getOne(queryWrapper);

        if (user != null) {
            List<SysPermission> resourceList = this.getResourceList(user.getId());
            return new AdminUserDetails(user, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 获取权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> getResourceList(Long userId) {
        List<Long> roleList = getRoleList(userId);
        return sysPermissionService.getPermission(rolePermissionService.getRelationPermission(roleList));
    }

    @Override
    public String login(UserLoginParam user) {
        String token = null;
        try {
            UserDetails userDetails = this.loadUserByUsername(user.getUsername());
            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public void insertUser(SysUser user) {
        String encodePassword = passwordEncoder.encode(user.getPwd());
        user.setPwd(encodePassword);
        baseMapper.insert(user);
    }

    @Override
    public void updateUser(SysUser user) {
        if (!StrUtil.isEmpty(user.getPwd())) {
            user.setPwd(passwordEncoder.encode(user.getPwd()));
        }
        baseMapper.updateById(user);
    }

    @Override
    public SysUser getUserByUsername(String name) {
        return getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getLoginname, name));
    }

    @Override
    public List<SysPermission> getMenus(Long id) {
        List<Long> roleList = getRoleList(id);
        return sysPermissionService.getMenus(rolePermissionService.getRelationPermission(roleList));
    }

    /**
     * 获取role id
     * @param id
     * @return
     */
    @Override
    public List<Long> getRoleList(Long id) {
        List<SysUserRole> relationRole = userRoleService.getRelationRole(id);
        return relationRole.stream().map(SysUserRole::getRid).collect(Collectors.toList());
    }

    @Override
    public SysUser getUserById(Long id) {
        return getById(id);
    }
}




