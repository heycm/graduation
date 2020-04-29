package com.heycm.filter;

import com.heycm.config.CustomConfig;
import com.heycm.config.JwtToken;
import com.heycm.utils.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description jwt 过滤器
 * @Author heycm@qq.com
 * @Date 2020-03-26 22:43
 *
 * 执行顺序： preHandle > isAccessAllowed > isLoginAttempt > executeLogin
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 跨域支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        System.out.println("====>跨域处理...");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 是否允许访问
     * 规则：开放API允许匿名访问，其他不可以
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("====>接口过滤...");
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        System.out.println("====>是否允许访问接口：" + uri);
        PathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/**/open/**", uri);
    }

    /**
     * 是否尝试登陆(认证)
     * 检测header里面是否包含Authorization字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("====>不允许");
        System.out.println("====>是否尝试认证？");
        return true;
    }



    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        System.out.println("====>认证中...");

        HttpServletRequest req = (HttpServletRequest) request;
        // 此 authorization 为请求头中携带的 token
        String authorization = req.getHeader("Authorization");
        // 此 token 为实现了 shiro AuthenticationToken 接口的 token
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行认证，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(token);
            return true;
        } catch (AuthenticationException e) {
            // 过滤器中抛出的异常，全局异常无法处理
            // 所以投机取巧把异常发到控制层再抛出
            req.setAttribute("filter.exception", "jetFilter.executeLogin.exception");
            req.setAttribute("jetFilter.executeLogin.exception", e);
            req.setAttribute("jetFilter.executeLogin.exception.uri", req.getRequestURI());
            req.getRequestDispatcher("/api/v1/sys/exception/throw").forward(request, response);
            return false;
        }
    }
}
