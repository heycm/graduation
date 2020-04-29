package com.heycm.utils;

import com.alibaba.fastjson.JSON;
import com.heycm.config.CustomConfig;
import com.heycm.model.sms.ZhenziSmsResult;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 榛子云短信工具类
 * @Author heycm@qq.com
 * @Date 2020-01-20 16:43
 */

@Component
public class ZhenziSmsUtil {

    @Resource
    private CustomConfig customConfig;

    /**
     * 发送短信验证码
     *
     * @param number 手机号
     * @param code   验证码
     * @return 发送短信验证码结果
     * @throws Exception
     */
    public ZhenziSmsResult sendCodeSms(String number, String code) throws Exception {
        ZhenziSmsClient client = new ZhenziSmsClient(customConfig.getSmsApiUrl(), customConfig.getSmsAppId(), customConfig.getSmsAppSecret());
        Map<String, String> params = new HashMap<>();
        params.put("number", number);
        params.put("message", "您的验证码为：" + code + "。请不要把验证码泄露给其他人，" + customConfig.getSmsValidTime() + "分钟内有效！");
        String result = client.send(params);
        ZhenziSmsResult zhenziSmsResult = JSON.parseObject(result, ZhenziSmsResult.class);
        return zhenziSmsResult;
    }

    /**
     * 查询短信余额
     *
     * @return 查询结果
     * @throws Exception
     */
    public ZhenziSmsResult balance() throws Exception {
        ZhenziSmsClient client = new ZhenziSmsClient(customConfig.getSmsApiUrl(), customConfig.getSmsAppId(), customConfig.getSmsAppSecret());
        String result = client.balance();
        ZhenziSmsResult zhenziSmsResult = JSON.parseObject(result, ZhenziSmsResult.class);
        return zhenziSmsResult;
    }

}
