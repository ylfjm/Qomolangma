package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataArrayUtils {

    public static void main(String[] args) {
        /*String[] arr1 = { "sdfsdfsdfdsgsdfgsdfgsdfgsdfgsdfgsdf", "dfgasdSFDASDAQ4W523TFASDFSDF" };
		String[] arr2 = { "sdfasdfasdfsdfsdfsdfsd","dfdfsaqwtqwetwee" };
		String[] arr3 = { "0" };
		String[] arr4 = { "0" };
		String[] arr5 = { "0" };
		String[] arr6 = { "0" };
		String[] arr7 = { "0" };
		String[] arr8 = { "0" };
		String[] arr9 = { "0" };
		String[] arr10 = { "0" };
		String[] arr11 = { "0" };
		String[] arr12 = { "0" };
		String[] arr13 = { "0" };
		String[] arr14 = { "0" };
		String[] arr15 = { "0" };
		String[] arr16 = { "0" };
		List<String[]> list = new ArrayList<String[]>();
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		list.add(arr4);
		list.add(arr5);
		list.add(arr6);
		list.add(arr7);
		list.add(arr8);
		list.add(arr9);
		list.add(arr10);
		list.add(arr11);
		list.add(arr12);
		list.add(arr13);
		list.add(arr14);
		list.add(arr15);
		list.add(arr16);
		List<List<String>> splitContentArr = splitContentArr(list,arr1,"");
		System.out.println(JacksonUtil.toJson(splitContentArr));*/


        String par = "1,3,4";
        System.out.println(par.contains("2,3"));
    }

    public static void dataArray(List<String[]> list, String[] arr, String str, List<String> result) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            // 取得当前的数组
            if (i == list.indexOf(arr)) {
                // 迭代数组
                for (String st : arr) {
                    st = str + "," + st;
                    if (i < list.size() - 1) {
                        dataArray(list, list.get(i + 1), st, result);
                    } else if (i == list.size() - 1) {
                        result.add(st.substring(1));
                        System.out.println("=======@============" + st.substring(1));
                    }
                }
            }
        }
    }

    /**
     * 排列组合
     *
     * @param list
     * @param arr
     * @param str
     * @return
     */
    public static List<List<String>> splitContentArr(List<String[]> list, String[] arr, String str) {
        List<List<String>> _result = new ArrayList<List<String>>();
        try {
            List<String> result = new ArrayList<String>();
            dataArray(list, arr, str, result);
            for (String item : result) {
                _result.add(Arrays.asList(item.split(",")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _result;
    }


    public static boolean mathData(String _param1, String _param2) {
        boolean isUp = false;
        if (StringUtils.isBlank(_param1)) {
            isUp = true;
        } else {
            if (_param2.indexOf(",") < 0) {
                if (_param1.contains(_param2) || _param1.equals("0")) {
                    isUp = true;
                }
            } else {
                String[] split = _param2.split(",");
                for (String key : split) {
                    if (_param1.contains(key)) {
                        isUp = true;
                    }
                }
            }
        }
        return isUp;
    }


}
