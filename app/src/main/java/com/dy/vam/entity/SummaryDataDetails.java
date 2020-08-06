package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/24.
 */

public class SummaryDataDetails {

    /**
     * code : 0
     * msg :
     * data : {"list":[{"termid":604,"title":"存续客户总收入","month":"2018/1--2018/5","money":300},{"termid":605,"title":"新增客户总收入","month":"2018/1--2018/5","money":300},{"termid":606,"title":"管理费收入","month":"2018/1--2018/5","money":30000}],"help_url":"http://192.168.1.14:8081/help.html#2"}
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
         * list : [{"termid":604,"title":"存续客户总收入","month":"2018/1--2018/5","money":300},{"termid":605,"title":"新增客户总收入","month":"2018/1--2018/5","money":300},{"termid":606,"title":"管理费收入","month":"2018/1--2018/5","money":30000}]
         * help_url : http://192.168.1.14:8081/help.html#2
         */

        private String help_url;
        private List<ListBean> list;

        public String getHelp_url() {
            return help_url;
        }

        public void setHelp_url(String help_url) {
            this.help_url = help_url;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * termid : 604
             * title : 存续客户总收入
             * month : 2018/1--2018/5
             * money : 300
             */

            private int termid;
            private String title;
            private String month;
            private String money;

            public int getTermid() {
                return termid;
            }

            public void setTermid(int termid) {
                this.termid = termid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
