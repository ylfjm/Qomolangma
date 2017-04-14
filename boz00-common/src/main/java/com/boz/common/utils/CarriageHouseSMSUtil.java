package com.boz.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.Date;


/**
 * 健齿客短信发送工具类
 *
 * @author chenzhenhu
 * @company tocersoft
 * @cerate-date 2015-2-4上午9:45:14
 * @email chenzhenhu@tocersoft.com
 */
public class CarriageHouseSMSUtil {


    /**
     * 请求地址
     */
    private final static String requestUrl = "http://www.tecact.com/smsinfo/smsinfo.aspx";
    /**
     * 帐号
     */
    private final static String username = "carriagehouseyzm";
    /**
     * 密码
     */
    private final static String password = "123456";
    private static Log logger = LogFactory.getLog(CarriageHouseSMSUtil.class);

    /**
     * 发送短信
     *
     * @param mobile  手机号
     * @param content 发送内容
     * @return 成功 true 失败 false
     */
    public static String send(String mobile, String content) {
        logger.info("开始调用短信接口");
        logger.info("发送内容：" + content);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String taskID = Utils.dateFormat(new Date(), "yyyyMMddHHmmss") + "_000001";
        try {
            content += "【APEX_CLUB】";
//            content += "【恺丽之家】";
            String url = requestUrl + "?op=sendsms&username=" + username
                    + "&password=" + password + "&smsmes=" + URLEncoder.encode(content, "UTF-8") + "&smsphone=" + mobile
                    + "&taskId=" + taskID;
            HttpGet httpGet = new HttpGet(url);

            response = httpclient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("发送短信接口数据返回内容：" + result);

            if (StringUtils.isBlank(result)) {
                logger.error("调用短信接口失败，返回错误信息为:空！");
                return taskID;
            }
            String re[] = result.split(",");
            String resultStatus = "";
            Double balance = 0D;
            if (re.length == 1) {
                resultStatus = re[0];
            } else if (re.length == 2) {
                resultStatus = re[0];
                balance = Double.valueOf(re[1]);
            }
            String message = "";
            if ("1".equals(resultStatus)) {
                logger.info("短信接口调用成功,发送成功,余额：" + balance + "元！");
            } else if ("0".equals(resultStatus)) {
                message = "参数不正确";
                logger.error("调用短信接口失败，返回错误信息为:" + message + ",余额：" + balance + "元！");
            } else if ("2".equals(resultStatus)) {
                message = "用户名密码错误";
                logger.error("调用短信接口失败，返回错误信息为:" + message + ",余额：" + balance + "元！");
            } else if ("3".equals(resultStatus)) {
                message = "手机号码不能超过200个";
                logger.error("调用短信接口失败，返回错误信息为:" + message + ",余额：" + balance + "元！");
            } else if ("5".equals(resultStatus)) {
                message = "数据库访问失败";
                logger.error("调用短信接口失败，返回错误信息为:" + message + ",余额：" + balance + "元！");
            } else if ("6".equals(resultStatus)) {
                message = "余额不足";
                logger.error("调用短信接口失败，返回错误信息为:" + message + ",余额：" + balance + "元！");
            } else if ("7".equals(resultStatus)) {
                message = "未加签名或签名不正确";
                logger.error("调用短信接口失败，返回错误信息为:" + message + ",余额：" + balance + "元！");
            } else {
                logger.error("调用短信接口失败，发送短信接口数据返回内容：空！");
            }
            return taskID;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用短信接口发生异常：" + e.getMessage(), e);
        }
        return taskID;
    }


    public static void main(String[] args) {
        //测试接口
        CarriageHouseSMSUtil.send("13797277467", "测试短信上行内容");
    }
}
