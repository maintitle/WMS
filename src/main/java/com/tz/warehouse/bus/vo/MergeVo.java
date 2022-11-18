package com.tz.warehouse.bus.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by TangZhen on 2022/11/18
 */
@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}
