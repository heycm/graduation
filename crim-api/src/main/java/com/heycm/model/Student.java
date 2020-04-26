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
@TableName("crim_student")
@ApiModel(value="Student对象", description="")
public class Student extends BaseModel<Student> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学生id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "班级id")
    private Integer gradeClassId;

    @ApiModelProperty(value = "学生学号")
    private Integer studentNumber;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "学生性别")
    private String studentSex;

    @ApiModelProperty(value = "学生出生年月")
    private Date studentBirth;

    @ApiModelProperty(value = "学生手机号")
    private String studentPhone;

    @ApiModelProperty(value = "学生邮箱号")
    private String studentEmail;

    @ApiModelProperty(value = "是否允许使用手机号登录：0 是 1 否")
    private Integer allowPhoneLogin;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
