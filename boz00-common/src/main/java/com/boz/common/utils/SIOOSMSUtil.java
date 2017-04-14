package com.boz.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/27.
 */
public class SIOOSMSUtil {
    public static String send(String mobile, String message) throws IOException {
//        if(StringUtils.isNotBlank(CarriageHouseSMSUtil.send(mobile, message))){
//            return "good";
//        }

        if(!message.startsWith("【APEX_CLUB】"))
            message = "【APEX_CLUB】"+message;

        HttpClient httpClient = new HttpClient();

        String encode = "gbk";

        String url = "http://sms.10690221.com:9011/hy/";
        String uid = "80125";
        String auth = new MD5().getMD5ofStr("bkywWt0i46");
//        String mobile = "136123456787";
        String content=java.net.URLEncoder.encode(message, "gbk");
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        NameValuePair[] data = {
                new NameValuePair("uid", uid),
                new NameValuePair("auth", auth),
                new NameValuePair("encode", encode),
                new NameValuePair("mobile", mobile),
                new NameValuePair("expid", "0"),
                new NameValuePair("msg",content )};
        postMethod.setRequestBody(data);
        int statusCode = httpClient.executeMethod(postMethod);

        if (statusCode == HttpStatus.SC_OK) {
            String sms = postMethod.getResponseBodyAsString();
            System.out.println("result:" + sms);
        }
        System.out.println("statusCode="+statusCode);
        return String.valueOf(statusCode);
    }
}
