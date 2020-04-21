package com.overdose.homeschooljoint.bean;

import java.util.List;

public class StudentLeaveListBean {
    /**
     * status : 200
     * msg : 提交成功!
     * data : [{"id":10,"studentname":"ST22","studentcode":"ST22","reason":"1111","starttime":null,
     * "endtime":null,"approvername":"ST0000","approvercode":"ST0000","opinion":"111",
     * "status":null,"createtime":"2019-01-20T16:00:00.000+0000","updatetime":"2019-01-20T16:00:00
     * .000+0000"}]
     */

    private int status;
    private String msg;
    private List<ToLeaveDataBean> data;

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

    public List<ToLeaveDataBean> getData() {
        return data;
    }

    public void setData(List<ToLeaveDataBean> data) {
        this.data = data;
    }
}
