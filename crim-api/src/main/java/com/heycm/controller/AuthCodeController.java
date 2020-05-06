package com.heycm.controller;

import com.heycm.service.IAuthCodeService;
import com.heycm.utils.response.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 验证码控制器
 * @Author heycm@qq.com
 * @Date 2020-04-27 13:30
 */
@RestController
@RequestMapping("/api/v1/authCode")
@Api(tags = "01 - 验证码控制器 AuthCode")
@Slf4j
public class AuthCodeController {

    @Autowired
    public IAuthCodeService authCodeService;

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @return ResponseMessage
     * @throws Exception 短信发送异常
     */
    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
    @PostMapping("/open/getSmsCode")
    public ResponseMessage getSmsCode(String phone) throws Exception {
        return authCodeService.sendSmsCode(phone);
    }

    /**
     * 获取图像验证码
     * @return ResponseMessage
     */
    @ApiOperation(value = "获取随机验证码", notes = "获取随机验证码")
    @GetMapping("/open/getVCode")
    public ResponseMessage getVCode() {
        return authCodeService.getVCode();
    }


}
