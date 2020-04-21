package com.overdose.homeschooljoint.bean;

import java.util.List;

public class ToLeaveTBean {
    private int status;
    private String msg;
    private ToLeaveDataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ToLeaveDataBean getData() {
        return data;
    }

    public void setData(ToLeaveDataBean data) {
        this.data = data;
    }
}
