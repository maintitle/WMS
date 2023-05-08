package com.tz.warehouse.sys.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by TangZhen on 2022/11/19
 */
@Data
public class SysUserVo {
    private Long id;
    private String name;
    private String loginname;
    private String phone;
    private Integer sex;
    private Integer deptid;
    private Integer available;
    private Integer ordernum;
    private List<Long> role;
    private Long rid;
    private Date hiredate;
}
