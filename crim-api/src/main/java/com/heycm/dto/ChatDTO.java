package com.heycm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-08 23:45
 */
@Data
public class ChatDTO {

    private Integer id;
    private Integer type;
    private Integer fromId;
    private Integer toId;
    private String content;
    private Integer errCode;
    private String errMsg;
    private Date sendTime;
    private Integer status;

    private String fromName;
    private String fromPhotoUrl;
    private String toName;
    private String toPhotoUrl;
}
