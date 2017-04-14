package com.boz.common.wechat;

/**
 * Created by Administrator on 2016/10/16.
 */
public class Constants {
//    // 用户唯一凭证
//    public static String appid = "";
//    // 用户唯一凭证密钥
//    public static String appsecret = "";
//    //商户ID
//    public static String mch_id="";
//    //获取openId
    //public static String oauth2_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    public static String oauth_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
    public static String oauth_refresh_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

}
