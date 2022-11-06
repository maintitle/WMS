package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.Captcha;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.entity.SysUser;
import com.tz.warehouse.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    /**
     * 登入用户返回token
     *
     * @param user 用户名，密码，验证码
     * @return 返回Token
     */
    @ApiOperation("登入")
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

    @GetMapping("/getCode")
    public R getCode() {
        Map<String, Object> code = captcha.getCode();
        return R.ok().put("data", code);
    }

}
