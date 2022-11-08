package com.tz.warehouse.sys.dto;

import lombok.Data;

/**
 * Created by TangZhen on 2022/11/7
 */
@Data
public class SysMenu {
    private Long id;
    private Long parentId;
    private String title;
    private String name;
    private String icon;
    private Integer hidden;
}
