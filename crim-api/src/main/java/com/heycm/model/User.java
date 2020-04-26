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
@TableName("crim_user")
@ApiModel(value="User对象", description="")
public class User extends BaseModel<User> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id", example = "")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名（工号/学号/手机号）")
    private String username;

    @ApiModelProperty(value = "加密密码")
    private String password;

    @ApiModelProperty(value = "加密盐值")
    private String salt;

    @ApiModelProperty(value = "用户类型：0 学校 1 学生 2 企业", example = "")
    private Integer type;

    @ApiModelProperty(value = "父账户id", example = "")
    private Integer pid;

    @ApiModelProperty(value = "账户状态：0 正常 1 审核 2 禁用", example = "0")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
