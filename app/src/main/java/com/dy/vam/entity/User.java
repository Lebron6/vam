package com.dy.vam.entity;

/**
 * Created by Administrator on 2017/12/19.
 */

public class User {
    @Override
    public String toString() {
        return "User{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * code : 0
     * msg :
     * data : {"id":1,"username":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNTQ3NTQyMDE2fQ.k8B6gaevWoYUzABgTCzYmL0D3J8p5An_A2d4F_W7ig"}
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
         * id : 1
         * username :
         * headimg : http://192.168.1.14:8081/uploads/user/head.png
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNTQ3NTQyMDE2fQ.k8B6gaevWoYUzABgTCzYmL0D3J8p5An_A2d4F_W7ig
         */

        private int id;
        private String username;
        private String headimg;
        private String token;
        private int companyid;

        public int getCompanyid() {
            return companyid;
        }

        public void setCompanyid(int companyid) {
            this.companyid = companyid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", headimg='" + headimg + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}
