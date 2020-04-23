package com.heycm.model.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description ws消息对象
 * @Author heycm@qq.com
 * @Date 2020-03-25 22:17
 */
@Data
public class WsMessage implements Serializable {

    private String messageId; // 消息id
    private Integer messageType; // 消息类型
    private Integer errorCode; // 错误代码
    private String errorMsg; // 错误信息
    private Date date; // 时间
    private String userId; // 发送方id
    private String friendId; // 接收方id
    private String content; // 内容
    private Integer status; // 状态
    private Integer isDelete; // 删除标识

}
