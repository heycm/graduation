package com.heycm.config;

import com.alibaba.fastjson.JSON;
import com.heycm.model.jwt.JwtToken;
import com.heycm.utils.JwtUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 专门用于验证 jwtToken 的 realm
 * @Author heycm@qq.com
 * @Date 2020-03-27 15:56
 */
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 自定义realm名称
     */
    public void setName(String name) {
        super.setName("jwtRealm");
    }

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken（支持自定义 AuthenticationToken）
     * 不负责验证其他的token（UsernamePasswordToken）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("JwtRealm === 授权...");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        info.addRole("vip");

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("JwtRealm === 认证...");

        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();

        if (StringUtils.isEmpty(token)) {
            throw new NullPointerException("token is null");
        }

        Claims claims = null;
        try {
            claims = jwtUtil.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT超时");
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("不支持该JWT");
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT格式错误");
        } catch (SignatureException e) {
            throw new AuthenticationException("签名异常");
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT参数错误");
        }
        // System.out.println(JSON.toJSONString(claims));
        if (claims == null) {
            throw new AuthenticationException("JWT无效");
        }
        String username = claims.getSubject();
        if (StringUtils.isEmpty(username)) {
            throw new AuthenticationException("JWT无效");
        }
        // TODO 去数据库查此用户，最好是登录时把登录信息和对应token存到redis，减小mysql压力
        String userInfo = "此用户信息";
        if (userInfo == null){
            throw new AuthenticationException("用户不存在");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
