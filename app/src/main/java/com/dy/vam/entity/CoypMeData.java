package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/2.
 */

public class CoypMeData {
    /**
     * code : 0
     * msg :
     * data : {"page_now":1,"has_next":false,"list":[{"expenseid":3,"title":"用户 1 的差旅费报销","explain":"trhsfh","examine":1,"createtime":"2018.02.05","url":"http://192.168.1.14:8081/main/expense/show_info/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/expenseid/3.html"}]}
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
         * list : [{"expenseid":3,"title":"用户 1 的差旅费报销","explain":"trhsfh","examine":1,"createtime":"2018.02.05","url":"http://192.168.1.14:8081/main/expense/show_info/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/expenseid/3.html"}]
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
             * expenseid : 3
             * title : 用户 1 的差旅费报销
             * explain : trhsfh
             * examine : 1
             * createtime : 2018.02.05
             * url : http://192.168.1.14:8081/main/expense/show_info/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/expenseid/3.html
             */

            private int expenseid;
            private String title;
            private String explain;
            private int examine;
            private String createtime;
            private String url;

            public int getExpenseid() {
                return expenseid;
            }

            public void setExpenseid(int expenseid) {
                this.expenseid = expenseid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public int getExamine() {
                return examine;
            }

            public void setExamine(int examine) {
                this.examine = examine;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
