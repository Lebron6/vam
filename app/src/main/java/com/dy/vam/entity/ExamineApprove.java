package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/23.
 */

public class ExamineApprove {

    /**
     * code : 0
     * msg :
     * data : {"page_now":"1","has_next":false,"list":[{"headimg":"http://192.168.1.14:8081/uploads/user/head.png","taskid":15,"name":"Android","title":"用户1的新增任务","examine":0,"committime":"1970/01/01"},{"headimg":"http://192.168.1.14:8081/uploads/user/head.png","taskid":26,"name":"Android","title":"用户1的新增任务","examine":0,"committime":"1970/01/01"}]}
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
         * list : [{"headimg":"http://192.168.1.14:8081/uploads/user/head.png","taskid":15,"name":"Android","title":"用户1的新增任务","examine":0,"committime":"1970/01/01"},{"headimg":"http://192.168.1.14:8081/uploads/user/head.png","taskid":26,"name":"Android","title":"用户1的新增任务","examine":0,"committime":"1970/01/01"}]
         */

        private String page_now;
        private boolean has_next;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * headimg : http://192.168.1.14:8081/uploads/user/head.png
             * taskid : 15
             * name : Android
             * title : 用户1的新增任务
             * examine : 0
             * committime : 1970/01/01
             */

            private String headimg;
            private int taskid;
            private String name;
            private String title;
            private int examine;
            private int pay;
            private String committime;
            private String createtime;
            private String url;
            private String explain;
            private String money;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
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

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public int getTaskid() {
                return taskid;
            }

            public void setTaskid(int taskid) {
                this.taskid = taskid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getExamine() {
                return examine;
            }

            public void setExamine(int examine) {
                this.examine = examine;
            }

            public String getCommittime() {
                return committime;
            }

            public void setCommittime(String committime) {
                this.committime = committime;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }
        }
    }
}
