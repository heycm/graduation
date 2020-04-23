package com.heycm.config;

// import com.heycm.model.jwt.JwtToken;
import com.heycm.model.jwt.JwtToken;
import com.heycm.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-03-24 17:50
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 自定义realm名称
     */
    public void setName(String name) {
        super.setName("userRealm");
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权。。。");

        // 从认证方法里过来的安全数据
        Object principal = principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("vip");

        return info;
    }


    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行了认证。。。");


        // 单shiro的写法
        // 根据登录用户名去数据库取用户信息
        String name = "admin";
        String pwd = "13a9986eb28bf942ef12afc35a27d55c";
        String name1 = "dafenqi";
        String pwd1 = "5e900e3c7194394a073aaf3ef9816e9f";

        // 盐值
        ByteSource salt = ByteSource.Util.bytes("116c00679abf5a769213d791e7f4ddb8");
        ByteSource salt1 = ByteSource.Util.bytes("33d38d1e415e76781a9608465eb6e073");


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 用户名验证
        if (!name.equals(token.getUsername()) && !name1.equals(token.getUsername())) {
            return null;
        }
        if (name.equals(token.getUsername())) {
            // 密码验证， shiro做这件事
            // principal其实就是登陆用户对象user，从这里传到授权方法里
            HashMap<String, Object> map = new HashMap<>();
            map.put("username", name);
            map.put("pwd", pwd);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(map, pwd, salt, getName());

            return info;
        } else {
            // 密码验证， shiro做这件事
            // principal其实就是登陆用户对象user，从这里传到授权方法里
            HashMap<String, Object> map = new HashMap<>();
            map.put("username", name1);
            map.put("pwd", pwd1);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(map, pwd1, salt1, getName());

            return info;
        }
    }

    public static void main(String[] args) {

        // 生成盐值，注册时保存到数据库
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println(salt);

        // 生成密码密文
        String hashAlgorithmName = "MD5";
        String credentials = "dafenqi";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj.toString());
    }
}
