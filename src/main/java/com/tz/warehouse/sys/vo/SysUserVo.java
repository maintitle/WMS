package com.tz.warehouse.sys.vo;

import lombok.Data;

/**
 * Created by TangZhen on 2022/11/19
 */
@Data
public class SysUserVo {
    private Long id;
    private String name;
    private String loginname;
    private String phone;
    private String address;
    private Integer sex;
    private String remark;
    private Integer deptid;
    private Integer available;
    private Integer ordernum;
}
