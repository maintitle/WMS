package com.tz.warehouse.bus.vo;

import com.tz.warehouse.bus.entity.BusRequirement;
import lombok.Data;

/**
 * Created by TangZhen on 2022/11/18
 */
@Data
public class BusRequirementDto extends BusRequirement {
    private String goodsName;
    private String wareName;
}
