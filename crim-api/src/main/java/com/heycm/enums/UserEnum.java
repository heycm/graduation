package com.heycm.enums;

import com.heycm.utils.enums.BaseEnum;

/**
 * @Description 用户枚举
 *   统一成功状态码：0
 *   失败状态码：[50200, 50399]
 * @Author heycm@qq.com
 * @Date 2020-04-26 18:11
 */
public enum UserEnum implements BaseEnum {
    REGISTER_SUCCESS("0", "注册成功"),
    LOGOUT_SUCCESS("0", "登出成功"),

    PARAM_EMPTY("50200", "参数不能为空"),
    PHONE_EMPTY("50201", "手机号不能为空"),


    REGISTER_ERROR("50204", "注册失败"),
    USER_EXIST("50205", "用户已存在"),
    SET_USER_ROLE_ERROR("50206", "分配用户角色失败"),
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
