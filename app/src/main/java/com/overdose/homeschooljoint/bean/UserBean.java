package com.overdose.homeschooljoint.bean;

import com.overdose.homeschooljoint.utils.ConstantValue;

import androidx.annotation.NonNull;

public class UserBean {

    /**
     * status : 200
     * msg : 成功
     * data : {"id":null,"name":"admin1","password":"e00cf25ad42683b3df678c61f42c6bda","code":"1","sex":"1","classcode":"1","role":"1","createtime":"2019-01-18T14:07:36.614+0000","updatetime":"2019-01-18T14:07:36.614+0000"}
     */

    private int status;
    private String msg;
    private UserDataBean data;

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

    public UserDataBean getData() {
        return data;
    }

    public void setData(UserDataBean data) {
        this.data = data;
    }




}
