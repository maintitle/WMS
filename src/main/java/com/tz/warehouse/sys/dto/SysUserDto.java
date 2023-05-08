package com.tz.warehouse.sys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;
import java.util.List;

/**
 * Created by TangZhen on 2023/5/9
 */
@Data
public class SysUserDto {
    private Long id;
    private String name;
    private String phone;
    private String pwd;
    private String address;
    private Integer sex;
    private String remark;
    private List<String> role;
    private Integer deptid;

    private Date hiredate;
    private Integer mgr;
    @Range(min = 0, max = 999, message = "0-999")
    private Integer ordernum;
}
