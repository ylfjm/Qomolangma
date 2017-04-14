package com.boz.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;


/**
 * web帮助类
 *
 * @author tqc
 */
public class WebUtils {

    /**
     * 生成JSONObject对象
     *
     * @param itemList
     */
    @SuppressWarnings("unchecked")
    public static JSONObject toJsonResultList(List<?> itemList, String[] includeProperties) {
        JSONObject root = new JSONObject();
        JSONArray array = new JSONArray();
        root.put("resultList", array);

        for (int i = 0; i < itemList.size(); i++) {
            JSONObject json = new JSONObject();
            Object obj = itemList.get(i);
            for (String includeProperty : includeProperties) {
                json.put(includeProperty, ReflectionUtil.invokeGetterMethod(obj, includeProperty));
            }
            array.add(json);
        }
        return root;
    }
}
