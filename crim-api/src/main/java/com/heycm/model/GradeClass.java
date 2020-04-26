package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.heycm.model.base.BaseModel;
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
@TableName("crim_grade_class")
@ApiModel(value="GradeClass对象", description="")
public class GradeClass extends BaseModel<GradeClass> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "班级id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "专业id")
    private Integer professionId;

    @ApiModelProperty(value = "年级id：type表year_level子节点id")
    private Integer yearLevelId;

    @ApiModelProperty(value = "班级名称")
    private String gradeClassName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
