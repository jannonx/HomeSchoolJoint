package com.overdose.homeschooljoint.bean;

public class ModifyPwQBean {
    private String roleName;
    private String roleSim;//teacher-edl、student-edl
    private String studentCode;
    private String teacherCode;
    private String password;

    public ModifyPwQBean(String roleName, String studentCode, String teacherCode, String password) {
        this.roleName = roleName;
        this.studentCode = studentCode;
        this.teacherCode = teacherCode;
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSim() {
        return roleSim;
    }

    public void setRoleSim(String roleSim) {
        this.roleSim = roleSim;
    }

    public ModifyPwQBean() {
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ModifyPwQBean{" +
                "roleName='" + roleName + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", teacherCode='" + teacherCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

