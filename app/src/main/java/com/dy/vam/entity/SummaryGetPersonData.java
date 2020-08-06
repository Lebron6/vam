package com.dy.vam.entity;

/**
 * Created by James on 2018/3/1.
 */

public class SummaryGetPersonData {
    /**
     * code : 0
     * msg :
     * data : {"user":{"username":"用户1","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png","role":0,"allottype":0,"task_url":"http://192.68.1.14:8081/main/task/person_task/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html"},"value":{"total":0,"allot":0,"noallot":0,"url":"http://192.168.1.14:8081/main/person/value_detail/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html"},"commission":{"total":2.52,"allot":0,"noallot":2.52,"url":"http://192.168.1.14:8081/main/person/commission_detail/token/eyJ0eXAiOiJV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html"}}
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
         * user : {"username":"用户1","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png","role":0,"allottype":0,"task_url":"http://192.68.1.14:8081/main/task/person_task/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html"}
         * value : {"total":0,"allot":0,"noallot":0,"url":"http://192.168.1.14:8081/main/person/value_detail/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html"}
         * commission : {"total":2.52,"allot":0,"noallot":2.52,"url":"http://192.168.1.14:8081/main/person/commission_detail/token/eyJ0eXAiOiJV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html"}
         */

        private UserBean user;
        private ValueBean value;
        private CommissionBean commission;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public ValueBean getValue() {
            return value;
        }

        public void setValue(ValueBean value) {
            this.value = value;
        }

        public CommissionBean getCommission() {
            return commission;
        }

        public void setCommission(CommissionBean commission) {
            this.commission = commission;
        }

        public static class UserBean {
            /**
             * username : 用户1
             * post :
             * headimg : http://192.168.1.14:8081/uploads/user/head.png
             * role : 0
             * allottype : 0
             * task_url : http://192.68.1.14:8081/main/task/person_task/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html
             */

            private String username;
            private String post;
            private String headimg;
            private int role;
            private int allottype;
            private String task_url;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPost() {
                return post;
            }

            public void setPost(String post) {
                this.post = post;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public int getAllottype() {
                return allottype;
            }

            public void setAllottype(int allottype) {
                this.allottype = allottype;
            }

            public String getTask_url() {
                return task_url;
            }

            public void setTask_url(String task_url) {
                this.task_url = task_url;
            }
        }

        public static class ValueBean {
            /**
             * total : 0
             * allot : 0
             * noallot : 0
             * url : http://192.168.1.14:8081/main/person/value_detail/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html
             */

            private String total;
            private String allot;
            private String noallot;
            private String url;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getAllot() {
                return allot;
            }

            public void setAllot(String allot) {
                this.allot = allot;
            }

            public String getNoallot() {
                return noallot;
            }

            public void setNoallot(String noallot) {
                this.noallot = noallot;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class CommissionBean {
            /**
             * total : 2.52
             * allot : 0
             * noallot : 2.52
             * url : http://192.168.1.14:8081/main/person/commission_detail/token/eyJ0eXAiOiJV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NDg3Mjg1NDB9.2sSgJ_IFqFz9GDU6biNNb23adTvdQFK8tohrLjPCZSI/year/2018.html
             */

            private String total;
            private String allot;
            private String noallot;
            private String url;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getAllot() {
                return allot;
            }

            public void setAllot(String allot) {
                this.allot = allot;
            }

            public String getNoallot() {
                return noallot;
            }

            public void setNoallot(String noallot) {
                this.noallot = noallot;
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
