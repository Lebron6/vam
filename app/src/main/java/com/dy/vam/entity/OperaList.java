package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/2/7.
 */

public class OperaList {
    /**
     * code : 0
     * msg :
     * data : {"operation":[{"title":"审批","tab":"examine","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"财务录入","tab":"financial","icon":"http://192.168.1.14:8081/static/images/icon.png"}],"manage":[{"title":"组织构","tab":"manage_organization","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"添加新员工","tab":"manage_addemployee","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"部门分配设置","tab":"manage_department","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"报销管理","tab":"manage_reimbursement","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"权限分配7","tab":"manage_authority","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"年度分配系数","tab":"manage_partition","icon":"http://192.168.1.14:8081/static/images/icon.png"}],"view":[{"title":"财务数据","tab":"view_financial","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"价值分配","tab":"view_distribution","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"组织架构","tab":"view_organization","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"部门分配比例","tab":"view_departments","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"报销流程","tab":"view_reimbursement","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"权限分配","tab":"view_authority","icon":"http://192.168.1.14:8081/static/images/icon.png"},{"title":"年度分配系数","tab":"view_partition","icon":"http://192.168.1.14:8081/static/images/icon.png"}]}
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
        private int examine_count;

        public int getExamine_count() {
            return examine_count;
        }

        public void setExamine_count(int examine_count) {
            this.examine_count = examine_count;
        }

        private List<OperationBean> operation;
        private List<ManageBean> manage;
        private List<ViewBean> view;

        public List<OperationBean> getOperation() {
            return operation;
        }

        public void setOperation(List<OperationBean> operation) {
            this.operation = operation;
        }

        public List<ManageBean> getManage() {
            return manage;
        }

        public void setManage(List<ManageBean> manage) {
            this.manage = manage;
        }

        public List<ViewBean> getView() {
            return view;
        }

        public void setView(List<ViewBean> view) {
            this.view = view;
        }

        public static class OperationBean {
            /**
             * title : 审批
             * tab : examine
             * icon : http://192.168.1.14:8081/static/images/icon.png
             */

            private String title;
            private String tab;
            private String icon;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class ManageBean {
            /**
             * title : 组织构
             * tab : manage_organization
             * icon : http://192.168.1.14:8081/static/images/icon.png
             */

            private String title;
            private String tab;
            private String icon;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class ViewBean {
            /**
             * title : 财务数据
             * tab : view_financial
             * icon : http://192.168.1.14:8081/static/images/icon.png
             */

            private String title;
            private String tab;
            private String icon;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
