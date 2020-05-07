package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.heycm.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("crim_education_experience")
@ApiModel(value="EducationExperience对象", description="")
public class EducationExperience extends BaseModel<EducationExperience> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "教育经历id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "简历id")
    private Integer resumeId;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "学位：0 初中及以下 1 中专/中技 2 高中 3 大专 4 本科 5 硕士 6 博士")
    private Integer certificate;

    @ApiModelProperty(value = "主修专业")
    private String major;

    @ApiModelProperty(value = "开始日期")
    private Date studyStart;

    @ApiModelProperty(value = "结束日期")
    private Date studyEnd;

    @ApiModelProperty(value = "在校经历")
    private String schoolExperience;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
