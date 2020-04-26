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
@TableName("crim_job_fair")
@ApiModel(value="JobFair对象", description="")
public class JobFair extends BaseModel<JobFair> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "招聘会id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学校id")
    private Integer schoolId;

    @ApiModelProperty(value = "年度id：type表year_level子节点id")
    private Integer yearLevelId;

    @ApiModelProperty(value = "季度：0 秋季 1 春季")
    private Integer quarter;

    @ApiModelProperty(value = "招聘会标题")
    private String jobFairTitle;

    @ApiModelProperty(value = "招聘会内容")
    private String jobFairContent;

    @ApiModelProperty(value = "开始日期")
    private Date startTime;

    @ApiModelProperty(value = "结束日期")
    private Date endTime;

    @ApiModelProperty(value = "状态：0 保存 1 已发布 -1 已过期")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
