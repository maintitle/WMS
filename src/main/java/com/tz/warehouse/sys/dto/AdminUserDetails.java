package com.tz.warehouse.sys.dto;

import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/9/15
 */
public class AdminUserDetails implements UserDetails {
    private SysUser sysUser;

    private List<SysPermission> resourceList;

    public AdminUserDetails(SysUser sysUser, List<SysPermission> resourceList) {
        this.sysUser = sysUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getPercode()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getPwd();
    }

    @Override
    public String getUsername() {
        return sysUser.getLoginname();
    }

    @Override
    public boolean isAccountNonExpired() { //账号是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //账号是否未锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //密码是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() { //是否激活
        return sysUser.getAvailable().equals(1);
    }
}
