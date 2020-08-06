package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/12.
 */

public class FinancialGetCustomList {
    /**
     * code : 0
     * msg :
     * data : {"page_now":1,"has_next":false,"list":[{"customid":4,"name":"杭州网易","turnover":100000000,"grossprofit":50000000,"createtime":"2018/01/29","username":"用户 1"}]}
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
         * has_next : false
         * list : [{"customid":4,"name":"杭州网易","turnover":100000000,"grossprofit":50000000,"createtime":"2018/01/29","username":"用户 1"}]
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
             * customid : 4
             * name : 杭州网易
             * turnover : 100000000
             * grossprofit : 50000000
             * createtime : 2018/01/29
             * username : 用户 1
             */

            private int customid;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
