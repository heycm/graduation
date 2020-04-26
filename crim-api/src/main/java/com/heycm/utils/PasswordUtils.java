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
        String hashAlgorithmName = "MD5";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        return obj.toString();
    }
}
