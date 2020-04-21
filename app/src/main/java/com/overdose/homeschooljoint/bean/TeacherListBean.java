package com.overdose.homeschooljoint.bean;

import java.util.List;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/21 17:39
 */
public class TeacherListBean {

  /**
   * status : 200
   * msg : 成功
   * data : [{"id":1,"name":"春老师","code":"ST2323","password":"149815eb972b3c370dee3b89d645ae14",
   * "sex":"女","role":"0","coursecode":"chineseLanguage","classname":"115班","coursename":"语文",
   * "classcode":"115","createtime":"2020-04-18T16:00:00.000+0000",
   * "updatetime":"2020-04-18T16:00:00.000+0000"},{"id":2,"name":"ST0000","code":"ST0000",
   * "password":"bb79b0df1b3f429311918927942c1ee4","sex":"男","role":"0",
   * "coursecode":"chineseLanguage","classname":"115班","coursename":"语文","classcode":"115",
   * "createtime":"2020-04-18T16:00:00.000+0000","updatetime":"2020-04-18T16:00:00.000+0000"},{
   * "id":6,"name":"ST0004","code":"ST0004","password":"5fa285d80fa8bd3c90679b04e46e6d32",
   * "sex":"男","role":"0","coursecode":"chineseLanguage","classname":"115班","coursename":"语文",
   * "classcode":"115","createtime":"2020-04-18T16:00:00.000+0000",
   * "updatetime":"2020-04-18T16:00:00.000+0000"},{"id":7,"name":"ST01","code":"ST01",
   * "password":"d758a5d987a118322a363e28f7b01806","sex":"男","role":"0","coursecode":"chemical",
   * "classname":"115班","coursename":"化学","classcode":"115","createtime":"2019-01-18T16:00:00
   * .000+0000","updatetime":"2019-01-18T16:00:00.000+0000"},{"id":8,"name":"ST2","code":"ST2",
   * "password":"743b3257b344fcb0dc3158aa21e2c329","sex":"男","role":"0","coursecode":"history",
   * "classname":"115班","coursename":"历史","classcode":"115","createtime":"2019-01-20T16:00:00
   * .000+0000","updatetime":"2019-01-20T16:00:00.000+0000"}]
   */

  private int status;
  private String msg;
  private List<UserDataBean> data;

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

  public List<UserDataBean> getData() {
    return data;
  }

  public void setData(List<UserDataBean> data) {
    this.data = data;
  }


}
