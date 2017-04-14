package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckCardUtils {

    private static int[] cardBins = new int[0];

    static {
        try {
            //读取银行卡bin列表
            BufferedReader br = new BufferedReader(new InputStreamReader(CheckCardUtils.class.getResourceAsStream("/cardbins.txt")));
            List<Integer> values = new ArrayList<Integer>();
            String line = "";
            while ((line = br.readLine()) != null) {
                if (tryParseInt(line)) {
                    values.add(Integer.parseInt(line));
                }
            }
            cardBins = new int[values.size()];
            for (int i = 0; i < values.size(); i++) {
                cardBins[i] = values.get(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //根据输入号码判断是否是银行卡
    public static boolean isCashcardInvaild(String cardno) {
        try {
            // 利用StringUtils.isNumeric
            if (StringUtils.isBlank(cardno) || !StringUtils.isNumeric(cardno) || cardno.length() < 12 || cardno.length() > 50) {
                return false;
            }
            int head3bit = Integer.parseInt(cardno.substring(0, 3));// 截取银行卡前3位
            int head4bit = Integer.parseInt(cardno.substring(0, 4));// 截取银行卡前4位
            int head5bit = Integer.parseInt(cardno.substring(0, 5));// 截取银行卡前5位
            int head6bit = Integer.parseInt(cardno.substring(0, 6));// 截取银行卡前6位
            // 检验前6位
            if (Arrays.binarySearch(cardBins, head6bit) >= 0) {
                return true;
            }
            // 检验前5位
            if (Arrays.binarySearch(cardBins, head5bit) >= 0) {
                return true;
            }
            // 检验前4位
            if (Arrays.binarySearch(cardBins, head4bit) >= 0) {
                return true;
            }
            // 检验前3位: 只有一种可能是 888 所以直接比较
            if (head3bit == 888) {
                return true;
            }
        } catch (Exception ex) {
            return true;
        }
        return false;
    }


    //尝试从字符串解析
    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isCashcardInvaild("4392268338458675"));

    }

}
