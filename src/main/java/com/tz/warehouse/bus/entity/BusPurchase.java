package com.tz.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tz.warehouse.sys.common.valid.AddGroup;
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
 * 采购单
 * @TableName bus_purchase
 */
@TableName(value ="bus_purchase")
@Data
@ApiModel("采购单实体类")
public class BusPurchase implements Serializable {
    /**
     * 采购单id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("采购单id")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Long id;

    /**
     * 采购人id
     */
    @ApiModelProperty("采购人id")
    private Long assigneeId;

    /**
     * 采购人名
     */
    @ApiModelProperty("采购人名")
    private String assigneeName;

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    private String phone;

    /**
     * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
     */
    @ApiModelProperty("状态[0新建，1已分配，已领取，3已完成，4有异常]")
    @FlagValidator(value = {0,1,2,3,4},message = "参数只能为[0新建，1已分配，已领取，3已完成，4有异常]",groups = {AddGroup.class, UpdateGroup.class})
    private Integer status;

    /**
     * 总金额
     */
    @ApiModelProperty("总金额")
    private BigDecimal amount;

    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
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
        BusPurchase other = (BusPurchase) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAssigneeId() == null ? other.getAssigneeId() == null : this.getAssigneeId().equals(other.getAssigneeId()))
            && (this.getAssigneeName() == null ? other.getAssigneeName() == null : this.getAssigneeName().equals(other.getAssigneeName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAssigneeId() == null) ? 0 : getAssigneeId().hashCode());
        result = prime * result + ((getAssigneeName() == null) ? 0 : getAssigneeName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
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
        sb.append(", assigneeId=").append(assigneeId);
        sb.append(", assigneeName=").append(assigneeName);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", amount=").append(amount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}