package com.heycm.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description JwtToken，对应 Shiro 用户名和密码的载体
 * @Author heycm@qq.com
 * @Date 2020-03-26 22:39
 */
// 类似 UsernamePasswordToken
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    // 类似用户名
    @Override
    public Object getPrincipal() {
        return token;
    }

    // 类似密码
    @Override
    public Object getCredentials() {
        return token;
    }
}
