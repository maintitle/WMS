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
 * @TableName bus_inport
 */
@TableName(value ="bus_inport")
@Data
public class BusInport implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String paytype;

    /**
     * 
     */
    private LocalDateTime inporttime;

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
    private Double inportprice;

    /**
     * 
     */
    private Integer providerid;

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
        BusInport other = (BusInport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getInporttime() == null ? other.getInporttime() == null : this.getInporttime().equals(other.getInporttime()))
            && (this.getOperateperson() == null ? other.getOperateperson() == null : this.getOperateperson().equals(other.getOperateperson()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getInportprice() == null ? other.getInportprice() == null : this.getInportprice().equals(other.getInportprice()))
            && (this.getProviderid() == null ? other.getProviderid() == null : this.getProviderid().equals(other.getProviderid()))
            && (this.getGoodsid() == null ? other.getGoodsid() == null : this.getGoodsid().equals(other.getGoodsid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getInporttime() == null) ? 0 : getInporttime().hashCode());
        result = prime * result + ((getOperateperson() == null) ? 0 : getOperateperson().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInportprice() == null) ? 0 : getInportprice().hashCode());
        result = prime * result + ((getProviderid() == null) ? 0 : getProviderid().hashCode());
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
        sb.append(", paytype=").append(paytype);
        sb.append(", inporttime=").append(inporttime);
        sb.append(", operateperson=").append(operateperson);
        sb.append(", number=").append(number);
        sb.append(", remark=").append(remark);
        sb.append(", inportprice=").append(inportprice);
        sb.append(", providerid=").append(providerid);
        sb.append(", goodsid=").append(goodsid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}