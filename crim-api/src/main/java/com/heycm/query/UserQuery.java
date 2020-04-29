package com.heycm.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Data
@ApiModel(value="UserQuery对象", description="入参对象")
public class UserQuery {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码明文")
    private String password;

    @ApiModelProperty("图片验证码")
    private String vcode;

    @ApiModelProperty("短信验证码")
    private String smsCode;
}
