package com.tz.warehouse.sys.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.dto.SysMenu;
import com.tz.warehouse.sys.dto.SysUserDto;
import com.tz.warehouse.sys.entity.SysPermission;
import com.tz.warehouse.sys.entity.SysRole;
import com.tz.warehouse.sys.entity.SysUser;
import com.tz.warehouse.sys.service.SysRoleService;
import com.tz.warehouse.sys.service.SysUserService;
import com.tz.warehouse.sys.vo.SysUserPwdVo;
import com.tz.warehouse.sys.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TangZhen on 2022/11/5
 */
@RestController
@RequestMapping("/system/user")
@Api("用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 更新用户
     *
     * @param user 用户表单
     * @return 返回状态代码
     */
    @ApiOperation("更新用户")
    @PostMapping("/update")
    public R update(@RequestBody SysUserDto user) {
        sysUserService.updateUser(user);
        return R.ok();
    }
    @ApiOperation("更新密码")
    @PostMapping("/updatePwd")
    public R updatePwd(@RequestBody SysUserPwdVo user) {
        sysUserService.updatePwdById(user);
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
        map.put("id", user.getId());
        //获取菜单列表
        List<SysPermission> menus = sysUserService.getMenus(user.getId());
        List<SysMenu> sysMenus = menus.stream().map(item -> {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(item.getId());
            sysMenu.setName(item.getName());
            sysMenu.setIcon(item.getIcon());
            sysMenu.setParentId(item.getPid());
            sysMenu.setTitle(item.getTitle());
            sysMenu.setHidden(item.getHidden());
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

    @ApiOperation("获取用户信息和菜单")
    @GetMapping("/infoDetail/{id}")
    public R getInfoDetail(@PathVariable("id") Long id){
        SysUserVo sysUserVo=sysUserService.getUserById(id);
        return R.ok().put("data", sysUserVo);
    }


    @ApiOperation("获取用户列表")
    @GetMapping("/listAll")
    public R listAll(){
        List<SysUserVo> userVos=sysUserService.getIdAndName();
        return R.ok().put("data", userVos);
    }

    @GetMapping("/list")
    @ApiOperation("根据条件分页获取用户信息列表")
    public R getList(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = sysUserService.queryPage(params);
        return R.ok().put("data", page);
    }

    @PostMapping("/save")
    @ApiOperation("保存用户")
    public R save(@RequestBody SysUser sysUser){
        sysUserService.saveUser(sysUser);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation("删除用户")
    public R delete(@RequestBody ArrayList<Long> ids){
        sysUserService.removeBatchByIds(ids);
        return R.ok();
    }


}
