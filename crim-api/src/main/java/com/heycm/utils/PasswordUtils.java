package com.heycm.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-26 22:00
 */
public class PasswordUtils {

    private static final String HASH_ALGORITHM_NAME = "MD5";
    private static final int HASH_ITERATIONS = 1024;

    /**
     * 生成盐值
     * @return salt
     */
    public static String generateSalt() {
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * 密码加密
     * @param credentials 密码明文
     * @param salt 盐值
     * @return 密码密文
     */
    public static String encrypt(String credentials, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, credentials, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toString();
    }
}
