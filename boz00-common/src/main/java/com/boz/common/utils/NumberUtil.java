package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 数组工具类
 *
 * @version 1.0
 * @creator zhangqiang
 * @create-time Aug 17, 2015   4:52:32 PM
 */
public class NumberUtil {

    /**
     * 将string类型字符串数组转换成Long类型
     *
     * @param ids
     * @return
     */
    public static Long[] convertStringArrayToLongArray(String[] ids) {
        List<Long> convertList = new ArrayList<Long>();
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNotBlank(ids[i])) {
                convertList.add(Long.parseLong(ids[i]));

            }
        }
        return convertList.toArray(new Long[convertList.size()]);
    }

    /**
     * 将string类型字符串数组转换成Integer类型
     *
     * @param ids
     * @return
     */
    public static Integer[] convertStringArrayToIntergeArray(String[] ids) {
        Integer[] convert = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNotBlank(ids[i])) {
                convert[i] = Integer.parseInt(ids[i]);
            }
        }

        return convert;
    }


    /**
     * 将string类型字符串数组转换成Integer类型
     *
     * @param ids
     * @return
     */
    public static Double[] convertStringArrayToDoubleArray(String[] ids) {
        Double[] convert = new Double[ids.length];
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNotBlank(ids[i])) {
                convert[i] = Double.valueOf(ids[i]);
            }
        }

        return convert;
    }

    /**
     * 比较是否相等
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean equals(Long num1, Long num2) {
        if (null != num1 && null != num2 && num1.longValue() == num2.longValue()) {
            return true;
        }

        return false;
    }

    /**
     * 比较是否相等
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean doubleEquals(Double num1, Double num2) {
        if (null != num1 && null != num2 && num1.doubleValue() == num2.doubleValue()) {
            return true;
        }

        return false;
    }
}
