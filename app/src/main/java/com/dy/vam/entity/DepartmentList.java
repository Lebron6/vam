package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/9.
 */

public class DepartmentList {
    /**
     * code : 0
     * msg :
     * data : [{"departmentid":1,"name":"企划研发中心"},{"departmentid":2,"name":"营销中心"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
         * departmentid : 1
         * name : 企划研发中心
         */

        private int departmentid;
        private String name;

        public int getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(int departmentid) {
            this.departmentid = departmentid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
