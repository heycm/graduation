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
@TableName("crim_job_vacancy")
@ApiModel(value="JobVacancy对象", description="")
public class JobVacancy extends BaseModel<JobVacancy> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "招聘职位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "职位名称")
    private String jobName;

    @ApiModelProperty(value = "工作地点")
    private String workPlace;

    @ApiModelProperty(value = "正式薪资，如：10-12K")
    private String regularPay;

    @ApiModelProperty(value = "经验要求")
    private String experience;

    @ApiModelProperty(value = "学历要求")
    private String certificate;

    @ApiModelProperty(value = "技能要求，以英文逗号','间隔")
    private String skill;

    @ApiModelProperty(value = "职位描述")
    private String jobDesc;

    @ApiModelProperty(value = "福利待遇")
    private String welfare;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
