package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/13.
 */

public class Users {

    /**
     * code : 0
     * msg :
     * data : [{"letter":"C","list":[{"userid":6,"username":"陈小希","post":"设计师","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]},{"letter":"J","list":[{"userid":13,"username":"接口","post":"接口","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]},{"letter":"L","list":[{"userid":16,"username":"卢西奥","post":"设计师","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]},{"letter":"W","list":[{"userid":15,"username":"王勇","post":"小弟","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]},{"letter":"Y","list":[{"userid":1,"username":"用户1","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png"},{"userid":2,"username":"用户2","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png"},{"userid":3,"username":"用户3","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]},{"letter":"ZZ","list":[{"userid":12,"username":"","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png"},{"userid":14,"username":"","post":"","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]}]
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
         * letter : C
         * list : [{"userid":6,"username":"陈小希","post":"设计师","headimg":"http://192.168.1.14:8081/uploads/user/head.png"}]
         */

        private String letter;
        private List<ListBean> list;

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * userid : 6
             * username : 陈小希
             * post : 设计师
             * headimg : http://192.168.1.14:8081/uploads/user/head.png
             */

            private int userid;
            private String username;
            private String post;
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
        }
    }
}
