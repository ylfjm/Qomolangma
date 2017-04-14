//package com.boz.common.utils;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
///**
// * HTTP请求工具类
// *
// * @author zhangqiang
// */
//public class HttpUtil {
//
//    private static Log logger = LogFactory.getLog(HttpUtil.class);
//
//    private static String map_baidu_url = "http://api.map.baidu.com/geocoder/v2/?address=";
//
//    private static String map_baidu_ak = "gs0p8QqkzhyGvMoQw7uWizjY25fZvjB5";
//
//    /**
//     * Get方式获取数据
//     *
//     * @param url
//     * @return
//     */
//    public static String loadContentByGetMethod(String url) throws Exception {
//        String result = null;
//
//		/* 1 生成 HttpClinet 对象并设置参数 */
//        HttpClient httpClient = new HttpClient();
//        // 设置 Http 连接超时为10秒
//        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
//		/* 2 生成 GetMethod 对象并设置参数 */
//        GetMethod getMethod = new GetMethod(url);
//        try {
//            // 设置 get 请求超时为 10 秒
//            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
//            // 设置请求重试处理，用的是默认的重试处理：请求三次
//            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
//			/* 3 执行 HTTP GET 请求 */
//            int statusCode = httpClient.executeMethod(getMethod);
//			/* 4 判断访问的状态码 */
//            if (statusCode != HttpStatus.SC_OK) {
//                logger.debug("GET请求失败: " + getMethod.getStatusLine());
//            }
//
//
//            // 读取 HTTP 响应内容，这里简单打印网页内容
//            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
//            result = new String(responseBody, "utf-8");
//        } catch (Exception e) {
//            // 发生致命的异常，可能是协议不对或者返回的内容有问题
//            logger.error("读取GET接口数据时发生异常：" + e.getMessage(), e);
//            throw new Exception(e);
//        } finally {
//			/* 6 .释放连接 */
//            getMethod.releaseConnection();
//        }
//
//        logger.debug("读取数据为：" + result);
//
//        return result;
//    }
//
//    /**
//     * 发送JSON数据，POST方式
//     *
//     * @param data
//     */
//    public static String postJSONData(String url, JSONObject data) throws Exception {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//        String inputParam = data.toJSONString();
//        logger.debug("POST接口发送内容：" + inputParam);
//        httpPost.setEntity(new StringEntity(inputParam, "UTF-8"));
//        CloseableHttpResponse response = null;
//
//        String result = null;
//        try {
//            response = httpclient.execute(httpPost);
//            result = EntityUtils.toString(response.getEntity(), "UTF-8");
//            logger.debug("发送POST接口数据返回内容：" + result);
//        } catch (Exception ex) {
//            logger.debug("调用POST接口数据时发生异常：" + ex.getMessage(), ex);
//            throw new Exception(ex.getMessage());
//        } finally {
//            if (null != response) {
//                try {
//                    response.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//
//        return result;
//
//    }
//
//    /**
//     * 发送POST请求参数
//     *
//     * @param url
//     * @param paramMap
//     * @return
//     * @throws Exception
//     */
//    public static String postData(String url, Map<String, String> paramMap) throws Exception {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String result = null;
//        try {
//            // 构造请求
//            HttpPost httpPost = new HttpPost(url);
//            // 封装参数
//            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
//            if (null != paramMap) {
//                for (Entry<String, String> item : paramMap.entrySet()) {
//                    nvps.add(new BasicNameValuePair(item.getKey(), item.getValue()));
//                }
//            }
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//            // 发起请求
//            response = httpClient.execute(httpPost);
//            // 获取响应数据
//            result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
//            logger.debug("接收到的POST响应内容：" + result);
//        } catch (Exception ex) {
//            String msg = "POST请求发送失败:" + ex.getMessage();
//            logger.error(msg, ex);
//            throw new Exception(ex.getMessage());
//        } finally {
//            try {
//                response.close();
//                httpClient.close();
//            } catch (Exception e) {
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * 百度地图API获取地址的经纬度
//     *
//     * @param address
//     * @return
//     */
//    public static Map<String, Double> getLngAndLat(String address) {
//    	Map<String, Double> map = new HashMap<String, Double>();
//		try {
//			URL url = new URL(map_baidu_url + address + "&output=json&ak=" + map_baidu_ak);
//			URLConnection urlConn = url.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//			String inputLine = null;
//			StringBuilder sb = new StringBuilder();
//			while ((inputLine = in.readLine()) != null) {
//				sb.append(inputLine);
//			}
//			in.close();
//			String jsonStr = sb.toString();
//			JSONObject json = JSONObject.parseObject(jsonStr);
//			if (json.get("status").toString().equals("0")) {
//				double lng = json.getJSONObject("result").getJSONObject("location").getDouble("lng");
//				double lat = json.getJSONObject("result").getJSONObject("location").getDouble("lat");
//				map.put("lng", lng);
//				map.put("lat", lat);
//			}
//		} catch(JSONException e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        } catch (MalformedURLException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		return map;
//	}
//}
