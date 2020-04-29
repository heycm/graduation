package com.heycm.service;

import com.heycm.utils.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 验证码接口
 * @Author heycm@qq.com
 * @Date 2020-04-27 13:38
 */
public interface IAuthCodeService {

    // 发送短信验证码
    ResponseMessage sendSmsCode(String phone) throws Exception;

    // 获取数据验证码
    ResponseMessage getVCode();
}
