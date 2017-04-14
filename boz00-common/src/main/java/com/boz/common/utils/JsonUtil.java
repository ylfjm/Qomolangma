//package com.boz.common.utils;
//
//import com.alibaba.fastjson.JSON;
//
//public class JsonUtil {
//
//    /**
//     * 将对象转换为JSON字符串
//     *
//     * @param obj
//     * @return
//     */
//    public static String toJson(Object obj) {
//        try {
//            return JSON.toJSONString(obj, true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 将JSON字符串转换为对象
//     *
//     * @param json
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public static <T> T toObject(String json, Class<T> clazz) {
//        T result = null;
//        try {
//            result = (T) JSON.parseObject(json, clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}