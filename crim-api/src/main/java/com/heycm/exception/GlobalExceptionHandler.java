package com.heycm.exception;

import com.heycm.utils.JwtUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 全局异常处理
 * @Author heycm@qq.com
 * @Date 2020-04-01 17:26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private JwtUtil jwtUtil;

    /**-------- 通用异常处理方法 --------**/
    @ExceptionHandler(Exception.class)
    public ResponseMessage error(Exception e, HttpServletRequest request) {
        log.error("[异常][时间:{}][接口:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), e);
        return Result.error("500", "系统出现未知异常！");    // 通用异常结果
    }

    /**-------- 指定异常处理方法 --------**/
    @ExceptionHandler(NullPointerException.class)
    public ResponseMessage error(NullPointerException e, HttpServletRequest request) {
        // e.printStackTrace();
        log.error("[异常][时间:{}][接口:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), e);
        return Result.error("500", "系统出现空指针异常！");
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseMessage error(ArithmeticException e, HttpServletRequest request) {
        log.error("[异常][时间:{}][接口:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), e);
        return Result.error("500", "系统出现算术异常！");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseMessage error(AuthenticationException e, HttpServletRequest request) {
        // 认证失败都不知道是谁认证失败，写日志也是没用
        // log.info("[身份认证][失败][接口:{}][原因:{}][时间:{}][结束]",  request.getAttribute("jetFilter.executeLogin.uri"), e.getMessage(), DateUtil.getStringYMDHMS());
        return Result.error("401", e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseMessage error(UnauthorizedException e, HttpServletRequest request) {

        String username = jwtUtil.getUsername((String) SecurityUtils.getSubject().getPrincipal());

        log.info("[权限认证][失败][用户:{},接口:{}][原因:{}][时间:{}][结束]", username, request.getRequestURI(), e.getMessage(), DateUtil.getStringYMDHMS());
        return Result.error("403", e.getMessage());
    }
}
