package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/24.
 */

public class SummaryDepartmentData {


    /**
     * code : 0
     * msg :
     * data : {"department":[{"departmentid":1,"name":"企划研发中心1","selected":1,"allot":0},{"departmentid":2,"name":"营销中心","selected":0,"allot":1},{"departmentid":23,"name":"Obsv","selected":0,"allot":0}],"role":2,"disposable":2707264.86,"control":453337,"netoutput":2253927.86,"distrubution":1149503.21,"detail_url":"http://yj.tigerisa.com/main/department/income_detail/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NTc4MjU0OTd9.H_J2Y-XK5UhTMjT9U6NB8XM7KrWLR1YSmFuDqnYJ7MA/departmentid/1.html","proportion":"22%","allot_url":"http://yj.tigerisa.com/main/department/task/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NTc4MjU0OTd9.H_J2Y-XK5UhTMjT9U6NB8XM7KrWLR1YSmFuDqnYJ7MA.html","help_url":"http://yj.tigerisa.com/help.html#6","dynamic_status":0,"dynamic_url":"http://yj.tigerisa.com/main/open/dynamic/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NTc4MjU0OTd9.H_J2Y-XK5UhTMjT9U6NB8XM7KrWLR1YSmFuDqnYJ7MA.html"}
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
         * department : [{"departmentid":1,"name":"企划研发中心1","selected":1,"allot":0},{"departmentid":2,"name":"营销中心","selected":0,"allot":1},{"departmentid":23,"name":"Obsv","selected":0,"allot":0}]
         * role : 2
         * disposable : 2707264.86
         * control : 453337
         * netoutput : 2253927.86
         * distrubution : 1149503.21
         * detail_url : http://yj.tigerisa.com/main/department/income_detail/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NTc4MjU0OTd9.H_J2Y-XK5UhTMjT9U6NB8XM7KrWLR1YSmFuDqnYJ7MA/departmentid/1.html
         * proportion : 22%
         * allot_url : http://yj.tigerisa.com/main/department/task/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NTc4MjU0OTd9.H_J2Y-XK5UhTMjT9U6NB8XM7KrWLR1YSmFuDqnYJ7MA.html
         * help_url : http://yj.tigerisa.com/help.html#6
         * dynamic_status : 0
         * dynamic_url : http://yj.tigerisa.com/main/open/dynamic/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MSwiY29tcGFueWlkIjoxLCJleHAiOjE1NTc4MjU0OTd9.H_J2Y-XK5UhTMjT9U6NB8XM7KrWLR1YSmFuDqnYJ7MA.html
         */

        private int role;
        private String disposable;
        private String control;
        private String netoutput;
        private String distrubution;
        private String detail_url;
        private String proportion;
        private String allot_url;
        private String help_url;
        private int dynamic_status;
        private String dynamic_url;
        private String company_distrubution;

        public String getCompany_distrubution() {
            return company_distrubution;
        }

        public void setCompany_distrubution(String company_distrubution) {
            this.company_distrubution = company_distrubution;
        }

        private List<DepartmentBean> department;

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public String getDisposable() {
            return disposable;
        }

        public void setDisposable(String disposable) {
            this.disposable = disposable;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getNetoutput() {
            return netoutput;
        }

        public void setNetoutput(String netoutput) {
            this.netoutput = netoutput;
        }

        public String getDistrubution() {
            return distrubution;
        }

        public void setDistrubution(String distrubution) {
            this.distrubution = distrubution;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getProportion() {
            return proportion;
        }

        public void setProportion(String proportion) {
            this.proportion = proportion;
        }

        public String getAllot_url() {
            return allot_url;
        }

        public void setAllot_url(String allot_url) {
            this.allot_url = allot_url;
        }

        public String getHelp_url() {
            return help_url;
        }

        public void setHelp_url(String help_url) {
            this.help_url = help_url;
        }

        public int getDynamic_status() {
            return dynamic_status;
        }

        public void setDynamic_status(int dynamic_status) {
            this.dynamic_status = dynamic_status;
        }

        public String getDynamic_url() {
            return dynamic_url;
        }

        public void setDynamic_url(String dynamic_url) {
            this.dynamic_url = dynamic_url;
        }

        public List<DepartmentBean> getDepartment() {
            return department;
        }

        public void setDepartment(List<DepartmentBean> department) {
            this.department = department;
        }

        public static class DepartmentBean {
            /**
             * departmentid : 1
             * name : 企划研发中心1
             * selected : 1
             * allot : 0
             */

            private int departmentid;
            private String name;
            private int selected;
            private int allot;

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

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public int getAllot() {
                return allot;
            }

            public void setAllot(int allot) {
                this.allot = allot;
            }
        }
    }
}
