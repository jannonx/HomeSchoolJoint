package com.overdose.homeschooljoint.bean;

import java.util.List;

/**
 * 班级
 */
public class ClassBean {


    /**
     * status : 200
     * msg : 成功
     * data : [{"id":1,"classcame":"115班","classcode":"115","createtime":"2020-04-17T16:00:00.000+0000","updatetime":""}]
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
         * classcame : 115班
         * classcode : 115
         * createtime : 2020-04-17T16:00:00.000+0000
         * updatetime :
         */

        private int id;
        private String classcame;
        private String classcode;
        private String createtime;
        private String updatetime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClasscame() {
            return classcame;
        }

        public void setClasscame(String classcame) {
            this.classcame = classcame;
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

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
