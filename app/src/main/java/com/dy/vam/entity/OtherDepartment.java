package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/7.
 */

public class OtherDepartment {
    /**
     * code : 0
     * msg :
     * data : [{"departmentid":2,"name":"营销中","distrubution":"34.3%"},{"departmentid":4,"name":"行政部","distrubution":"0"},{"departmentid":5,"name":"Craig","distrubution":"0"},{"departmentid":6,"name":"总经办","distrubution":"12.7%"},{"departmentid":7,"name":"poj","distrubution":"0"},{"departmentid":8,"name":"Test 部门","distrubution":"0"},{"departmentid":9,"name":"Test1","distrubution":"0"},{"departmentid":10,"name":"泰格力莎","distrubution":"7.8%"},{"departmentid":11,"name":"3453","distrubution":"0"}]
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
         * departmentid : 2
         * name : 营销中
         * distrubution : 34.3%
         */

        private int departmentid;
        private String name;
        private String distrubution;

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

        public String getDistrubution() {
            return distrubution;
        }

        public void setDistrubution(String distrubution) {
            this.distrubution = distrubution;
        }
    }
}
