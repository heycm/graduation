package com.heycm.service.impl;

import com.alibaba.fastjson.JSON;
import com.heycm.config.CustomConfig;
import com.heycm.enums.CommEnum;
import com.heycm.enums.Const;
import com.heycm.enums.UserEnum;
import com.heycm.model.sms.ZhenziSmsResult;
import com.heycm.service.IAuthCodeService;
import com.heycm.utils.RedisUtil;
import com.heycm.utils.Tools;
import com.heycm.utils.ZhenziSmsUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.random.RandomUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 验证码接口服务类
 * @Author heycm@qq.com
 * @Date 2020-04-27 13:38
 */
@Service
@Slf4j
public class AuthCodeServiceImpl implements IAuthCodeService {

    @Resource
    CustomConfig customConfig;

    @Resource
    RedisUtil redisUtil;

    @Resource
    ZhenziSmsUtil zhenziSmsUtil;

    /**
     * 获取短信验证码，并保存到redis
     * @param phone 手机号
     * @return ResponseMessage
     * @throws Exception 短信发送异常
     */
    @Override
    public ResponseMessage sendSmsCode(String phone) throws Exception {
        // 1.校验手机号
        if (StringUtils.isEmpty(phone)){
            return Result.error(UserEnum.PHONE_EMPTY);
        }
        if (!Tools.checkPhone(phone)) {
            return Result.error(CommEnum.PHONE_INVALID);
        }
        // 2.生成6位数字验证码
        String code = RandomUtil.generate6Code().toString();
        // 3.发送短信
        ZhenziSmsResult smsResult = zhenziSmsUtil.sendCodeSms(phone, code);
        if (smsResult.getCode() != 0) {
            log.info("[发送短信验证码][失败][时间:{}][失败信息:{}][结束]", DateUtil.getStringYMDHMS(), JSON.toJSONString(smsResult));
            return Result.error(CommEnum.SEND_SMS_FAILED);
        }
        // 4.验证码存入Redis，并设置失效时间
        redisUtil.set(Const.PREFIX_REDIS_SMS_CODE.getValue() + phone, code, customConfig.getSmsValidTime());
        // 5.返回结果
        return Result.ok(CommEnum.SEND_SMS_SUCCESS);
    }

    /**
     * 获取随机验证码，并保存到redis
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage getVCode() {
        String randomString = RandomUtil.generateRandomString(6);
        redisUtil.set(Const.PREFIX_REDIS_V_CODE.getValue() + randomString.toLowerCase(), randomString.toLowerCase(), 10L);
        return Result.ok(randomString);
    }
}
