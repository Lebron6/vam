package com.dy.vam.entity;

/**
 * Created by James on 2018/3/22.
 */

public class GetStarInfo {

    /**
     * code : 0
     * msg :
     * data : {"taskid":15,"name":"testteststsetsetsetset"," username":"用户 1"}
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
         * taskid : 15
         * name : testteststsetsetsetset
         *  username : 用户 1
         */

        private int taskid;
        private String name;
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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
    }
}
