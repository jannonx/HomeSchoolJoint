package com.overdose.homeschooljoint.bean;

import java.util.List;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/21 18:14
 */
public class ToLeaveRBean {

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
  private List<DataBean> data;

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

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * id : 10
     * studentname : ST22
     * studentcode : ST22
     * reason : 1111
     * starttime : null
     * endtime : null
     * approvername : ST0000
     * approvercode : ST0000
     * opinion : 111
     * status : null
     * createtime : 2019-01-20T16:00:00.000+0000
     * updatetime : 2019-01-20T16:00:00.000+0000
     */

    private int id;
    private String studentname;
    private String studentcode;
    private String reason;
    private String starttime;
    private String endtime;
    private String approvername;
    private String approvercode;
    private String opinion;
    private int status;//0 未审批 ,1 已经审批
    private String createtime;
    private String updatetime;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getStudentname() {
      return studentname;
    }

    public void setStudentname(String studentname) {
      this.studentname = studentname;
    }

    public String getStudentcode() {
      return studentcode;
    }

    public void setStudentcode(String studentcode) {
      this.studentcode = studentcode;
    }

    public String getReason() {
      return reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }

    public String getStarttime() {
      return starttime;
    }

    public void setStarttime(String starttime) {
      this.starttime = starttime;
    }

    public String getEndtime() {
      return endtime;
    }

    public void setEndtime(String endtime) {
      this.endtime = endtime;
    }

    public String getApprovername() {
      return approvername;
    }

    public void setApprovername(String approvername) {
      this.approvername = approvername;
    }

    public String getApprovercode() {
      return approvercode;
    }

    public void setApprovercode(String approvercode) {
      this.approvercode = approvercode;
    }

    public String getOpinion() {
      return opinion;
    }

    public void setOpinion(String opinion) {
      this.opinion = opinion;
    }

    public int getStatus() {
      return status;
    }

    public String getStatusTxt() {
      return status == 1 ? "已经审批" : "未审批";
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getCreatetime() {
      return createtime;
    }

    public void setCreatetime(String createtime) {
      this.createtime = createtime;
    }

    public String getUpdatetime() {
      return updatetime;
    }

    public void setUpdatetime(String updatetime) {
      this.updatetime = updatetime;
    }
  }
}
