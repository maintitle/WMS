package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName bus_goods
 */
@TableName(value ="bus_goods")
@Data
public class BusGoods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String goodsname;

    /**
     * 
     */
    private Integer providerid;

    /**
     * 
     */
    private String produceplace;

    /**
     * 
     */
    private String size;

    /**
     * 
     */
    private String goodspackage;

    /**
     * 
     */
    private String productcode;

    /**
     * 
     */
    private String promitcode;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Double price;

    /**
     * 
     */
    private Integer number;

    /**
     * 
     */
    private Integer dangernum;

    /**
     * 
     */
    private String goodsimg;

    /**
     * 
     */
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
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getDangernum() == null ? other.getDangernum() == null : this.getDangernum().equals(other.getDangernum()))
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
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getDangernum() == null) ? 0 : getDangernum().hashCode());
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
        sb.append(", price=").append(price);
        sb.append(", number=").append(number);
        sb.append(", dangernum=").append(dangernum);
        sb.append(", goodsimg=").append(goodsimg);
        sb.append(", available=").append(available);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}