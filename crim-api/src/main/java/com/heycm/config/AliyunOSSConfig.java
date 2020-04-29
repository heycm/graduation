package com.heycm.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-29 0:39
 */
@Configuration
public class AliyunOSSConfig {

    @Resource
    CustomConfig customConfig;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(
                customConfig.getOssEndpoint(),
                customConfig.getOssAccessKeyID(),
                customConfig.getOssAccessKeySecret()
        );
    }

}
