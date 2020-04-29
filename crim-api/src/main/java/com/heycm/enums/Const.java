package com.heycm.enums;

import lombok.Getter;

/**
 * @Description 常量枚举
 * @Author heycm@qq.com
 * @Date 2020-04-27 0:10
 */
@Getter
public enum Const {
    PREFIX_REDIS_SMS_CODE("sms_code_", "Redis中手机验证码key的前缀"),
    PREFIX_REDIS_V_CODE("v_code_", "Redis中随机验证码的key的前缀"),
    ;
    private String value;
    private String desc;
    private Const(String value, String desc){
        this.value = value;
        this.desc = desc;
    }
}
