package com.boz.common.wechat;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Cartoon Zhang
 * @since 2016/11/24 下午8:17
 */
public class WechatUser {

    private Boolean subscribe;

    private String openid;

    private String nickname;

    private Integer sex;//1 is Male, 0 is Female

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Long subscribeTime;

    private String unionid;

    private String remark;

    private Integer groupid;

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public static WechatUser fromJsonObject(JSONObject jsonObject){
        WechatUser wechatUser = new WechatUser();
        wechatUser.setSubscribe(jsonObject.getBoolean("subscribe"));
        wechatUser.setHeadimgurl(jsonObject.getString("headimgurl"));
        wechatUser.setOpenid(jsonObject.getString("openid"));
        wechatUser.setCity(jsonObject.getString("city"));
        wechatUser.setCountry(jsonObject.getString("country"));
        wechatUser.setNickname(jsonObject.getString("nickname"));
        wechatUser.setProvince(jsonObject.getString("province"));
        wechatUser.setSex(jsonObject.getInteger("sex"));
        wechatUser.setUnionid(jsonObject.getString("unionid"));
        wechatUser.setLanguage(jsonObject.getString("language"));
        wechatUser.setRemark(jsonObject.getString("remark"));
        wechatUser.setGroupid(jsonObject.getInteger("groupid"));
        wechatUser.setSubscribeTime(jsonObject.getLong("subscribe_time"));
        return wechatUser;
    }
}

