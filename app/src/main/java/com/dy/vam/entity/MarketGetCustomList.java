package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/12.
 */

public class MarketGetCustomList {

    /**
     * code : 0
     * msg :
     * data : {"page_now":1,"has_next":true,"list":[{"customid":32,"name":"王老三","turnover":0,"grossprofit":0,"createtime":"2018/02/13","users":[{"userid":16,"username":"卢西奥","proportion":327.67,"money":0},{"userid":15,"username":"王勇","proportion":327.67,"money":0}]},{"customid":31,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":28,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":30,"name":"李白2222","turnover":0,"grossprofit":3000,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0.6},{"userid":2,"username":"用户2","proportion":3,"money":0.9}]},{"customid":29,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":26,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":27,"name":"李白2222","turnover":6800,"grossprofit":9600,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":1.92},{"userid":2,"username":"用户2","proportion":3,"money":2.88}]},{"customid":24,"name":"李白1","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":25,"name":"李白1","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":23,"name":"李白1","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]}]}
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
         * page_now : 1
         * has_next : true
         * list : [{"customid":32,"name":"王老三","turnover":0,"grossprofit":0,"createtime":"2018/02/13","users":[{"userid":16,"username":"卢西奥","proportion":327.67,"money":0},{"userid":15,"username":"王勇","proportion":327.67,"money":0}]},{"customid":31,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":28,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":30,"name":"李白2222","turnover":0,"grossprofit":3000,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0.6},{"userid":2,"username":"用户2","proportion":3,"money":0.9}]},{"customid":29,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":26,"name":"李白2222","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":27,"name":"李白2222","turnover":6800,"grossprofit":9600,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":1.92},{"userid":2,"username":"用户2","proportion":3,"money":2.88}]},{"customid":24,"name":"李白1","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":25,"name":"李白1","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]},{"customid":23,"name":"李白1","turnover":0,"grossprofit":0,"createtime":"2018/02/12","users":[{"userid":1,"username":"用户1","proportion":2,"money":0},{"userid":2,"username":"用户2","proportion":3,"money":0}]}]
         */

        private int page_now;
        private boolean has_next;
        private List<ListBean> list;

        public int getPage_now() {
            return page_now;
        }

        public void setPage_now(int page_now) {
            this.page_now = page_now;
        }

        public boolean isHas_next() {
            return has_next;
        }

        public void setHas_next(boolean has_next) {
            this.has_next = has_next;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * customid : 32
             * name : 王老三
             * turnover : 0
             * grossprofit : 0
             * createtime : 2018/02/13
             * users : [{"userid":16,"username":"卢西奥","proportion":327.67,"money":0},{"userid":15,"username":"王勇","proportion":327.67,"money":0}]
             */

            private int customid;
            private String name;
            private String turnover;
            private String grossprofit;
            private String createtime;
            private List<UsersBean> users;

            public int getCustomid() {
                return customid;
            }

            public void setCustomid(int customid) {
                this.customid = customid;
            }

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
                 * userid : 16
                 * username : 卢西奥
                 * proportion : 327.67
                 * money : 0
                 */

                private int userid;
                private String username;
                private String proportion;
                private String money;

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

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }
            }
        }
    }
}
