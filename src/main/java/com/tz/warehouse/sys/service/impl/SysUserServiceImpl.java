package com.tz.warehouse.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.IPUtils;
import com.tz.warehouse.sys.common.utils.JwtTokenUtil;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.dto.AdminUserDetails;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.*;
import com.tz.warehouse.sys.mapper.SysUserMapper;
import com.tz.warehouse.sys.service.*;
import com.tz.warehouse.sys.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    private SysRoleService roleService;
    @Autowired
    private SysRolePermissionService rolePermissionService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysLoginfoService loginfoService;


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
    public String login(UserLoginParam user, HttpServletRequest request) {
        String token = null;
        try {
            UserDetails userDetails = this.loadUserByUsername(user.getUsername());
            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            //获取用户登入地址
            String ipAddr = IPUtils.getClientIpAddr(request);
            //获取user用户信息
            AdminUserDetails userDetail = (AdminUserDetails) authentication.getPrincipal();
            SysLoginfo loginfo = new SysLoginfo();
            loginfo.setLoginname(userDetail.getUser().getLoginname());
            loginfo.setLoginip(ipAddr);
            loginfo.setLoginid(userDetail.getUser().getId());
            loginfo.setLogintime(LocalDateTime.now());
            loginfoService.save(loginfo);
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
     *
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

    @Override
    public List<SysUserVo> getIdAndName() {
        List<SysUserVo> collect = list().stream().map(item -> {
            SysUserVo userVo = new SysUserVo();
            userVo.setId(item.getId());
            if (StringUtils.isNotEmpty(item.getName())) {
                userVo.setName(item.getName());
                userVo.setPhone(item.getPhone());
            }
            return userVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        String deptid = (String) params.get("deptid");
        if (StringUtils.isNotEmpty(deptid)) {
            queryWrapper.likeRight(SysUser::getDeptid, deptid);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.and(obj -> obj.likeRight(SysUser::getName, key).or().likeRight(SysUser::getLoginname, key));
        }
        String address = (String) params.get("address");
        if (StringUtils.isNotEmpty(address)) {
            queryWrapper.likeRight(SysUser::getAddress, address);
        }
        IPage<SysUser> page = this.page(new Query<SysUser>().getPage(params), queryWrapper);
        //替换数据
        List<SysUser> records = page.getRecords();
        List<SysUserVo> collect = records.stream().map(o -> {
            SysUserVo userVo = new SysUserVo();
            BeanUtils.copyProperties(o, userVo);
            //包装角色信息
            SysUserRole userRole = userRoleService.getById(o.getId());
            if (ObjectUtils.isNotEmpty(userRole)) {
                SysRole role = roleService.getById(userRole.getRid());
                userVo.setRole(role.getName());
            }
            return userVo;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);
        return pageUtils;
    }
}




