package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName bus_salesback
 */
@TableName(value ="bus_salesback")
@Data
public class BusSalesback implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer customerid;

    /**
     * 
     */
    private String paytype;

    /**
     * 
     */
    private LocalDateTime salesbacktime;

    /**
     * 
     */
    private Double salebackprice;

    /**
     * 
     */
    private String operateperson;

    /**
     * 
     */
    private Integer number;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private Integer goodsid;

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
        BusSalesback other = (BusSalesback) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerid() == null ? other.getCustomerid() == null : this.getCustomerid().equals(other.getCustomerid()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getSalesbacktime() == null ? other.getSalesbacktime() == null : this.getSalesbacktime().equals(other.getSalesbacktime()))
            && (this.getSalebackprice() == null ? other.getSalebackprice() == null : this.getSalebackprice().equals(other.getSalebackprice()))
            && (this.getOperateperson() == null ? other.getOperateperson() == null : this.getOperateperson().equals(other.getOperateperson()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getGoodsid() == null ? other.getGoodsid() == null : this.getGoodsid().equals(other.getGoodsid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerid() == null) ? 0 : getCustomerid().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getSalesbacktime() == null) ? 0 : getSalesbacktime().hashCode());
        result = prime * result + ((getSalebackprice() == null) ? 0 : getSalebackprice().hashCode());
        result = prime * result + ((getOperateperson() == null) ? 0 : getOperateperson().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getGoodsid() == null) ? 0 : getGoodsid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerid=").append(customerid);
        sb.append(", paytype=").append(paytype);
        sb.append(", salesbacktime=").append(salesbacktime);
        sb.append(", salebackprice=").append(salebackprice);
        sb.append(", operateperson=").append(operateperson);
        sb.append(", number=").append(number);
        sb.append(", remark=").append(remark);
        sb.append(", goodsid=").append(goodsid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}