package alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088421830408783";

    public static String app_id = "2016073000125021";//"2016121904418574";

    // 交易安全检验码，由数字和字母组成的32位字符串
    // 如果签名方式设置为“MD5”时，请设置该参数
    public static String key = "ziwoocwqtdij1y2vu1t1n8xqhata252z";

    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
    public static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCsqtOU6+EsONttWVz7X9BcqtsxEXx1vjce3qvGB241/Ekt0p2lxSrOiMowd71uuu4FyHlB2hJOSD72Tk5kPVFQnTj6+X5IVZ1xpTLtgvFHpCkGjr3fFcyItf0KxPcuI2UcCY/UEM3nE5WtfYMmY1+m5G1DkXLg/xQ/8T46dQ6pR4/Fz/gIph55ViVfVEKZ5D4z9hz7IB/STrvs9lNvppwTKkpeedMuaXWn+iq3McoIKRkRH7Qp4uofOkuYtDhq5vSezH/bnUJp31Bf3i6ZHlM2uNs6jvyQU1+7VA9yUDUpws7zzTpRVpM2yhvowdFDZugnKuANNNz/j25v3in2bNMXAgMBAAECggEBAJJSHQS5M1MXR5ONxAbddiPhQeh+Cft5WxqU1fn1uAaVqankLmYZBKKLNtQF1IjubeCGt4L/er3N6VUunK3DbJIk+fFs1gAUVVzGArdVXIQ6b+23rt1Kis5bcnja2yDFK9yrma+7IdGtPg0MLMSuN5OlRZaZvkE+x/k7xWEia28058rIAU0LVEbKAfIhfmBdZM56DeuWkewobCiBd3riO6rjNm/WqnXs9qeV2elZcVgUj3Hw3uOAz8BWR7s+xnkP86tAfrzObfRdSwwsrSG6s6s5XNsLc3W8S/aii43DHn5ito3TWE25QIucVRcUS5wRxe2DZltDMk/SPtqdwxczJ+kCgYEA68gDTXRSkob2KC6hpdD2Dwl/MrjzBZzK4pVF3IKKsxxGu+9tNBFqpkyoMRoeik3QSGwde0o2bfQoP+cwIhmk+9lmNAxaD+VYuCRkNzpMKdysBnjiDkaSdIbcGGtNEBJN0qQBJ0h3uGx/Oq+naN3b9iW/+usuzwvkMS6s6D9uCKUCgYEAu3lNxkhSRLJS9+JOTBv//qV+Z1vC8A5Zzc4g6OQOpVUrGXwj0emKrd7Ys0x5y06V5x97ka8avB2epUH/6PEdVO7r7BBAag3qo2FyTJlSRcr/XcJoMhWX2WKBoCxLtjFoYPe92UWufqaXmQRv+LSw/0GzYETjPZiQOpdKiWlqZAsCgYB2/zdLfA3AjgTmOsKyoUfORwi/XMCqcHuk55metPOzee3pI+mFgdTFoaKxRXFsc+FO6+HlO4tE6xj1R4TvcOnngc0nL6S0qRehuBwsE2wlJI5dpdzRP6Ti0r16RY1bMyG70bITVWu21GtR/x0KvA4rqNm/lbrGHEZK3De/72NlUQKBgGrJE8YH1+SNdrj/zP2JTW3tMm0JVaaLZhfWe+RsUvNV6fDgRz8AOiADuYeX3iAyKSy3QBmhlibk8ELNr0kuXjjMxtF2CFrghnB9xy9efgCHOqJbZCJzLkWC2Lc1xAKJbh0dwKQMDvLRTbS5VFZkBhW/hL8nPhEN38mf97MBuro1AoGABMRYmm1PmKX9Qh4G0KbkFGe6EZK8tnpZD/YljfOdYGVgj+h6JXBp3526NkqbWnXFdmr2VOvATeC2eaR3PeokcqTzSqBdS1gV1Tn11ytX2+OJghkksfEtTDFJH71+Ql2KcWwMbf9qWTuqRSTDsolBnbcG3ubwl4bsEUVY/0kawpM=";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
            //"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArKrTlOvhLDjbbVlc+1/QXKrbMRF8db43Ht6rxgduNfxJLdKdpcUqzojKMHe9brruBch5QdoSTkg+9k5OZD1RUJ04+vl+SFWdcaUy7YLxR6QpBo693xXMiLX9CsT3LiNlHAmP1BDN5xOVrX2DJmNfpuRtQ5Fy4P8UP/E+OnUOqUePxc/4CKYeeVYlX1RCmeQ+M/Yc+yAf0k677PZTb6acEypKXnnTLml1p/oqtzHKCCkZER+0KeLqHzpLmLQ4aub0nsx/251Cad9QX94umR5TNrjbOo78kFNfu1QPclA1KcLO8806UVaTNsob6MHRQ2boJyrgDTTc/49ub94p9mzTFwIDAQAB";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持  utf-8
    public static String input_charset = "utf-8";

    // 签名方式，选择项：0001(RSA)、MD5
    public static String sign_type = "MD5";
    // 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA
}
