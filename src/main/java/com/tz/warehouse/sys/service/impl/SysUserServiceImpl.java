package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.*;
import com.tz.warehouse.sys.dto.AdminUserDetails;
import com.tz.warehouse.sys.dto.SysUserDto;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.*;
import com.tz.warehouse.sys.mapper.SysUserMapper;
import com.tz.warehouse.sys.service.*;
import com.tz.warehouse.sys.vo.SysRoleInfoVo;
import com.tz.warehouse.sys.vo.SysUserPwdVo;
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
import java.util.ArrayList;
import java.util.Arrays;
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
    public void updateUser(SysUserDto userDto) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, userDto.getId());
        Long count = baseMapper.selectCount(queryWrapper);
        if (count <= 0) {
            throw new RRException("User dose Not Exist", 500);
        }
        if (StringUtils.isNotEmpty(userDto.getPwd())) {
            userDto.setPwd(passwordEncoder.encode(userDto.getPwd()));
        }
        if(CollectionUtils.isNotEmpty(userDto.getRole())){
            SysUserRole userRole = new SysUserRole();
            userRole.setUid(userDto.getId());
            userRole.setRid(String.join(";", userDto.getRole()));
            userRoleService.updateById(userRole);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto,sysUser);
        baseMapper.updateById(sysUser);
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
        SysUserRole relationRole = userRoleService.getById(id);
        if (ObjectUtils.isNotEmpty(relationRole) && StringUtils.isNotEmpty(relationRole.getRid())) {
            return Arrays.stream(relationRole.getRid().split(";")).map(Long::valueOf).collect(Collectors.toList());
        }
        return null;
    }

    public List<SysRoleInfoVo> getRoleInfoVo(Long id) {
        List<Long> roleIds = getRoleList(id);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<SysRole> roleList = roleService.getRoleList(roleIds);
            List<SysRoleInfoVo> sysRoleInfoVos = new ArrayList<>();
            roleList.forEach(i -> {
                        SysRoleInfoVo sysRoleInfoVo = new SysRoleInfoVo();
                        sysRoleInfoVo.setId(i.getId());
                        sysRoleInfoVo.setName(i.getName());
                        sysRoleInfoVos.add(sysRoleInfoVo);
                    }
            );
            return sysRoleInfoVos;
        }
        return null;
    }

    @Override
    public SysUserVo getUserById(Long id) {
        SysUser byId = getById(id);
        SysUserVo userVo = new SysUserVo();
        BeanUtils.copyProperties(byId, userVo);
        //包装角色信息
        userVo.setRole(getRoleList(id));
        return userVo;
    }

    @Override
    public List<SysUserVo> getIdAndName() {
        return list().stream().map(item -> {
            SysUserVo userVo = new SysUserVo();
            userVo.setId(item.getId());
            if (StringUtils.isNotEmpty(item.getName())) {
                userVo.setName(item.getName());
                userVo.setPhone(item.getPhone());
            }
            return userVo;
        }).collect(Collectors.toList());
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
            userVo.setRole(getRoleList(userVo.getId()));
            return userVo;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public void saveUser(SysUser sysUser) {
        ArrayList<String> userLoginName = baseMapper.selectLoginName();
        // 查询是否有重复的登入名
        if (userLoginName.contains(sysUser.getLoginname())) {
            throw new RRException("重复登入名", 500);
        }
        if(StringUtils.isEmpty(sysUser.getPwd())){
            sysUser.setPwd(passwordEncoder.encode("123456"));
        }
        save(sysUser);
    }

    @Override
    public void updatePwdById(SysUserPwdVo userPwdVo) {
        if (userPwdVo == null) {
            throw new RRException("User is Null", 500);
        }
        if (StringUtils.isEmpty(userPwdVo.getOldPwd()) && StringUtils.isEmpty(userPwdVo.getPwd())) {
            throw new RRException("Password is Null", 500);
        }
        SysUser sysUser = baseMapper.selectById(userPwdVo.getId());
        if (sysUser == null) {
            throw new RRException("User does Not Exist", 500);
        }
        if (!passwordEncoder.matches(userPwdVo.getOldPwd(), sysUser.getPwd())) {
            throw new RRException("Password Error", 500);
        }
        sysUser.setPwd(passwordEncoder.encode(userPwdVo.getPwd()));
        baseMapper.updateById(sysUser);
    }
}




