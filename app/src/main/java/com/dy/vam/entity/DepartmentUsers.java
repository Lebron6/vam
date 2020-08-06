package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/9.
 */

public class DepartmentUsers {
    /**
     * code : 0
     * msg :
     * data : [{"userid":1,"username":"用户1","headimg":"http://192.168.1.14:8081/uploads/user/head.png"},{"userid":2,"username":"用户2","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : 1
         * username : 用户1
         * headimg : http://192.168.1.14:8081/uploads/user/head.png
         */

        private int userid;
        private String username;
        private String headimg;

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

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
    }
}
