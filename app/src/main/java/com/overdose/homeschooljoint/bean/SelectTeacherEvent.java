package com.overdose.homeschooljoint.bean;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/21 16:56
 */
public class SelectTeacherEvent {
  private UserDataBean dataBean;

  public SelectTeacherEvent(UserDataBean dataBean) {
    this.dataBean = dataBean;
  }

  public UserDataBean getDataBean() {
    return dataBean;
  }

  public void setDataBean(UserDataBean dataBean) {
    this.dataBean = dataBean;
  }
}
