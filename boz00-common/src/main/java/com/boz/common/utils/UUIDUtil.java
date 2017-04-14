package com.boz.common.utils;

import java.util.UUID;

/**
 * UUID 工具类
 */
public class UUIDUtil {

    //private static final String randomChars = "abcdefghijklmnopqrstuvwxyz1234567890";

    /**
     * 生成uuid 去除"-"
     *
     * @return
     */
    public static String uuid() {
        //String uuid = RandomStringUtils.random(32,randomChars);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.uuid());
    }
}
