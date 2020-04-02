package com.remo.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    private static final String defaultPassword = "default";

    public static String encrypt(String password) {
        DigestUtils.md5Digest(password.getBytes());
        String encodePassword = DigestUtils.md5DigestAsHex(password.getBytes());
        return encodePassword;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin"));
    }

}
