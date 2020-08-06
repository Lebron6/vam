package com.dy.vam.entity;

import java.io.Serializable;

/**
 * Created by James on 2018/4/23.
 */

public class UpLoadReimbursementPicResult  implements Serializable{
    /**
     * code : 0
     * msg :
     * data : []
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
    public static class DataBean implements Serializable{
        @Override
        public String toString() {
            return "DataBean{" +
                    "imgid='" + imgid + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        private String imgid;

        public String getImgid() {
            return imgid;
        }

        public void setImgid(String imgid) {
            this.imgid = imgid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url ;}
}
