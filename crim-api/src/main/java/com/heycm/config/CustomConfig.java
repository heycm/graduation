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
@Data
public class CustomConfig {

    // 系统开放API
    @Value("${heycm.system.openApi}")
    private String sysOpenApi;

    // jwt签名私钥
    @Value("${heycm.jwt.privateKey}")
    private String jwtPrivateKey;

    // jwt过期时间（秒）
    @Value("${heycm.jwt.ttl}")
    private Long jwtTtl;

    @Value("${heycm.shiro.rememberMeMaxAge}")
    private int shiroRememberMeMaxAge;

    @Value("${heycm.shiro.rememberMeCipherKey}")
    private String shiroRememberMeCipherKey;

    // 榛子云短信API
    @Value("${heycm.zhenziSms.apiUrl}")
    private String smsApiUrl;

    // 榛子云短信APPID
    @Value("${heycm.zhenziSms.appId}")
    private String smsAppId;

    // 榛子云短信秘钥
    @Value("${heycm.zhenziSms.appSecret}")
    private String smsAppSecret;

    // 榛子云短信有效时间（分钟）
    @Value("${heycm.zhenziSms.validTime}")
    private int smsValidTime;

    @Value("${heycm.aliyun.oss.endPoint}")
    private String ossEndpoint;

    @Value("${heycm.aliyun.oss.accessKeyID}")
    private String ossAccessKeyID;

    @Value("${heycm.aliyun.oss.accessKeySecret}")
    private String ossAccessKeySecret;
    @Value("${heycm.aliyun.oss.imgBucketName}")
    private String ossImgBucketName;

    @Value("${heycm.aliyun.oss.imgHost}")
    private String ossImgHost;

    @Value("${heycm.aliyun.oss.imgDir}")
    private String ossImgDir;

    @Value("${heycm.aliyun.oss.fileBucketName}")
    private String ossFileBucketName;

    @Value("${heycm.aliyun.oss.fileDir}")
    private String ossFileDir;

}
