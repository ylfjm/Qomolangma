package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码加密
 *
 * @version 1.0
 * @creator zhangqiang
 * @create-time May 13, 2015   5:58:15 PM
 */
public class DataUtil {
    /**
     * 将double值改为四位字符串 例：60.00-->0060 120.00-->0120
     *
     * @param fee
     * @return
     */
    public static String getMoneyStr(double fee) {
        int i = (int) Math.round(fee);
        return String.format("%04d", i);
    }

    /*
     * 将明文密码进行二次加密
     */
    public static String encryptionPw(String password) {
        MD5 md5 = new MD5();
        return SecondaryInfilling.encrypt(md5.getMD5ofStr(password).substring(15, 30));
    }

    /*
     * 将二次加密密码转换成一次加密密码
     */
    public static String decryptPw(String encryptionPwd) {
        return SecondaryInfilling.decrypt(encryptionPwd);
    }

    public static void main(String[] args) {
        long i = 9999;
        System.out.println(String.format("%08d", i));
        System.out.println(DataUtil.encryptionPw("admin"));
    }
    
    /**
     * 比较两个字符串是否相等的共通方法
     * @param _param1
     * @param _param2
     * @return
     */
    public static boolean toMatch(String _param1, String _param2) {
        if (StringUtils.isBlank(_param1) && StringUtils.isBlank(_param2)) {
            return true;
        } else if(StringUtils.isNotBlank(_param1) && StringUtils.isNotBlank(_param2)) {
        	String[] arr = _param1.split(",");
        	for(String s : arr) {
        		if(_param2.indexOf(s) > -1) {
            		return true;
            	}
        	}
        }
        return false;
    }
}
