package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/24.
 */

public class GetDepartmentDisposable {


    /**
     * code : 0
     * msg :
     * data : {"total":-99889.2,"proportion":6.2,"list":[{"year":2019,"month":1,"disposable":"0.0000","departmentid":41,"month_proportion":"0.0000"},{"year":2019,"month":3,"disposable":"0.0000","departmentid":41,"month_proportion":"0.0000"},{"year":2019,"month":4,"disposable":"-99902.4600","departmentid":41,"month_proportion":"20.6600"},{"year":2019,"month":5,"disposable":13.26,"departmentid":41,"month_proportion":6.2}]}
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
         * total : -99889.2
         * proportion : 6.2
         * list : [{"year":2019,"month":1,"disposable":"0.0000","departmentid":41,"month_proportion":"0.0000"},{"year":2019,"month":3,"disposable":"0.0000","departmentid":41,"month_proportion":"0.0000"},{"year":2019,"month":4,"disposable":"-99902.4600","departmentid":41,"month_proportion":"20.6600"},{"year":2019,"month":5,"disposable":13.26,"departmentid":41,"month_proportion":6.2}]
         */

        private double total;
        private double proportion;
        private List<ListBean> list;

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double getProportion() {
            return proportion;
        }

        public void setProportion(double proportion) {
            this.proportion = proportion;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * year : 2019
             * month : 1
             * disposable : 0.0000
             * departmentid : 41
             * month_proportion : 0.0000
             */

            private int year;
            private int month;
            private String disposable;
            private String departmentid;
            private String month_proportion;

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

            public String getDisposable() {
                return disposable;
            }

            public void setDisposable(String disposable) {
                this.disposable = disposable;
            }

            public String getDepartmentid() {
                return departmentid;
            }

            public void setDepartmentid(String departmentid) {
                this.departmentid = departmentid;
            }

            public String getMonth_proportion() {
                return month_proportion;
            }

            public void setMonth_proportion(String month_proportion) {
                this.month_proportion = month_proportion;
            }
        }
    }
}
