package com.dy.vam.entity;

/**
 * Created by James on 2018/3/22.
 */

public class ConfirmTaskOver {
    /**
     * code : 0
     * msg :
     * data : {"star":0}
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
         * star : 0
         */

        private int star;

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }
    }
}
