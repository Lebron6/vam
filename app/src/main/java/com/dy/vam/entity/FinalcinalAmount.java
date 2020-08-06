package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/11.
 */

public class FinalcinalAmount {


    /**
     * code : 0
     * msg :
     * data : {"times":[{"year":2018,"month":1,"selected":0},{"year":2018,"month":2,"selected":0},{"year":2018,"month":3,"selected":0},{"year":2018,"month":4,"selected":0},{"year":2018,"month":5,"selected":0},{"year":2018,"month":6,"selected":0},{"year":2018,"month":7,"selected":0},{"year":2018,"month":8,"selected":0},{"year":2018,"month":9,"selected":0},{"year":2018,"month":10,"selected":0},{"year":2018,"month":11,"selected":0},{"year":2018,"month":12,"selected":0},{"year":2019,"month":1,"selected":0},{"year":2019,"month":2,"selected":0},{"year":2019,"month":3,"selected":0},{"year":2019,"month":4,"selected":0},{"year":2019,"month":5,"selected":1}],"list":[{"detailid":1921,"year":2019,"month":5,"money":0,"title":"abc","isincome":0},{"detailid":1922,"year":2019,"month":5,"money":120000,"title":"123","isincome":1},{"detailid":1923,"year":2019,"month":5,"money":0,"title":"项目盈利","isincome":1},{"detailid":1924,"year":2019,"month":5,"money":0,"title":"新增客户主营成本","isincome":0},{"detailid":1925,"year":2019,"month":5,"money":0,"title":"存续客户主营成本","isincome":0},{"detailid":1926,"year":2019,"month":5,"money":0,"title":"基准收益","isincome":0},{"detailid":1927,"year":2019,"month":5,"money":0,"title":"财务费用","isincome":0},{"detailid":1928,"year":2019,"month":5,"money":0,"title":"税费","isincome":0},{"detailid":1929,"year":2019,"month":5,"money":0,"title":"董事长室费用","isincome":0},{"detailid":1930,"year":2019,"month":5,"money":100000,"title":"业务提成","isincome":0},{"detailid":1931,"year":2019,"month":5,"money":0,"title":"外包费用","isincome":0},{"detailid":1932,"year":2019,"month":5,"money":0,"title":"管理费用","isincome":0},{"detailid":1933,"year":2019,"month":5,"money":0,"title":"管理费收入","isincome":1},{"detailid":1934,"year":2019,"month":5,"money":0,"title":"新增客户总收入","isincome":1},{"detailid":1935,"year":2019,"month":5,"money":0,"title":"存续客户总收入11","isincome":0}],"list2":[{"detailid":7,"year":2019,"month":5,"money":10000,"title":"工资社保"}]}
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
        private List<TimesBean> times;
        private List<ListBean> list;
        private List<List2Bean> list2;

        public List<TimesBean> getTimes() {
            return times;
        }

        public void setTimes(List<TimesBean> times) {
            this.times = times;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<List2Bean> getList2() {
            return list2;
        }

        public void setList2(List<List2Bean> list2) {
            this.list2 = list2;
        }

        public static class TimesBean {
            /**
             * year : 2018
             * month : 1
             * selected : 0
             */

            private int year;
            private int month;
            private int selected;

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
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
             * detailid : 1921
             * year : 2019
             * month : 5
             * money : 0
             * title : abc
             * isincome : 0
             */

            private int detailid;
            private int year;
            private int month;
            private String money;
            private String title;
            private int isincome;

            public int getDetailid() {
                return detailid;
            }

            public void setDetailid(int detailid) {
                this.detailid = detailid;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getIsincome() {
                return isincome;
            }

            public void setIsincome(int isincome) {
                this.isincome = isincome;
            }
        }

        public static class List2Bean {
            /**
             * detailid : 7
             * year : 2019
             * month : 5
             * money : 10000
             * title : 工资社保
             */

            private int detailid;
            private int year;
            private int month;
            private String money;
            private String title;

            public int getDetailid() {
                return detailid;
            }

            public void setDetailid(int detailid) {
                this.detailid = detailid;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
