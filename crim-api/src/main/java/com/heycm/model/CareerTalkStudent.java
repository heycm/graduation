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
@TableName("crim_career_talk_student")
@ApiModel(value="CareerTalkStudent对象", description="")
public class CareerTalkStudent extends BaseModel<CareerTalkStudent> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学生参加宣讲会记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "宣讲会id")
    private Integer careerTalkId;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
