package com.heycm.enums;

import com.heycm.utils.enums.BaseEnum;

/**
 * @Description 公共枚举
 * 统一成功状态码：0
 * 失败状态码：[50000, 50199]
 * @Author heycm@qq.com
 * @Date 2020-04-26 19:49
 */
public enum CommEnum implements BaseEnum {

    WS_CONN_SUCCESS("20000", "与WS服务器连接成功"),
    WS_DIS_CONN_SUCCESS("20001", "关闭WS服务器连接成功"),
    WS_SEND_SUCCESS("20002", "发送消息成功"),

    WS_ERROR("50500", "WS服务器出现异常"),
    WS_NOT_ONLINE("505001", "对方未在线"),


    SYS_REDIS_CONNECT_ERROR("500","Redis连接异常"),
    REDIS_SAVE_ERROR("500", "Redis写入缓存异常"),
    REDIS_COMMAND_TIMEOUT_ERROR("500", "Redis操作超时"),
    REDIS_QUERY_TIMEOUT_ERROR("500", "Redis查询超时"),


    PHONE_INVALID("50000", "无效的手机号"),
    SEND_SMS_FAILED("50001", "发送短信验证码失败"),
    SMS_CODE_EXPIRED("50002", "短信验证码过期"),
    SMS_CODE_INVALID("50003", "短信验证码无效"),
    V_CODE_INVALID("50004", "随机验证码无效"),
    AUTH_401_NOT("50005", "未登录"),
    AUTH_401_EXPIRED("50006", "登录过期"),
    AUTH_403("50007", "无权限"),




    SEND_SMS_SUCCESS("0", "发送短信验证码成功"),
    ;


    private String code;
    private String msg;
    private CommEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
