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
@TableName("crim_resume")
@ApiModel(value="Resume对象", description="")
public class Resume extends BaseModel<Resume> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "简历id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "求职者姓名")
    private String name;

    @ApiModelProperty(value = "性别：0 男 1 女")
    private Integer sex;

    @ApiModelProperty(value = "出生年月")
    private Date brith;

    @ApiModelProperty(value = "届别，如：2020")
    private Integer classLevel;

    @ApiModelProperty(value = "求职状态：0 离职-随时到岗 1 在职-暂不考虑 2 在职-考虑机会 3 在职-月内到岗 ")
    private Integer jobStatus;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String weixin;

    @ApiModelProperty(value = "邮箱号")
    private String email;

    @ApiModelProperty(value = "证件照id")
    private Integer photoId;

    @ApiModelProperty(value = "个人优势")
    private String advantage;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
