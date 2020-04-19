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
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * name : admin1
         * password : e00cf25ad42683b3df678c61f42c6bda
         * code : 1
         * sex : 1
         * classcode : 1
         * role : 1
         * createtime : 2019-01-18T14:07:36.614+0000
         * updatetime : 2019-01-18T14:07:36.614+0000
         */

        private Object id;
        private String name;
        private String password;
        private String code;
        private String sex;
        private String coursecode;
        private String coursename;
        private String classcode;
        private String classname;
        private String role;
        private String createtime;
        private String updatetime;

        public boolean isStudent() {
            return ConstantValue.USER_ROLE_STUDENT.equals(role);
        }

        public String getRoleName() {
            return isStudent() ? "student" : "teacher";
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getCoursename() {
            return coursename;
        }

        public void setCoursename(String coursename) {
            this.coursename = coursename;
        }

        public String getClasscode() {
            return classcode;
        }

        public void setClasscode(String classcode) {
            this.classcode = classcode;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", code='" + code + '\'' +
                    ", sex='" + sex + '\'' +
                    ", coursecode='" + coursecode + '\'' +
                    ", coursename='" + coursename + '\'' +
                    ", classcode='" + classcode + '\'' +
                    ", classname='" + classname + '\'' +
                    ", role='" + role + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    '}';
        }
    }


}
