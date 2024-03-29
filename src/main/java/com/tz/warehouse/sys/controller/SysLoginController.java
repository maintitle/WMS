package com.tz.warehouse.sys.controller;

import com.tz.warehouse.sys.common.utils.Captcha;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.dto.UserLoginParam;
import com.tz.warehouse.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TangZhen on 2022/12/5
 */
@Api("登入接口")
@RestController
public class SysLoginController {
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
    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody @Validated UserLoginParam user, HttpServletRequest request) {
        String token = sysUserService.login(user,request);
        if (token == null) {
            return R.error("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return R.ok().put("data", tokenMap);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("退出")
    @PostMapping("/logout")
    public R logout() {
        return R.ok();
    }

    @ApiOperation("获取验证")
    @GetMapping("/getCode")
    public R getCode() {
        Map<String, Object> code = captcha.getCode();
        return R.ok().put("data", code);
    }

    /**
     * 插入用户
     *
     * @param user 用户表单
     * @return 返回状态代码
     */
    //@ApiOperation("注册")
    //@PostMapping("/register")
    //public R register(@RequestBody @Validated(AddGroup.class) SysUser user) {
    //    sysUserService.insertUser(user);
    //    return R.ok();
    //}
}
