package com.heycm.enums;

import com.heycm.utils.enums.BaseEnum;

/**
 * @Description 公共枚举
 * 成功状态码：[20000, 20199]
 * 失败状态码：[50000, 50199]
 * @Author heycm@qq.com
 * @Date 2020-04-26 19:49
 */
public enum CommEnum implements BaseEnum {

    SYS_REDIS_CONNECT_ERROR("500","Redis连接异常"),
    REDIS_SAVE_ERROR("500", "Redis写入缓存异常"),
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
