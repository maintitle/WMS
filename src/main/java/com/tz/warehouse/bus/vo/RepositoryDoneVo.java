package com.tz.warehouse.bus.vo;

import lombok.Data;

/**
 * Created by TangZhen on 2022/11/20
 */
@Data
public class RepositoryDoneVo {
    private Long itemId;
    private Integer status;
    private String reason;
}
