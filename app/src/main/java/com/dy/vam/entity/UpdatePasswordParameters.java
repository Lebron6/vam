package com.dy.vam.entity;

/**
 * Created by James on 2018/2/6.
 */

public class UpdatePasswordParameters {
    /**
     * code : 0
     * msg :
     * data : {"userid":"8","verify":"BvmxbGVq3dkkvGo069Nu"}
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

    @Override
    public String toString() {
        return "UpdatePasswordParameters{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "userid='" + userid + '\'' +
                    ", verify='" + verify + '\'' +
                    '}';
        }

        /**
         * userid : 8
         * verify : BvmxbGVq3dkkvGo069Nu
         */

        private String userid;
        private String verify;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getVerify() {
            return verify;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }
    }
}
