package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品库存
 *
 * @TableName bus_repository
 */
@TableName(value = "bus_repository")
@Data
@ApiModel("商品库存实体类")
public class BusRepository implements Serializable {
    /**
     * 库存id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("库存id")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Long id;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long goodsId;

    /**
     * 商品名（冗余字段）
     */
    @ApiModelProperty("商品名")
    private String goodsName;

    /**
     * 仓库id
     */
    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("仓库名")
    private String wareName;
    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer stock;

    /**
     * 阈值
     */
    @ApiModelProperty("阈值")
    private Integer threshold;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BusRepository other = (BusRepository) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
                && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
                && (this.getWareId() == null ? other.getWareId() == null : this.getWareId().equals(other.getWareId()))
                && (this.getWareName() == null ? other.getWareName() == null : this.getWareName().equals(other.getWareName()))
                && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
                && (this.getThreshold() == null ? other.getThreshold() == null : this.getThreshold().equals(other.getThreshold()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
        result = prime * result + ((getWareName() == null) ? 0 : getWareName().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getThreshold() == null) ? 0 : getThreshold().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", wareId=").append(wareId);
        sb.append(", wareName=").append(wareName);
        sb.append(", stock=").append(stock);
        sb.append(", threshold=").append(threshold);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}