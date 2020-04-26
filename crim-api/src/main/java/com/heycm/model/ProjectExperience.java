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
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("crim_project_experience")
@ApiModel(value="ProjectExperience对象", description="")
public class ProjectExperience extends BaseModel<ProjectExperience> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "项目经历id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "简历id")
    private Integer resumeId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目角色")
    private String projectRole;

    @ApiModelProperty(value = "项目链接")
    private String projectLink;

    @ApiModelProperty(value = "项目开始时间")
    private Date projectStart;

    @ApiModelProperty(value = "项目结束时间")
    private Date projectEnd;

    @ApiModelProperty(value = "项目描述")
    private String projectDesc;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
