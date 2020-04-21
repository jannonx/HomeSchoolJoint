package com.overdose.homeschooljoint.bean;

import java.io.Serializable;

public class HomeWordQBean  implements Serializable {
    /**
     * classcode : 115
     * createtime : 2020-04-21T14:23:25.998Z
     * instructions : hahha
     * teachercode : ST11
     * teachername : ST11
     * updatetime : 2020-04-21T14:23:25.998Z
     */

    private int id;
    private String classcode;
    private String createtime;
    private String instructions;
    private String teachercode;
    private String teachername;
    private String updatetime;
    private String content;
    private String studentname;
    private String studentscode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTeachercode() {
        return teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentscode() {
        return studentscode;
    }

    public void setStudentscode(String studentscode) {
        this.studentscode = studentscode;
    }
}
