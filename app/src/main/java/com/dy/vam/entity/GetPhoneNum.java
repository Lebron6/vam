package com.dy.vam.entity;

/**
 * Created by James on 2018/3/2.
 */

public class GetPhoneNum {
    /**
     * code : 0
     * msg :
     * data : {"phone_view":"158****4682","phone_actual":"15850524682"}
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
         * phone_view : 158****4682
         * phone_actual : 15850524682
         */

        private String phone_view;
        private String phone_actual;

        public String getPhone_view() {
            return phone_view;
        }

        public void setPhone_view(String phone_view) {
            this.phone_view = phone_view;
        }

        public String getPhone_actual() {
            return phone_actual;
        }

        public void setPhone_actual(String phone_actual) {
            this.phone_actual = phone_actual;
        }
    }
}
