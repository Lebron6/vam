package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/26.
 */

public class SummaryGetDepartmentControl {


    /**
     * code : 0
     * msg :
     * data : {"years":[{"year":2019,"selected":1}],"month":"2019/1 - 2019/05","cost":[{"color":"#a25092","type":0,"title":"工资社保","money":20.2,"proportion":0},{"color":"#d52073","type":0,"title":"其他","money":"4555912","proportion":0},{"color":"#d81d1e","type":1,"title":"办公费","money":"171200","proportion":0},{"color":"#e4791f","type":2,"title":"办公用品","money":"46200","proportion":0},{"color":"#e5e140","type":6,"title":"福利费","money":"1000","proportion":0}],"total":4774312}
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
        /**
         * years : [{"year":2019,"selected":1}]
         * month : 2019/1 - 2019/05
         * cost : [{"color":"#a25092","type":0,"title":"工资社保","money":20.2,"proportion":0},{"color":"#d52073","type":0,"title":"其他","money":"4555912","proportion":0},{"color":"#d81d1e","type":1,"title":"办公费","money":"171200","proportion":0},{"color":"#e4791f","type":2,"title":"办公用品","money":"46200","proportion":0},{"color":"#e5e140","type":6,"title":"福利费","money":"1000","proportion":0}]
         * total : 4774312
         */

        private String month;
        private String total;
        private List<YearsBean> years;
        private List<CostBean> cost;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<YearsBean> getYears() {
            return years;
        }

        public void setYears(List<YearsBean> years) {
            this.years = years;
        }

        public List<CostBean> getCost() {
            return cost;
        }

        public void setCost(List<CostBean> cost) {
            this.cost = cost;
        }

        public static class YearsBean {
            /**
             * year : 2019
             * selected : 1
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

        public static class CostBean {
            /**
             * color : #a25092
             * type : 0
             * title : 工资社保
             * money : 20.2
             * proportion : 0
             */

            private String color;
            private int type;
            private String title;
            private double money;
            private double proportion;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
            }
        }
    }
}
