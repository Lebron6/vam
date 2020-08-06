package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/8.
 */

public class GetValueData {
    /**
     * code : 0
     * msg :
     * data : {"years":[{"year":2017,"selected":0},{"year":2018,"selected":1}],"list":[{"userid":1,"username":"用户1","department":"企划研发中心1","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":2,"username":"用户2","department":"企划研发中心1","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":3,"username":"用户3","department":"营销中心","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":6,"username":"陈小希","department":"企划研发中心1","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":12,"username":"","department":"营销中心","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":13,"username":"接口","department":"企划研发中心1","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":14,"username":"","department":"企划研发中心1","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":15,"username":"王勇","department":"总经办","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0},{"userid":16,"username":"卢西奥","department":"营销中心","first":0,"second":0,"third":0,"fourth":0,"total":0,"allot":0,"distrubution":0,"balance":0}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<YearsBean> years;
        private List<ListBean> list;

        public List<YearsBean> getYears() {
            return years;
        }

        public void setYears(List<YearsBean> years) {
            this.years = years;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class YearsBean {
            /**
             * year : 2017
             * selected : 0
             */

            private int year;
            private int selected;

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }
        }

        public static class ListBean {
            /**
             * userid : 1
             * username : 用户1
             * department : 企划研发中心1
             * first : 0
             * second : 0
             * third : 0
             * fourth : 0
             * total : 0
             * allot : 0
             * distrubution : 0
             * balance : 0
             */

            private int userid;
            private String username;
            private String department;
            private String first;
            private String second;
            private String third;
            private String fourth;
            private String total;
            private String allot;
            private String distrubution;
            private String balance;
            private int type;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public String getFirst() {
                return first;
            }

            public void setFirst(String first) {
                this.first = first;
            }

            public String getSecond() {
                return second;
            }

            public void setSecond(String second) {
                this.second = second;
            }

            public String getThird() {
                return third;
            }

            public void setThird(String third) {
                this.third = third;
            }

            public String getFourth() {
                return fourth;
            }

            public void setFourth(String fourth) {
                this.fourth = fourth;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getAllot() {
                return allot;
            }

            public void setAllot(String allot) {
                this.allot = allot;
            }

            public String getDistrubution() {
                return distrubution;
            }

            public void setDistrubution(String distrubution) {
                this.distrubution = distrubution;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }
        }
    }
}
