package com.heycm.exception;

import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import com.heycm.enums.CommEnum;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import io.lettuce.core.RedisCommandTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    public ResponseMessage error(Exception e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(500);
        log.error("[异常][时间:{}][接口:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), e);
        return Result.error("500", "系统繁忙，请稍后再试！");    // 通用异常结果
    }

    /**-------- 指定异常处理方法 --------**/
    /**
     * token 认证
     * 未登录：无token
     * 登录过期：token过期/错误/异常/无效
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseMessage error(AuthenticationException e, HttpServletResponse response) {
        response.setStatus(401);
        if (CommEnum.AUTH_401_NOT.getMsg().equals(e.getMessage())){
            return Result.error(CommEnum.AUTH_401_NOT);
        }
        return Result.error(CommEnum.AUTH_401_EXPIRED);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseMessage error(MaxUploadSizeExceededException e) {
        return Result.error("单文件最大10MB");
    }

    /**
     * 权限认证
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseMessage error(UnauthorizedException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(403);
        Integer userId = jwtUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
        log.info("[权限认证][时间:{}][失败][用户ID:{},接口:{}][原因:{}][结束]", DateUtil.getStringYMDHMS(), userId, request.getRequestURI(), e.getMessage());
        return Result.error(CommEnum.AUTH_403);
    }

    /**
     * Redis异常
     */
    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseMessage error(RedisConnectionFailureException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(500);
        log.error("[异常][时间:{}][接口:{}][异常信息:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), CommEnum.SYS_REDIS_CONNECT_ERROR.getMsg(), e);
        return Result.error(CommEnum.SYS_REDIS_CONNECT_ERROR);
    }

    @ExceptionHandler(RedisCommandTimeoutException.class)
    public ResponseMessage error(RedisCommandTimeoutException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(500);
        log.error("[异常][时间:{}][接口:{}][异常信息:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), CommEnum.SYS_REDIS_CONNECT_ERROR.getMsg(), e);
        return Result.error(CommEnum.REDIS_COMMAND_TIMEOUT_ERROR);
    }

    @ExceptionHandler(QueryTimeoutException.class)
    public ResponseMessage error(QueryTimeoutException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(500);
        log.error("[异常][时间:{}][接口:{}][异常信息:{}][结束]", DateUtil.getStringYMDHMS(), request.getRequestURI(), CommEnum.SYS_REDIS_CONNECT_ERROR.getMsg(), e);
        return Result.error(CommEnum.REDIS_QUERY_TIMEOUT_ERROR);
    }

    /**
     * Excel导入异常
     */
    @ExceptionHandler(ExcelImportException.class)
    public ResponseMessage error(ExcelImportException e) {
        return Result.error(e.getMessage());
    }

}
