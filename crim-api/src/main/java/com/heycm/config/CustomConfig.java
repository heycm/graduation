package com.heycm.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description z
 * @Author heycm@qq.com
 * @Date 2020-04-27 1:07
 */
@Component
@PropertySource("classpath:custom-config.yml")
@Data
public class CustomConfig {

    @Value("${heycm.system.openApi}")
    private String sysOpenApi;

    @Value("${heycm.jwt.privateKey}")
    private String jwtPrivateKey;

    // jwt过期时间（秒）
    @Value("${heycm.jwt.ttl}")
    private Long jwtTtl;

    @Value("${heycm.shiro.rememberMeMaxAge}")
    private int shiroRememberMeMaxAge;

    @Value("${heycm.shiro.rememberMeCipherKey}")
    private String shiroRememberMeCipherKey;

    @Value("${heycm.zhenziSms.apiUrl}")
    private String smsApiUrl;

    @Value("${heycm.zhenziSms.appId}")
    private String smsAppId;

    @Value("${heycm.zhenziSms.appSecret}")
    private String smsAppSecret;

    @Value("${heycm.zhenziSms.validTime}")
    private int smsValidTime;

}
