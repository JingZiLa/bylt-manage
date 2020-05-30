package com.mirror.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 明文加密工具类
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="user";
        String pwd = encodePassword(password);
        System.out.print(pwd);
    }
}
