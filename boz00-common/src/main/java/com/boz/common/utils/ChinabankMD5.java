package com.boz.common.utils;

import java.security.MessageDigest;

public class ChinabankMD5 {
    public static String md5(String text, String key) throws Exception {
        byte[] bytes = (text + key).getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) < 16) {
                sb.append("0");
            }
            sb.append(Long.toString(bytes[i] & 0xFF, 16));
        }
        return sb.toString().toLowerCase();
    }

    public static String md5(String text, String key, String charset)
            throws Exception {
        byte[] bytes = (text + key).getBytes(charset);

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) < 16) {
                sb.append("0");
            }
            sb.append(Long.toString(bytes[i] & 0xFF, 16));
        }
        return sb.toString().toLowerCase();
    }

    public static String md5(String text, String key, boolean type)
            throws Exception {
        String lowerCase = md5(text, key);
        return type ? lowerCase.toUpperCase() : lowerCase;
    }

    public static String md5(String text, String key, String charset,
                             boolean type) throws Exception {
        String lowerCase = md5(text, key, charset);
        return type ? lowerCase.toUpperCase() : lowerCase;
    }

    public static boolean verify(String text, String key, String md5)
            throws Exception {
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }

    public static boolean verify(String text, String key, String charset,
                                 String md5) throws Exception {
        String md5Text = md5(text, key, charset);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }
}
