package com.tz.warehouse.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tz.warehouse.sys.common.valid.AddGroup;
import com.tz.warehouse.sys.common.valid.FlagValidator;
import com.tz.warehouse.sys.common.valid.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户ID")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Long id;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    @NotBlank(message = "用户昵称不能为空", groups = AddGroup.class)
    private String name;

    /**
     * 登入名称
     */
    @ApiModelProperty("登入名称")
    @NotBlank(message = "登入名称不能为空", groups = AddGroup.class)
    private String loginname;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String pwd;

    /**
     * 用户地址
     */
    @ApiModelProperty("用户地址")
    private String address;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer sex;

    /**
     * 用户备注
     */
    @ApiModelProperty("用户备注")
    private String remark;

    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门")
    private Integer deptid;

    /**
     * 入职时间
     */
    @ApiModelProperty("入职时间")
    private Date hiredate;

    /**
     * 上级领导id
     */
    @ApiModelProperty("上级领导")
    private Integer mgr;

    /**
     * 是否可用，0不可用，1可用
     */
    @ApiModelProperty("是否可用")
    @FlagValidator(value = {0, 1}, message = "只能为0或1", groups = {AddGroup.class})
    private Integer available;

    /**
     * 排序码
     */
    @ApiModelProperty("排序码")
    @Range(min = 0, max = 999, message = "0-999", groups = {AddGroup.class})
    private Integer ordernum;

    /**
     * 用户类型[0超级管理员，1管理员，2普通用户]
     */
    @ApiModelProperty("用户类型")
    @FlagValidator(value = {0, 1, 2}, message = "用户类型只能为0,1,2", groups = {AddGroup.class})
    private Integer type;

    /**
     * 用户头像地址
     */
    @ApiModelProperty("用户头像地址")
    private String imgpath;

    /**
     * 盐
     */
    @ApiModelProperty("盐")
    private String salt;

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
        SysUser other = (SysUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getLoginname() == null ? other.getLoginname() == null : this.getLoginname().equals(other.getLoginname()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getDeptid() == null ? other.getDeptid() == null : this.getDeptid().equals(other.getDeptid()))
                && (this.getHiredate() == null ? other.getHiredate() == null : this.getHiredate().equals(other.getHiredate()))
                && (this.getMgr() == null ? other.getMgr() == null : this.getMgr().equals(other.getMgr()))
                && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()))
                && (this.getOrdernum() == null ? other.getOrdernum() == null : this.getOrdernum().equals(other.getOrdernum()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getImgpath() == null ? other.getImgpath() == null : this.getImgpath().equals(other.getImgpath()))
                && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLoginname() == null) ? 0 : getLoginname().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDeptid() == null) ? 0 : getDeptid().hashCode());
        result = prime * result + ((getHiredate() == null) ? 0 : getHiredate().hashCode());
        result = prime * result + ((getMgr() == null) ? 0 : getMgr().hashCode());
        result = prime * result + ((getAvailable() == null) ? 0 : getAvailable().hashCode());
        result = prime * result + ((getOrdernum() == null) ? 0 : getOrdernum().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getImgpath() == null) ? 0 : getImgpath().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", loginname=").append(loginname);
        sb.append(", phone=").append(phone);
        sb.append(", pwd=").append(pwd);
        sb.append(", address=").append(address);
        sb.append(", sex=").append(sex);
        sb.append(", remark=").append(remark);
        sb.append(", deptid=").append(deptid);
        sb.append(", hiredate=").append(hiredate);
        sb.append(", mgr=").append(mgr);
        sb.append(", available=").append(available);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", type=").append(type);
        sb.append(", imgpath=").append(imgpath);
        sb.append(", salt=").append(salt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}