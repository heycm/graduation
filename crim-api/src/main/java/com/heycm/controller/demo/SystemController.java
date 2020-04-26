package com.heycm.controller.demo;

import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 系统控制器
 * @Author heycm@qq.com
 * @Date 2020-04-02 13:11
 */
@RestController
@RequestMapping("/api/v1/sys")
public class SystemController {

    /**
     * 未登录/认证失败跳转接口
     * @return ResponseMessage
     */
    @RequestMapping(value = "/401", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseMessage unAuthorized() {
        return Result.error("401","尚未登录，请先登录！");
    }

    /**
     * 无权限跳转接口
     * @return ResponseMessage
     */
    @RequestMapping(value = "/403", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseMessage accessDenied() {
        return Result.error("403","您无此权限！");
    }

    /**
     * 过滤器异常抛出接口
     * @param request HttpServletRequest
     * @throws Exception 过滤器异常
     */
    @RequestMapping(value = "/exception/throw", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public void throwException(HttpServletRequest request) throws Exception {
        String filterException = (String) request.getAttribute("filter.exception");
        if (StringUtils.isNotEmpty(filterException)) {
            Exception exception = (Exception) request.getAttribute(filterException);
            if (exception != null) {
                throw exception;
            }
        }
    }
}
