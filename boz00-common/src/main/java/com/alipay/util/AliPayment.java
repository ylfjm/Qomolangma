package alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 支付宝支付付款类
 *
 * @version 1.0
 * @creator zhangqiang
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @create-time Nov 10, 2014   1:59:02 PM
 */
public class AliPayment {

    /**
     * 创建付款链接
     *
     * @param paygateway    支付网管
     * @param service       交易方法
     * @param sign_type     签名方式
     * @param out_trade_no  订单号码
     * @param input_charset 字符集
     * @param partner       商户ID
     * @param key           商户秘钥
     * @param body          商品标题
     * @param total_fee     总价
     * @param currency      货币
     * @param subject       商品描述
     * @param notify_url    通知url
     * @param return_url    交易成功返回url
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static String createPaymentUrl(String paygateway, String service, String sign_type,
                                          String out_trade_no, String input_charset,
                                          String partner, String key,
                                          String body, String total_fee, String currency,
                                          String subject, String notify_url,
                                          String return_url) throws Exception {

        Map params = new HashMap();
        params.put("service", service);
        params.put("partner", partner);
        params.put("subject", subject);
        params.put("body", body);
        params.put("out_trade_no", out_trade_no);
        params.put("rmb_fee", total_fee);
        params.put("currency", currency);
        params.put("return_url", return_url);
        params.put("notify_url", notify_url);
        params.put("_input_charset", input_charset);

        String prestr = "";

        prestr = prestr + key;

        String sign = com.alipay.util.Md5Encrypt.md5(getContent(params, key));

        String parameter = "";
        parameter = parameter + paygateway;

        List keys = new ArrayList(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
            try {
                parameter = parameter + keys.get(i) + "="
                        + URLEncoder.encode((String) params.get(keys.get(i)), input_charset) + "&";
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }
        }

        parameter = parameter + "sign=" + sign + "&sign_type=" + sign_type;

        return parameter;

    }

    /**
     * 获取签名数据
     *
     * @param params
     * @param privateKey
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static String getContent(Map params, String privateKey) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);

            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr + privateKey;
    }
}
