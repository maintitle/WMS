package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName bus_outport
 */
@TableName(value ="bus_outport")
@Data
public class BusOutport implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long providerid;

    /**
     * 
     */
    private String paytype;

    /**
     * 
     */
    private LocalDateTime outputtime;

    /**
     * 
     */
    private String operateperson;

    /**
     * 
     */
    private Double outportprice;

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
        BusOutport other = (BusOutport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProviderid() == null ? other.getProviderid() == null : this.getProviderid().equals(other.getProviderid()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getOutputtime() == null ? other.getOutputtime() == null : this.getOutputtime().equals(other.getOutputtime()))
            && (this.getOperateperson() == null ? other.getOperateperson() == null : this.getOperateperson().equals(other.getOperateperson()))
            && (this.getOutportprice() == null ? other.getOutportprice() == null : this.getOutportprice().equals(other.getOutportprice()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getGoodsid() == null ? other.getGoodsid() == null : this.getGoodsid().equals(other.getGoodsid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProviderid() == null) ? 0 : getProviderid().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getOutputtime() == null) ? 0 : getOutputtime().hashCode());
        result = prime * result + ((getOperateperson() == null) ? 0 : getOperateperson().hashCode());
        result = prime * result + ((getOutportprice() == null) ? 0 : getOutportprice().hashCode());
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
        sb.append(", providerid=").append(providerid);
        sb.append(", paytype=").append(paytype);
        sb.append(", outputtime=").append(outputtime);
        sb.append(", operateperson=").append(operateperson);
        sb.append(", outportprice=").append(outportprice);
        sb.append(", number=").append(number);
        sb.append(", remark=").append(remark);
        sb.append(", goodsid=").append(goodsid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}