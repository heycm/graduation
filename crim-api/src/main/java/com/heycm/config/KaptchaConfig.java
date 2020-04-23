package com.heycm.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Description 验证码配置类
 * @Author heycm@qq.com
 * @Date 2020-03-25 14:50
 */
@Configuration
public class KaptchaConfig {

    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        //验证码字符范围
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
        //图片边框颜色
        properties.setProperty("kaptcha.border.color", "245,248,249");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //图片宽度
        properties.setProperty("kaptcha.image.width", "120");
        //图片高度
        properties.setProperty("kaptcha.image.height", "40");
        //字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
