package com.boz.common.utils;


import java.math.BigDecimal;

public class DoubleUtil {

    /**
     * 对double数据进行取精度.
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式. roundingMode 枚举
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    /**
     * double 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sum(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }

    public static double sum(double d1, double d2, double d3) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        BigDecimal bd3 = new BigDecimal(Double.toString(d3));
        double val = bd1.add(bd2).add(bd3).doubleValue();

        return val;
    }

    public static double sum(double d1, double d2, double d3, double d4) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        BigDecimal bd3 = new BigDecimal(Double.toString(d3));
        BigDecimal bd4 = new BigDecimal(Double.toString(d4));
        double val = bd1.add(bd2).add(bd3).add(bd4).doubleValue();

        return val;
    }

    /**
     * double 相减
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }

    /**
     * double 乘法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param data
     * @return
     */
    public static Double mul(Double[] data) {
        BigDecimal total = new BigDecimal(1d);
        for (int i = 0; i < data.length; i++) {
            BigDecimal bd = new BigDecimal(data[i]);
            total = total.multiply(bd);
        }

        return total.doubleValue();
    }

    /**
     * double 除法
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入 小数点位数
     * @return
     */
    public static double div(double d1, double d2, int scale) {
        // 当然在此之前，你要判断分母是否为0，
        // 为0你可以根据实际需求做相应的处理
        if (d2 == 0) {
            return 0;
        }

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
