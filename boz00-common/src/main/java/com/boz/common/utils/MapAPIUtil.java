package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取经纬度
 *
 * @author jueyue 返回格式：Map<String,Object> map map.put("status",
 * reader.nextString());//状态 map.put("result", list);//查询结果
 * list<map<String,String>>
 * 密钥:f247cdb592eb43ebac6ccd27f796e2d2
 */

/**
 * @author admin
 *
 */
public class MapAPIUtil {
    public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";


    /**
     * 返回输入地址的经纬度坐标
     * key lng(经度),lat(纬度)
     */
    public static Map<String, String> getGeocoderLatitude(String address) {
        BufferedReader in = null;
        try {
            //将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
//	       如果有代理，要设置代理，没代理可注释  
//	      System.setProperty("http.proxyHost","192.168.1.188");  
//	      System.setProperty("http.proxyPort","3128");  
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address=" + address + "&output=json&key=" + KEY_1);

            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            Map<String, String> map = null;
            if (StringUtils.isNotEmpty(str)) {
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String args[]) {

        System.out.println(MapAPIUtil.getDistance(140.535717, 31.30451, 121.535717, 31.30451));

//	        Map<String, String> json = baiDuDiTuAPIUtil.getGeocoderLatitude("上海");  
//	        System.out.println(json);
//	        System.out.println("lng : "+json.get("lng"));  
//	        System.out.println("lat : "+json.get("lat"));  
//	    	Double [] dou =baiDuDiTuAPIUtil.getCoordinate("ssssssssss");
//	    	System.out.println(dou[0]+","+dou[1]);
    }

    /**
     * @param addr  要获取坐标的地址
     * @return 返回经纬坐标
     */
    public static Double[] getCoordinate(String addr) {
        String lng = null;//经度
        String lat = null;//纬度
        String address = null;
        try {
            address = URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = "f247cdb592eb43ebac6ccd27f796e2d2";
        String url = String.format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理 
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                int count = 1;
                while ((data = br.readLine()) != null) {
                    if (count == 5) {
                        lng = (String) data.subSequence(data.indexOf(":") + 1, data.indexOf(","));//经度
                        count++;
                    } else if (count == 6) {
                        lat = data.substring(data.indexOf(":") + 1);//纬度
                        count++;
                    } else {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (insr != null) {
                try {
                    insr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (null == lng) {
            lng = "";
        }
        if (null == lat) {
            lat = "";
        }
        return new Double[]{Double.parseDouble(lng), Double.parseDouble(lat)};
    }

    /**
     *
     * @return 返回相差的经纬度
     */
    public static double Differ(double a1, double a2, double b1, double b2) {
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6366000 * tt;
    }

    /**
     * 计算两点之间距离
     * @param a1
     * @param a2
     * @param b1
     * @param b2
     * @return 相差多少米
     */
    public static double getDistance(double a1, double a2, double b1, double b2) {
        double lat1 = (Math.PI / 180) * a1;
        double lat2 = (Math.PI / 180) * b1;

        double lon1 = (Math.PI / 180) * a2;
        double lon2 = (Math.PI / 180) * b2;

        //地球半径
        double R = 6371;

        //两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d;
    }

}
