package com.dy.vam.entity;

/**
 * Created by James on 2018/2/9.
 */

public class DepartStaffDetails {

    /**
     * code : 0
     * msg :
     * data : {"username":"JAMES","headimg":"http://192.168.1.14:8081/uploads/user/head.png","phone":"13921770205","company":"大洋供应链","departmentid":1,"department":"企划研发中心1","post":"iOS","assess_money":0,"commission":0,"isdirector":0}
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
         * username : JAMES
         * headimg : http://192.168.1.14:8081/uploads/user/head.png
         * phone : 13921770205
         * company : 大洋供应链
         * departmentid : 1
         * department : 企划研发中心1
         * post : iOS
         * assess_money : 0
         * commission : 0
         * isdirector : 0
         */

        private String username;
        private String headimg;
        private String phone;
        private String company;
        private int departmentid;
        private String department;
        private String post;
        private String assess_money;
        private String commission;
        private int isdirector;
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(int departmentid) {
            this.departmentid = departmentid;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getAssess_money() {
            return assess_money;
        }

        public void setAssess_money(String assess_money) {
            this.assess_money = assess_money;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public int getIsdirector() {
            return isdirector;
        }

        public void setIsdirector(int isdirector) {
            this.isdirector = isdirector;
        }
    }
}
