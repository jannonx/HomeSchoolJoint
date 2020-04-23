package com.overdose.homeschooljoint.bean;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/22 14:31
 */
public class ScoreDataBean {

  /**
   * id : 6
   * score : 122
   * teachername : ST0000
   * teachercode : ST0000
   * coursecode : chineseLanguage
   * coursename : 语文
   * periodname : 期末考试
   * studentcode : ST111
   * studenname : ST111
   * createtime : 2019-01-20T16:00:00.000+0000
   * update : 2019-01-20T16:00:00.000+0000
   */

  private int id;
  private String score;
  private String teachername;
  private String teachercode;
  private String coursecode;
  private String coursename;
  private String periodname;
  private String studentcode;
  private String studenname;
  private String createtime;
  private String update;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public String getTeachername() {
    return teachername;
  }

  public void setTeachername(String teachername) {
    this.teachername = teachername;
  }

  public String getTeachercode() {
    return teachercode;
  }

  public void setTeachercode(String teachercode) {
    this.teachercode = teachercode;
  }

  public String getCoursecode() {
    return coursecode;
  }

  public void setCoursecode(String coursecode) {
    this.coursecode = coursecode;
  }

  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }

  public String getPeriodname() {
    return periodname;
  }

  public void setPeriodname(String periodname) {
    this.periodname = periodname;
  }

  public String getStudentcode() {
    return studentcode;
  }

  public void setStudentcode(String studentcode) {
    this.studentcode = studentcode;
  }

  public String getStudenname() {
    return studenname;
  }

  public void setStudenname(String studenname) {
    this.studenname = studenname;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public String getUpdate() {
    return update;
  }

  public void setUpdate(String update) {
    this.update = update;
  }
}
