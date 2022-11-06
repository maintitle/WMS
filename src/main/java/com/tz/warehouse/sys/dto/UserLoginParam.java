package com.tz.warehouse.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by TangZhen on 2022/11/5
 */
@Data
@ApiModel("登入表单")
public class UserLoginParam {
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    //@NotBlank(message = "验证码不能为空")
    //@ApiModelProperty("验证码")
    //private String code;
}
