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
@TableName("crim_internship_experience")
@ApiModel(value="InternshipExperience对象", description="")
public class InternshipExperience extends BaseModel<InternshipExperience> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "实习经历id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "简历id")
    private Integer resumeId;

    @ApiModelProperty(value = "实习公司名称")
    private String companyName;

    @ApiModelProperty(value = "行业")
    private String companyTrade;

    @ApiModelProperty(value = "实习所在部门")
    private String affiliatedDept;

    @ApiModelProperty(value = "实习职位名称")
    private String positionName;

    @ApiModelProperty(value = "实习开始日期")
    private Date jobStart;

    @ApiModelProperty(value = "实习结束日期")
    private Date jobEnd;

    @ApiModelProperty(value = "实习工作内容")
    private String workContent;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
