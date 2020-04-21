package com.overdose.homeschooljoint.bean;

import java.util.List;

public class HomeWorkSBean {
    private int status;
    private String msg;
    private List<HomeWordQBean> data;

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

    public List<HomeWordQBean> getData() {
        return data;
    }

    public void setData(List<HomeWordQBean> data) {
        this.data = data;
    }
}
