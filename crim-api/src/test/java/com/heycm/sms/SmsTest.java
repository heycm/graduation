package com.heycm.sms;

import com.heycm.CrimApiApplication;
import com.heycm.enums.UserEnum;
import com.heycm.utils.ZhenziSmsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-03-25 21:05
 */
@SpringBootTest(classes = CrimApiApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration // Junit模拟ServletContext
public class SmsTest {
    @Autowired
    private ZhenziSmsUtil zhenziSmsUtil;

    @Test
    public void sms() throws Exception {
        // System.out.println(zhenziSmsUtil.balance());

        System.out.println(zhenziSmsUtil.sendCodeSms("13737169260", "167039"));


    }

    @Test
    public void m1() {
        System.out.println(UserEnum.REGISTER_SUCCESS);
        System.out.println(UserEnum.REGISTER_SUCCESS.getCode());
        System.out.println(UserEnum.REGISTER_SUCCESS.getMsg());
    }
}
