package com.tz.warehouse.sys.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.tz.warehouse.sys.common.utils.Captcha;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import com.tz.warehouse.sys.dto.SysMenu;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.entity.SysRole;
import com.tz.warehouse.sys.entity.SysUser;
import com.tz.warehouse.sys.service.SysRoleService;
import com.tz.warehouse.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/11/5
 */
@RestController
@RequestMapping("/user")
@Api("登入接口")
public class SysUserController {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private Captcha captcha;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 登入用户返回token
     *
     * @param user 用户名，密码，验证码
     * @return 返回Token
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody @Validated UserLoginParam user) {
        String token = sysUserService.login(user);
        if (token == null) {
            return R.error("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return R.ok().put("data", tokenMap);
    }

    /**
     * 插入用户
     *
     * @param user 用户表单
     * @return 返回状态代码
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody @Validated(AddGroup.class) SysUser user) {
        sysUserService.insertUser(user);
        return R.ok();
    }

    /**
     * 更新用户
     *
     * @param user 用户表单
     * @return 返回状态代码
     */
    @ApiOperation("更新用户")
    @PutMapping("/update")
    public R update(@RequestBody @Validated(UpdateGroup.class) SysUser user) {
        sysUserService.updateUser(user);
        return R.ok();
    }

    @ApiOperation("获取用户信息和菜单")
    @GetMapping("/info")
    public R getInfo(Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return R.error("未认证");
        }
        //获取用户基础信息
        String name = principal.getName();
        SysUser user = sysUserService.getUserByUsername(name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", user.getName());
        map.put("icon", user.getImgpath());
        //获取菜单列表
        List<SysPermission> menus = sysUserService.getMenus(user.getId());
        List<SysMenu> sysMenus = menus.stream().map(item -> {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(item.getId());
            sysMenu.setName(item.getPercode());
            sysMenu.setIcon(item.getIcon());
            sysMenu.setParentId(item.getPid());
            sysMenu.setTitle(item.getTitle());
            sysMenu.setHidden(item.getAvailable());
            return sysMenu;
        }).collect(Collectors.toList());
        map.put("menus", sysMenus);
        //获取角色
        List<Long> roleList = sysUserService.getRoleList(user.getId());
        List<SysRole> roles = sysRoleService.getRoleList(roleList);
        if (CollectionUtils.isNotEmpty(roles)){
            List<String> collect = roles.stream().map(SysRole::getName).collect(Collectors.toList());
            map.put("roles", collect);
        }
        return R.ok().put("data", map);
    }

    @ApiOperation("获取验证")
    @GetMapping("/getCode")
    public R getCode() {
        Map<String, Object> code = captcha.getCode();
        return R.ok().put("data", code);
    }

}
