package com.tz.warehouse.bus.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by TangZhen on 2022/11/20
 */
@Data
public class PurchaseDoneVo {
    @NotNull
    private Long id;
    private List<RepositoryDoneVo> items;
}
