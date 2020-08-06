package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/13.
 */

public class MarketGetCustom {
    /**
     * code : 0
     * msg :
     * data : {"name":"杭州网易","turnover":1000000,"grossprofit":500000,"createtime":"2018/01/2916:12","users":[{"userid":1,"username":"用户1","proportion":3},{"userid":2,"username":"用户 2","proportion":4}]}
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
         * name : 杭州网易
         * turnover : 1000000
         * grossprofit : 500000
         * createtime : 2018/01/2916:12
         * users : [{"userid":1,"username":"用户1","proportion":3},{"userid":2,"username":"用户 2","proportion":4}]
         */

        private String name;
        private String turnover;
        private String grossprofit;
        private String createtime;
        private List<UsersBean> users;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public String getGrossprofit() {
            return grossprofit;
        }

        public void setGrossprofit(String grossprofit) {
            this.grossprofit = grossprofit;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * userid : 1
             * username : 用户1
             * proportion : 3
             */

            private int userid;
            private String username;
            private String proportion;

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

            public String getProportion() {
                return proportion;
            }

            public void setProportion(String proportion) {
                this.proportion = proportion;
            }
        }
    }
}
