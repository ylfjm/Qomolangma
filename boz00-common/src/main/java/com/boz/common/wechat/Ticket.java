package com.boz.common.wechat;

import org.joda.time.DateTime;

/**
 * Created by Administrator on 2016/10/16.
 */
public class Ticket {

    private String ticket;
    private int expiresIn;
    private DateTime createTime;

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public String getTicket() {
        return ticket;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}
