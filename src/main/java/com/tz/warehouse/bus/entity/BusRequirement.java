package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tz.warehouse.sys.common.valid.FlagValidator;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 采购需求
 * @TableName bus_requirement
 */
@TableName(value ="bus_requirement")
@Data
@ApiModel("采购需求实体类")
public class BusRequirement implements Serializable {
    /**
     * 采购id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("采购id")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Long id;

    /**
     * 采购单id
     */
    @ApiModelProperty("采购单id")
    private Long purchaseId;

    /**
     * 采购商品id
     */
    @ApiModelProperty("采购商品id")
    private Long goodsId;

    /**
     * 采购数量
     */
    @ApiModelProperty("采购数量")
    private Integer num;

    /**
     * 采购金额
     */
    @ApiModelProperty("采购金额")
    private BigDecimal price;

    /**
     * 仓库id
     */
    @ApiModelProperty("仓库id")
    private Long wareId;

    /**
     * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
     */
    @ApiModelProperty("状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    @FlagValidator(value = {0,1,2,3,4},message = "参数只能为[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

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
        BusRequirement other = (BusRequirement) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPurchaseId() == null ? other.getPurchaseId() == null : this.getPurchaseId().equals(other.getPurchaseId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getWareId() == null ? other.getWareId() == null : this.getWareId().equals(other.getWareId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPurchaseId() == null) ? 0 : getPurchaseId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", purchaseId=").append(purchaseId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", num=").append(num);
        sb.append(", price=").append(price);
        sb.append(", wareId=").append(wareId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}