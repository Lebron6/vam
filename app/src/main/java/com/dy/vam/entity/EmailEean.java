package com.dy.vam.entity;

/**
 * @author Rain
 * created:2019/5/31 16:00
 * desc:类作用描述
 */
public class EmailEean {


    /**
     * code : 0
     * msg : 
     * data : {"filename":"费用报销记录_1559289387.xlsx"}
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
         * filename : 费用报销记录_1559289387.xlsx
         */

        private String filename;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}
