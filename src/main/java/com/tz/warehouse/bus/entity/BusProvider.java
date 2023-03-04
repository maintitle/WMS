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
 * @TableName bus_provider
 */
@TableName(value ="bus_provider")
@Data
@ApiModel("供应商实体类")
public class BusProvider implements Serializable {
    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("供应商ID")
    private Long id;

    /**
     * 供应商昵称
     */
    @ApiModelProperty("供应商昵称")
    @NotBlank(message = "供应商称不能为空", groups = AddGroup.class)
    private String providername;

    /**
     * 邮编"
     */
    @ApiModelProperty("邮编")
    private String zip;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 客户电话
     */
    @ApiModelProperty("客户电话")
    private String telephone;

    /**
     * 联系人姓名
     */
    @ApiModelProperty("联系人姓名")
    private String connectionpersion;

    /**
     * 联系人电话
     */
    @ApiModelProperty("联系人电话")
    private String phone;

    /**
     * 开户银行
     */
    @ApiModelProperty("开户银行")
    private String bank;

    /**
     * 银行账号
     */
    @ApiModelProperty("银行账号")
    private String account;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 传真
     */
    @ApiModelProperty("传真")
    private String fax;

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
        BusProvider other = (BusProvider) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProvidername() == null ? other.getProvidername() == null : this.getProvidername().equals(other.getProvidername()))
            && (this.getZip() == null ? other.getZip() == null : this.getZip().equals(other.getZip()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getConnectionpersion() == null ? other.getConnectionpersion() == null : this.getConnectionpersion().equals(other.getConnectionpersion()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getBank() == null ? other.getBank() == null : this.getBank().equals(other.getBank()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
            && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProvidername() == null) ? 0 : getProvidername().hashCode());
        result = prime * result + ((getZip() == null) ? 0 : getZip().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getConnectionpersion() == null) ? 0 : getConnectionpersion().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBank() == null) ? 0 : getBank().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
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
        sb.append(", providername=").append(providername);
        sb.append(", zip=").append(zip);
        sb.append(", address=").append(address);
        sb.append(", telephone=").append(telephone);
        sb.append(", connectionpersion=").append(connectionpersion);
        sb.append(", phone=").append(phone);
        sb.append(", bank=").append(bank);
        sb.append(", account=").append(account);
        sb.append(", email=").append(email);
        sb.append(", fax=").append(fax);
        sb.append(", available=").append(available);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}