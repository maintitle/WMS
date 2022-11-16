package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.FlagValidator;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 
 * @TableName bus_goods
 */
@TableName(value ="bus_goods")
@Data
@ApiModel("商品实体类")
public class BusGoods implements Serializable {
    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    @NotBlank(message = "商品名称不能为空", groups = AddGroup.class)
    private String goodsname;

    /**
     * 供应商ID
     */
    @ApiModelProperty("供应商ID")
    @NotNull(message = "供应商ID不能为空", groups = AddGroup.class)
    private Long providerid;

    /**
     * 商品产地
     */
    @ApiModelProperty("商品产地")
    private String produceplace;

    /**
     * 包装
     */
    @ApiModelProperty("包装")
    private String size;

    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String goodspackage;

    /**
     * 生产批号
     */
    @ApiModelProperty("生产批号")
    private String productcode;

    /**
     * 批准文号
     */
    @ApiModelProperty("批准文号")
    private String promitcode;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String description;

    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private String goodsimg;

    /**
     * 是否可用(0为不可用,1为可用)
     */
    @ApiModelProperty("是否可用(0为不可用,1为可用)")
    @FlagValidator(value = {0,1},groups = {AddGroup.class,UpdateGroup.class})
    private Integer available;

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
        BusGoods other = (BusGoods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsname() == null ? other.getGoodsname() == null : this.getGoodsname().equals(other.getGoodsname()))
            && (this.getProviderid() == null ? other.getProviderid() == null : this.getProviderid().equals(other.getProviderid()))
            && (this.getProduceplace() == null ? other.getProduceplace() == null : this.getProduceplace().equals(other.getProduceplace()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getGoodspackage() == null ? other.getGoodspackage() == null : this.getGoodspackage().equals(other.getGoodspackage()))
            && (this.getProductcode() == null ? other.getProductcode() == null : this.getProductcode().equals(other.getProductcode()))
            && (this.getPromitcode() == null ? other.getPromitcode() == null : this.getPromitcode().equals(other.getPromitcode()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getGoodsimg() == null ? other.getGoodsimg() == null : this.getGoodsimg().equals(other.getGoodsimg()))
            && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodsname() == null) ? 0 : getGoodsname().hashCode());
        result = prime * result + ((getProviderid() == null) ? 0 : getProviderid().hashCode());
        result = prime * result + ((getProduceplace() == null) ? 0 : getProduceplace().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getGoodspackage() == null) ? 0 : getGoodspackage().hashCode());
        result = prime * result + ((getProductcode() == null) ? 0 : getProductcode().hashCode());
        result = prime * result + ((getPromitcode() == null) ? 0 : getPromitcode().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getGoodsimg() == null) ? 0 : getGoodsimg().hashCode());
        result = prime * result + ((getAvailable() == null) ? 0 : getAvailable().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsname=").append(goodsname);
        sb.append(", providerid=").append(providerid);
        sb.append(", produceplace=").append(produceplace);
        sb.append(", size=").append(size);
        sb.append(", goodspackage=").append(goodspackage);
        sb.append(", productcode=").append(productcode);
        sb.append(", promitcode=").append(promitcode);
        sb.append(", description=").append(description);
        sb.append(", goodsimg=").append(goodsimg);
        sb.append(", available=").append(available);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}