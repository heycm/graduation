package com.heycm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-26 22:45
 */
public class Tools {

    /**
     * 验证手机号
     * @param phone 手机号
     * @return boolean
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^[1][3-9][0-9]{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

}
