package com.dy.vam.entity;

/**
 * Created by James on 2018/4/25.
 */
public class VersionBean {


    /**
     * code : 0
     * msg :
     * data : {"version":"1.0","download":"http://oi.tigeric.com/uploads/download/tigerichaitou.apk"}
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
         * version : 1.0
         * download : http://oi.tigeric.com/uploads/download/tigerichaitou.apk
         */

        private String version;
        private String download;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }
    }
}
