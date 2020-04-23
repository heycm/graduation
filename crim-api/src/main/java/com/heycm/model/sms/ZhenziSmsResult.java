package com.heycm.model.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 榛子云短信接口返回对象
 * @Author heycm@qq.com
 * @Date 2020-01-20 17:21
 */

@Data
@ApiModel(value = "ZhenziSmsResult 榛子云短信接口返回对象")
public class ZhenziSmsResult implements Serializable {

    @ApiModelProperty(value = "状态码", example = "0")
    private Integer code;
    @ApiModelProperty(value = "数据", example = "发送成功")
    private String data;

}
