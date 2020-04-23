package com.overdose.homeschooljoint.bean;

import java.util.List;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/22 14:32
 */
public class ScoreRBean {
  private int status;
  private String msg;
  private List<ScoreDataBean> data;

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

  public List<ScoreDataBean> getData() {
    return data;
  }

  public void setData(List<ScoreDataBean> data) {
    this.data = data;
  }
}
