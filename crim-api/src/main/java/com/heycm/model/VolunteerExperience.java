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
@TableName("crim_volunteer_experience")
@ApiModel(value="VolunteerExperience对象", description="")
public class VolunteerExperience extends BaseModel<VolunteerExperience> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "志愿服务经历id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "简历id")
    private Integer resumeId;

    @ApiModelProperty(value = "志愿服务项目名称")
    private String projectName;

    @ApiModelProperty(value = "志愿服务总时长")
    private Integer serviceDuration;

    @ApiModelProperty(value = "志愿服务项目开始日期")
    private Date projectStart;

    @ApiModelProperty(value = "志愿服务项目结束日期")
    private Date projectEnd;

    @ApiModelProperty(value = "志愿服务项目描述")
    private String projectDesc;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
