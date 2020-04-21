package com.overdose.homeschooljoint.bean;

public class HomeWorkRBean {
    /**
     * status : 200
     * msg : 修改成功
     * data : 1
     */

    private int status;
    private String msg;
    private HomeWordQBean data;

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

    public HomeWordQBean getData() {
        return data;
    }

    public void setData(HomeWordQBean data) {
        this.data = data;
    }
}
