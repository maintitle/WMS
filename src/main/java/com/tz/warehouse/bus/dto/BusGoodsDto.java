package com.tz.warehouse.bus.dto;

import com.tz.warehouse.bus.entity.BusGoods;
import lombok.Data;

/**
 * Created by TangZhen on 2022/11/13
 */
@Data
public class BusGoodsDto extends BusGoods {
    private String providername;
}
