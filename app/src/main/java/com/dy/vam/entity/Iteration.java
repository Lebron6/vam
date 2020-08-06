package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/22.
 */

public class Iteration {
    /**
     * code : 0
     * msg :
     * data : [{"sort":1,"info":"闪电发货当房间都已经","createtime":"03/2017:49"},{"sort":2,"info":"asdfagafghsdhfgjxfgjn","createtime":"03/20 18:00"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sort : 1
         * info : 闪电发货当房间都已经
         * createtime : 03/2017:49
         */

        private int sort;
        private String info;
        private String createtime;

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
