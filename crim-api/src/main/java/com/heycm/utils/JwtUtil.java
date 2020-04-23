package com.heycm.utils;

import io.jsonwebtoken.*;
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

    // 签名私钥明文
    @Value("${heycm.jwt.privateKey}")
    private String privateKey;
    // 签名失效时间
    @Value("${heycm.jwt.ttl}")
    private Long ttl;

    /**
     * 生成 JWT Token
     * 不要在token内存敏感信息！！
     */
    public String generateJwtToken(String username, Map<String, Object> claims){
        if (claims == null) {
            claims = new HashMap<>();
        }

        // 1.设置失效时间
        long currentMillis = System.currentTimeMillis();
        long expire = currentMillis + ttl * 1000;

        // 2.创建jwtBuilder， 设置claim
        JwtBuilder jwtBuilder = Jwts.builder()
                // 荷载部分的非标准字段/附加字段，一般写在标准的字段之前。
                .setClaims(claims)
                // JWT ID（jti）：荷载部分的标准字段之一，JWT 的唯一性标识，虽不强求，但尽量确保其唯一性。
                .setId(UUID.randomUUID().toString())
                // 签发人（iss）：荷载部分的标准字段之一，代表这个 JWT 的所有者。通常是 username、userid 这样具有用户代表性的内容。
                .setSubject(username)
                // 签发时间（iat）：荷载部分的标准字段之一，代表这个 JWT 的生成时间。
                .setIssuedAt(new Date())
                // 过期时间（exp）：荷载部分的标准字段之一，代表这个 JWT 的有效期。
                .setExpiration(new Date(expire))
                // 设置生成签名的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, privateKey);
        // 3.生成token
        return jwtBuilder.compact();
    }

    /**
     * 生成 JWT Token
     * 不要在token内存敏感信息！！
     */
    public String generateJwtToken(String username){
        return generateJwtToken(username, null);
    }

    /**
     * 解析 JWT Token
     */
    public Claims parseJwtToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException{
        // Claims claims = null;
        // try {
        //     claims = Jwts.parser()
        //             .setSigningKey(privateKey)
        //             .parseClaimsJws(token)
        //             .getBody();
        // } catch (ExpiredJwtException e) {
        //     System.out.println("jwt超时");
        //     // e.printStackTrace();
        // } catch (UnsupportedJwtException e) {
        //     System.out.println("不支持该JWT");
        //     // e.printStackTrace();
        // } catch (MalformedJwtException e) {
        //     System.out.println("JWT格式错误");
        //     // e.printStackTrace();
        // } catch (SignatureException e) {
        //     System.out.println("签名异常");
        //     // e.printStackTrace();
        // } catch (IllegalArgumentException e) {
        //     System.out.println("参数错误异常");
        //     // e.printStackTrace();
        // }
        // return claims;
        return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token).getBody();
    }

    /**
     * 验证合法性
     *   - 解析不抛异常即为合法
     */
    public boolean verify(String token){
        boolean validity = false;

        if (parseJwtToken(token) != null) {
            validity = true;
        }

        return validity;
    }

    /**
     * 获取jwt绑定的用户名
     * @param token jwt
     * @return 用户名
     */
    public String getUsername(String token) {
        return parseJwtToken(token).getSubject();
    }
}
