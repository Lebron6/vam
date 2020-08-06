package com.dy.vam.entity;

import java.util.List;

/**
 * @author Rain
 * created:2019/7/8 13:54
 * desc:类作用描述
 */
public class s {

    /**
     * code : 0
     * msg :
     * data : {"page_now":1,"has_next":false,"list":[{"headimg":"http://yj.tigerisa.com/uploads/user/head.png","expenseid":1782,"title":"刘辉提交的报销","explain":"车辆使用费,其他","money":623.7,"pay":0,"examine":0,"createtime":"2019.07.04","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NjgsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTk0MTAwNzIwfQ.j1b9umM5HlP9WZTN5pM2SD8PzG0mPTDelwHrLrgy-A8/expenseid/1782.html","examineinfo":{"name":"审批中","color":"#F5A623"}}],"add_url":"http://yj.tigerisa.com/main/reimbursement/add_dep/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NjgsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTk0MTAwNzIwfQ.j1b9umM5HlP9WZTN5pM2SD8PzG0mPTDelwHrLrgy-A8.html"}
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
         * list : [{"headimg":"http://yj.tigerisa.com/uploads/user/head.png","expenseid":1782,"title":"刘辉提交的报销","explain":"车辆使用费,其他","money":623.7,"pay":0,"examine":0,"createtime":"2019.07.04","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NjgsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTk0MTAwNzIwfQ.j1b9umM5HlP9WZTN5pM2SD8PzG0mPTDelwHrLrgy-A8/expenseid/1782.html","examineinfo":{"name":"审批中","color":"#F5A623"}}]
         * add_url : http://yj.tigerisa.com/main/reimbursement/add_dep/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NjgsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTk0MTAwNzIwfQ.j1b9umM5HlP9WZTN5pM2SD8PzG0mPTDelwHrLrgy-A8.html
         */

        private int page_now;
        private boolean has_next;
        private String add_url;
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

        public String getAdd_url() {
            return add_url;
        }

        public void setAdd_url(String add_url) {
            this.add_url = add_url;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * headimg : http://yj.tigerisa.com/uploads/user/head.png
             * expenseid : 1782
             * title : 刘辉提交的报销
             * explain : 车辆使用费,其他
             * money : 623.7
             * pay : 0
             * examine : 0
             * createtime : 2019.07.04
             * url : http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NjgsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTk0MTAwNzIwfQ.j1b9umM5HlP9WZTN5pM2SD8PzG0mPTDelwHrLrgy-A8/expenseid/1782.html
             * examineinfo : {"name":"审批中","color":"#F5A623"}
             */

            private String headimg;
            private int expenseid;
            private String title;
            private String explain;
            private double money;
            private int pay;
            private int examine;
            private String createtime;
            private String url;
            private ExamineinfoBean examineinfo;

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

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

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
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

            public ExamineinfoBean getExamineinfo() {
                return examineinfo;
            }

            public void setExamineinfo(ExamineinfoBean examineinfo) {
                this.examineinfo = examineinfo;
            }

            public static class ExamineinfoBean {
                /**
                 * name : 审批中
                 * color : #F5A623
                 */

                private String name;
                private String color;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }
            }
        }
    }
}
