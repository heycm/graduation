package com.heycm.enums;

import com.heycm.utils.enums.BaseEnum;

/**
 * @Description 用户枚举
 *   成功状态码：[20200, 20399]
 *   失败状态码：[50200, 50399]
 * @Author heycm@qq.com
 * @Date 2020-04-26 18:11
 */
public enum UserEnum implements BaseEnum {
    REGISTER_SUCCESS("20200", "注册成功"),

    PARAM_EMPTY("50200", "参数不能为空"),
    SMS_CODE_EXPIRED("50201", "短信验证码过期"),
    SMS_CODE_INVALID("50202", "短信验证码无效"),
    PHONE_INVALID("50203", "手机号码无效"),
    REGISTER_ERROR("50204", "注册失败"),
    USER_EXIST("50205", "用户已存在"),
    V_CODE_INVALID("50206", "图片验证码无效"),
    USER_NOT_EXIST("50207", "账户不存在"),
    PASSWORD_ERROR("50208", "密码错误"),

    ;






    private String code;
    private String msg;
    private UserEnum(String code, String msg) {
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
