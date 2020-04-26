package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("crim_interview")
@ApiModel(value="Interview对象", description="")
public class Interview extends BaseModel<Interview> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "面试记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业id")
    private Integer companyId;

    @ApiModelProperty(value = "宣讲会id")
    private Integer careerTalkId;

    @ApiModelProperty(value = "职位id")
    private Integer jobVacancyId;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "最终薪资：如 10-12K")
    private String regularPay;

    @ApiModelProperty(value = "最终福利")
    private String welfare;

    @ApiModelProperty(value = "是否最终选择：0 否 1 是")
    private Integer isLastChoice;

    @ApiModelProperty(value = "状态：0 未开始 1 待回复 2 已通过 3 被拒绝")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
