package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/7.
 */

public class OtherDepartmentDetailsData {


    /**
     * code : 0
     * msg :
     * data : {"worth":-100054.25,"total_disposable":-100034.24702,"total_control":20,"disposable_list":[{"year":2019,"month":1,"disposable":"0.0000","control":"267517.2200","departmentid":42},{"year":2019,"month":3,"disposable":"0.0000","control":"63068.9300","departmentid":42},{"year":2019,"month":4,"disposable":"-100047.5300","control":"231931.0000","departmentid":42}],"control_list":[{"type":-1,"title":"工资社保","money":1,"proportion":0.05},{"type":1,"title":"办公费","money":"9.0000","proportion":45},{"type":6,"title":"福利费","money":"10.0000","proportion":50}],"control_month":"2019/1 - 2019/05"}
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
         * worth : -100054.25
         * total_disposable : -100034.24702
         * total_control : 20
         * disposable_list : [{"year":2019,"month":1,"disposable":"0.0000","control":"267517.2200","departmentid":42},{"year":2019,"month":3,"disposable":"0.0000","control":"63068.9300","departmentid":42},{"year":2019,"month":4,"disposable":"-100047.5300","control":"231931.0000","departmentid":42}]
         * control_list : [{"type":-1,"title":"工资社保","money":1,"proportion":0.05},{"type":1,"title":"办公费","money":"9.0000","proportion":45},{"type":6,"title":"福利费","money":"10.0000","proportion":50}]
         * control_month : 2019/1 - 2019/05
         */

        private String worth;
        private String total_disposable;
        private String total_control;
        private String control_month;
        private List<DisposableListBean> disposable_list;
        private List<ControlListBean> control_list;

        public String getWorth() {
            return worth;
        }

        public void setWorth(String worth) {
            this.worth = worth;
        }

        public String getTotal_disposable() {
            return total_disposable;
        }

        public void setTotal_disposable(String total_disposable) {
            this.total_disposable = total_disposable;
        }

        public String getTotal_control() {
            return total_control;
        }

        public void setTotal_control(String total_control) {
            this.total_control = total_control;
        }

        public String getControl_month() {
            return control_month;
        }

        public void setControl_month(String control_month) {
            this.control_month = control_month;
        }

        public List<DisposableListBean> getDisposable_list() {
            return disposable_list;
        }

        public void setDisposable_list(List<DisposableListBean> disposable_list) {
            this.disposable_list = disposable_list;
        }

        public List<ControlListBean> getControl_list() {
            return control_list;
        }

        public void setControl_list(List<ControlListBean> control_list) {
            this.control_list = control_list;
        }

        public static class DisposableListBean {
            /**
             * year : 2019
             * month : 1
             * disposable : 0.0000
             * control : 267517.2200
             * departmentid : 42
             */

            private String year;
            private String month;
            private String disposable;
            private String control;
            private int departmentid;

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getDisposable() {
                return disposable;
            }

            public void setDisposable(String disposable) {
                this.disposable = disposable;
            }

            public String getControl() {
                return control;
            }

            public void setControl(String control) {
                this.control = control;
            }

            public int getDepartmentid() {
                return departmentid;
            }

            public void setDepartmentid(int departmentid) {
                this.departmentid = departmentid;
            }
        }

        public static class ControlListBean {
            /**
             * type : -1
             * title : 工资社保
             * money : 1
             * proportion : 0.05
             */

            private String type;
            private String title;
            private String money;
            private String proportion;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getProportion() {
                return proportion;
            }

            public void setProportion(String proportion) {
                this.proportion = proportion;
            }
        }
    }
}
