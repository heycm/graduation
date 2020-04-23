package com.heycm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-01-17 22:09
 */
@Data
@ApiModel(value = "User 用户对象")
public class User implements Serializable {

    @ApiModelProperty(value = "用户ID", example = "1")
    private String id;
    @ApiModelProperty(value = "用户名", example = "test")
    private String username;
    @ApiModelProperty(value = "密码", example = "test")
    private String password;

}
