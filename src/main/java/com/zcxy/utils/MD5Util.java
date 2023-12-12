package com.zcxy.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5Util:MD5加密工具类
 */
public class MD5Util {

    /**
     * 返回MD5加密的字符串
     * @param password
     * @return
     */
    public static String MD5Encode(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static void main(String[] args) {
        String encode = MD5Encode("985211");
        System.out.println(encode);
    }

}
