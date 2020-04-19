package com.overdose.homeschooljoint.bean;

public class ToLeaveQBean {

    /**
     * approvercode : string
     * approvername : string
     * opinion : string
     * reason : string
     * starttime : 2020-04-19T16:11:38.566Z
     * studentcode : string
     * studentname : string
     * updatetime : 2020-04-19T16:11:38.566Z
     */

    private String approvercode;//必填
    private String approvername;//必填
    private String opinion;//必填
    private String reason;//必填
    private String starttime;//必填
    private String studentcode;//必填
    private String studentname;//必填
    private String updatetime;//必填

    public String getApprovercode() {
        return approvercode;
    }

    public void setApprovercode(String approvercode) {
        this.approvercode = approvercode;
    }

    public String getApprovername() {
        return approvername;
    }

    public void setApprovername(String approvername) {
        this.approvername = approvername;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "ToLeaveQBean{" +
                "approvercode='" + approvercode + '\'' +
                ", approvername='" + approvername + '\'' +
                ", opinion='" + opinion + '\'' +
                ", reason='" + reason + '\'' +
                ", starttime='" + starttime + '\'' +
                ", studentcode='" + studentcode + '\'' +
                ", studentname='" + studentname + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
