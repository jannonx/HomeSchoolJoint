package com.overdose.homeschooljoint.bean;

import java.util.List;

/**
 * 老师课程列表
 */
public class CourseBean {

    /**
     * status : 200
     * msg : 成功
     * data : [{"id":1,"coursecode":"chineseLanguage","coursename":"语文","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":2,"coursecode":"mathematic","coursename":"数学","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":3,"coursecode":"english","coursename":"英语","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":4,"coursecode":"sport","coursename":"体育","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":5,"coursecode":"physical","coursename":"物理","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":6,"coursecode":"chemical","coursename":"化学","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":7,"coursecode":"history","coursename":"历史","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":8,"coursecode":"moralEducation","coursename":"思想品德","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":9,"coursecode":"geographic","coursename":"地理","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":10,"coursecode":"sociology","coursename":"社会学","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"},{"id":11,"coursecode":"political","coursename":"政治","createtime":"2020-04-17T16:00:00.000+0000","updatetime":"2020-04-17T16:00:00.000+0000"}]
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
         * id : 1
         * coursecode : chineseLanguage
         * coursename : 语文
         * createtime : 2020-04-17T16:00:00.000+0000
         * updatetime : 2020-04-17T16:00:00.000+0000
         */

        private int id;
        private String coursecode;
        private String coursename;
        private String createtime;
        private String updatetime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
