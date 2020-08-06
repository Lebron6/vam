package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/12.
 */

public class FinalcinalCustomDetails {

    /**
     * code : 0
     * msg :
     * data : {"name":"大洋","turnover":0,"grossprofit":1000000,"createtime":"2018/02/11 11:04","username":"用户1","users":[{"username":"用户1","proportion":3,"money":300},{"username":"用户2","proportion":4,"money":400},{"username":"陈小希","proportion":2,"money":200},{"username":"","proportion":3,"money":300}]}
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
         * name : 大洋
         * turnover : 0
         * grossprofit : 1000000
         * createtime : 2018/02/11 11:04
         * username : 用户1
         * users : [{"username":"用户1","proportion":3,"money":300},{"username":"用户2","proportion":4,"money":400},{"username":"陈小希","proportion":2,"money":200},{"username":"","proportion":3,"money":300}]
         */

        private String name;
        private String turnover;
        private String grossprofit;
        private String createtime;
        private String username;
        private String post;

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * username : 用户1
             * proportion : 3
             * money : 300
             */

            private String username;
            private String proportion;
            private String money;

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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
