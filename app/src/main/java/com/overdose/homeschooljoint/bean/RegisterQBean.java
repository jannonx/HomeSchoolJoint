package com.overdose.homeschooljoint.bean;

public class RegisterQBean {
    private String roleName;
    private String name;
    private String code;
    private String password;
    private String classs;
    private String sex;
    private String coursecode;
    //role 1 是学生 role 0 是教师
    private String role;

    public RegisterQBean() {
    }

    public RegisterQBean(String roleName,String name, String code, String password, String classs, String sex, String coursecode, String role) {
        this.roleName = roleName;
        this.name = name;
        this.code = code;
        this.password = password;
        this.classs = classs;
        this.sex = sex;
        this.coursecode = coursecode;
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegisterQBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", classs='" + classs + '\'' +
                ", sex='" + sex + '\'' +
                ", coursecode='" + coursecode + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
