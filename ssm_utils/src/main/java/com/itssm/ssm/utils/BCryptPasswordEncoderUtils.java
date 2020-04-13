package com.itssm.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//对密码加密的工具类
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
    return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="ssm";
        String pwd = encodePassword(password);
        System.out.println(pwd);

    }
}
