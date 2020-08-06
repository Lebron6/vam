package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/7.
 */

public class Organization {


    /**
     * code : 0
     * msg :
     * data : {"department":[{"departmentid":1,"name":"企划研发中心1","count":3},{"departmentid":2,"name":"营销中心","count":3},{"departmentid":6,"name":"总经办","count":0},{"departmentid":13,"name":"01包装","count":0},{"departmentid":14,"name":"三年二班","count":1}],"user":[{"letter":"J","list":[{"userid":17,"username":"JAMES","headimg":"http://192.168.1.14:8081/uploads/user/head.png","post":"Android"}]},{"letter":"Y","list":[{"userid":1,"username":"用户1","headimg":"http://192.168.1.14:8081/uploads/user/head.png","post":""},{"userid":2,"username":"用户2","headimg":"http://192.168.1.14:8081/uploads/user/head.png","post":"J"},{"userid":3,"username":"用户3","headimg":"http://192.168.1.14:8081/uploads/user/head.png","post":""}]},{"letter":"Z","list":[{"userid":18,"username":"周杰伦","headimg":"http://192.168.1.14:8081/uploads/user/head.png","post":"酱油"}]},{"letter":"ZZ","list":[{"userid":12,"username":"","headimg":"http://192.168.1.14:8081/uploads/user/121519956652.png","post":""},{"userid":14,"username":"","headimg":"http://192.168.1.14:8081/uploads/user/141520923258.png","post":"Android"}]}]}
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
        private String company;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        private List<DepartmentBean> department;
        private List<UserBean> user;

        public List<DepartmentBean> getDepartment() {
            return department;
        }

        public void setDepartment(List<DepartmentBean> department) {
            this.department = department;
        }

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public static class DepartmentBean {
            /**
             * departmentid : 1
             * name : 企划研发中心1
             * count : 3
             */

            private int departmentid;
            private String name;
            private int count;

            public int getDepartmentid() {
                return departmentid;
            }

            public void setDepartmentid(int departmentid) {
                this.departmentid = departmentid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class UserBean {
            /**
             * letter : J
             * list : [{"userid":17,"username":"JAMES","headimg":"http://192.168.1.14:8081/uploads/user/head.png","post":"Android"}]
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
                 * userid : 17
                 * username : JAMES
                 * headimg : http://192.168.1.14:8081/uploads/user/head.png
                 * post : Android
                 */

                private int userid;
                private String username;
                private String headimg;
                private String post;

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

                public String getPost() {
                    return post;
                }

                public void setPost(String post) {
                    this.post = post;
                }
            }
        }
    }
}
