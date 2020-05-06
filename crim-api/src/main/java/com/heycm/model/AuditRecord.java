package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.heycm.model.base.BaseModel;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("crim_audit_record")
@ApiModel(value="AuditRecord对象", description="")
public class AuditRecord extends BaseModel<AuditRecord> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "审核记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "审核类型：0 注册审核 1 参与招聘会审核")
    private Integer auditType;

    @ApiModelProperty(value = "招聘会id")
    private Integer jobFairId;

    @ApiModelProperty(value = "校区id")
    private Integer campusId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "申请审核时间")
    private Date applyTime;

    @ApiModelProperty(value = "审核结果：0 通过 1 拒绝")
    private Integer auditRes;

    @ApiModelProperty(value = "审核反馈意见")
    private String auditFeedback;

    @ApiModelProperty(value = "审核人id")
    private Integer auditUser;

    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    @ApiModelProperty(value = "状态：0 已审核 1 待审核")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
