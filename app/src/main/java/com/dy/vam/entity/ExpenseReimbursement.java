package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/23.
 */

public class ExpenseReimbursement {

    /**
     * code : 0
     * msg :
     * data : {"page_now":"1","has_next":false,"list":[{"headimg":"http://192.168.1.14:8081/uploads/user/121519956652.png","expenseid":125,"title":"的办公费报销","explain":"2","examine":0,"createtime":"2018.03.13","url":"http://192.168.1.14:8081/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTUzODQ0MDM4fQ.5wD4h4EdOGxavlLMy8TveSBz3fMitcGYKojLepndrPA/expenseid/125.html"},{"headimg":"http://192.168.1.14:8081/uploads/user/121519956652.png","expenseid":122,"title":"的办公费报销","explain":"2","examine":0,"createtime":"2018.03.13","url":"http://192.168.1.14:8081/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTUzODQ0MDM4fQ.5wD4h4EdOGxavlLMy8TveSBz3fMitcGYKojLepndrPA/expenseid/122.html"}],"add_url":"http://192.168.1.14:8081/main/reimbursement/add/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTUzODQ0MDM4fQ.5wD4h4EdOGxavlLMy8TveSBz3fMitcGYKojLepndrPA.html"}
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
         * list : [{"headimg":"http://192.168.1.14:8081/uploads/user/121519956652.png","expenseid":125,"title":"的办公费报销","explain":"2","examine":0,"createtime":"2018.03.13","url":"http://192.168.1.14:8081/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTUzODQ0MDM4fQ.5wD4h4EdOGxavlLMy8TveSBz3fMitcGYKojLepndrPA/expenseid/125.html"},{"headimg":"http://192.168.1.14:8081/uploads/user/121519956652.png","expenseid":122,"title":"的办公费报销","explain":"2","examine":0,"createtime":"2018.03.13","url":"http://192.168.1.14:8081/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTUzODQ0MDM4fQ.5wD4h4EdOGxavlLMy8TveSBz3fMitcGYKojLepndrPA/expenseid/122.html"}]
         * add_url : http://192.168.1.14:8081/main/reimbursement/add/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInJvbGUiOjEsImNvbXBhbnlpZCI6MSwiZXhwIjoxNTUzODQ0MDM4fQ.5wD4h4EdOGxavlLMy8TveSBz3fMitcGYKojLepndrPA.html
         */

        private String page_now;
        private boolean has_next;
        private String add_url;
        private List<ListBean> list;

        public String getPage_now() {
            return page_now;
        }

        public void setPage_now(String page_now) {
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
             * headimg : http://yj.tigerisa.com/uploads/user/371557902964.png
             * expenseid : 1774
             * title : 黄金提交的报销
             * explain : 办公费
             * money : 5778
             * pay : 0
             * examine : 2
             * createtime : 2019.06.24
             * url : http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzcsInJvbGUiOjUsImNvbXBhbnlpZCI6OCwiZXhwIjoxNTkxMDkyMDU0fQ.bE7-uG1IVO3q3iHpAt3Wnh0ydcWDIS1p5y45vj7V-2U/expenseid/1774.html
             * examineinfo : {"name":"审批不通过","color":"#C7433D"}
             */

            private String headimg;
            private int expenseid;
            private String title;
            private String explain;
            private String money;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
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
                 * name : 审批不通过
                 * color : #C7433D
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
