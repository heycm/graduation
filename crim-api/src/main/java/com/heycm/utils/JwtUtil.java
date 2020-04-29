package com.heycm.utils;

import com.heycm.config.CustomConfig;
import io.jsonwebtoken.*;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-03-24 23:02
 */
@Component
public class JwtUtil {

    @Autowired
    private CustomConfig customConfig;

    /**
     * 生成 JWT Token
     * 不要在token内存敏感信息！！
     */
    public String generateJwtToken(Integer userId, Integer userType, Map<String, Object> claims){
        if (claims == null) {
            claims = new HashMap<>();
        }
        // 1.设置失效时间
        long currentMillis = System.currentTimeMillis();
        long expire = currentMillis + customConfig.getJwtTtl() * 1000;
        // 2.创建jwtBuilder， 设置claim
        JwtBuilder jwtBuilder = Jwts.builder()
                // 荷载部分的非标准字段/附加字段，一般写在标准的字段之前。
                .setClaims(claims)
                // JWT ID（jti）：荷载部分的标准字段之一，JWT 的唯一性标识，虽不强求，但尽量确保其唯一性。
                .setId(userId.toString())
                // 签发人（iss）：荷载部分的标准字段之一，代表这个 JWT 的所有者。通常是 username、userid 这样具有用户代表性的内容。
                .setSubject(userType.toString())
                // 签发时间（iat）：荷载部分的标准字段之一，代表这个 JWT 的生成时间。
                .setIssuedAt(new Date())
                // 过期时间（exp）：荷载部分的标准字段之一，代表这个 JWT 的有效期。
                .setExpiration(new Date(expire))
                // 设置生成签名的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, customConfig.getJwtPrivateKey());
        // 3.生成token
        return jwtBuilder.compact();
    }

    /**
     * 生成 JWT Token
     */
    public String generateJwtToken(Integer userId, Integer userType){
        return generateJwtToken(userId, userType, null);
    }

    /**
     * 解析 JWT Token
     */
    public Claims parseJwtToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException{
        return Jwts.parser().setSigningKey(customConfig.getJwtPrivateKey()).parseClaimsJws(token).getBody();
    }

    /**
     * 验证合法性
     *   - 解析不抛异常即为合法
     */
    public boolean verify(String token){
        if (parseJwtToken(token) != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取jwt绑定的用户名
     * @param token jwt
     * @return 用户名
     */
    public Integer getUserId(String token) {
        return Integer.valueOf(parseJwtToken(token).getId());
    }

    /**
     * 获取jwt绑定的用户名
     * @param token jwt
     * @return 用户名
     */
    public Integer getUserType(String token) {
        return Integer.valueOf(parseJwtToken(token).getSubject());
    }
}
