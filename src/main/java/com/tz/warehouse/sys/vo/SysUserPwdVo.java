package com.tz.warehouse.sys.vo;

import lombok.Data;

/**
 * Created by TangZhen on 2023/5/2
 */
@Data
public class SysUserPwdVo {
    private Long id;
    private String pwd;
    private String oldPwd;
}
