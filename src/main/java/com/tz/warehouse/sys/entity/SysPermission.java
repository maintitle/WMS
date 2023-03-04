package com.tz.warehouse.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_permission
 */
@TableName(value = "sys_permission")
@Data
@ApiModel("权限实体类")
public class SysPermission implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("权限id")
    private Long id;

    /**
     *
     */
    @ApiModelProperty("父级id")
    private Long pid;

    /**
     * 权限类型[menu/permission]
     */
    @ApiModelProperty("权限类型")
    private String type;

    /**
     *
     */
    @ApiModelProperty("菜单名称")
    private String title;

    /**
     * 权限编码[只有type= permission才有  user:view]
     */
    @ApiModelProperty("权限编码")
    private String percode;

    /*
     *
     */
    @ApiModelProperty("菜单简称名称")
    private String name;
    /**
     *
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     *
     */
    @ApiModelProperty("排序")
    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    @ApiModelProperty("状态")
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
        SysPermission other = (SysPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getPercode() == null ? other.getPercode() == null : this.getPercode().equals(other.getPercode()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getOrdernum() == null ? other.getOrdernum() == null : this.getOrdernum().equals(other.getOrdernum()))
                && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPercode() == null) ? 0 : getPercode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getOrdernum() == null) ? 0 : getOrdernum().hashCode());
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
        sb.append(", pid=").append(pid);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", percode=").append(percode);
        sb.append(", name=").append(name);
        sb.append(", icon=").append(icon);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", available=").append(available);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}