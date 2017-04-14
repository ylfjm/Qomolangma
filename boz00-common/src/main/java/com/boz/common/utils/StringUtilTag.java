package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 如果传入值为null或空字符串 用于在页面输出默认值"-"
 *
 * @author vinartis
 * @createDate Aug 5, 2013
 */
public class StringUtilTag {

    /**
     * utf-8编码
     */
    private static final String CHARACTER_UTF8 = "utf-8";

    /**
     * 输入默认值
     *
     * @param input
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object defaultVal(Object input) {
        if (input == null) {
            return "-";
        }
        if (input instanceof String && StringUtils.isBlank(input.toString())) {
            return "-";
        }
        if (input instanceof List) {
            List list = (List) input;
            if (list.size() == 0) {
                return "-";
            }
        }
        return input;
    }

    /**
     * 字符串加码
     *
     * @param input
     * @return
     */
    public static String encode(String input, String character) {
        if (StringUtils.isBlank(character)) {
            character = CHARACTER_UTF8;
        }
        if (StringUtils.isNotBlank(input)) {
            try {
                return URLEncoder.encode(input, character);
            } catch (UnsupportedEncodingException e) {
                return input;
            }
        }
        return input;
    }

    /**
     * 文本截取，按照给定的长度截取字符，一个中文占两个英文字符宽度
     *
     * @param input  输入文本
     * @param maxLen 英文字符宽度，最大值,falg -true 是... -false 是
     * @return
     */
    public static Object ellipsis(String input, Integer maxLen, Boolean flag) {
        if (StringUtils.isBlank(input)) {
            return input;
        }

        int len = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isLetter(c)) {
                len = len + 1;
            } else {
                len = len + 2;
            }
            if (len > maxLen) {
                break;
            }
            sb.append(c);
        }
        if (len <= maxLen) {
            return input;
        }
        String result = "";
        if (null != flag && flag == true) {
            result = sb.toString() + "...";
        } else {
            result = sb.toString();
        }

        return result;
    }

    /**
     * 判断字符串是否为纯数字构成
     *
     * @param 要判断的字符
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是不是字母数字或单字节字符
     *
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

}
