package com.boz.common.wechat;

import org.joda.time.DateTime;

/**
 * Created by Administrator on 2016/10/16.
 */
public class Token {

    private String accessToken;
    private int expiresIn;
    private DateTime createTime;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }
}
