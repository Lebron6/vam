package com.dy.vam.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by James on 2018/2/24.
 */

public class SummaryGetDataChart {
    /**
     * code : 0
     * msg :
     * data : {"1":1.11,"2":1.11,"3":1.11,"4":1.11,"5":1.11,"6":1.11,"7":1.11,"8":1.11,"9":1.11,"10":1.11,"11":1.11,"12":-1.11}
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
         * 1 : 1.11
         * 2 : 1.11
         * 3 : 1.11
         * 4 : 1.11
         * 5 : 1.11
         * 6 : 1.11
         * 7 : 1.11
         * 8 : 1.11
         * 9 : 1.11
         * 10 : 1.11
         * 11 : 1.11
         * 12 : -1.11
         */

        @SerializedName("1")
        private double _$1;
        @SerializedName("2")
        private double _$2;
        @SerializedName("3")
        private double _$3;
        @SerializedName("4")
        private double _$4;
        @SerializedName("5")
        private double _$5;
        @SerializedName("6")
        private double _$6;
        @SerializedName("7")
        private double _$7;
        @SerializedName("8")
        private double _$8;
        @SerializedName("9")
        private double _$9;
        @SerializedName("10")
        private double _$10;
        @SerializedName("11")
        private double _$11;
        @SerializedName("12")
        private double _$12;

        public double get_$1() {
            return _$1;
        }

        public void set_$1(double _$1) {
            this._$1 = _$1;
        }

        public double get_$2() {
            return _$2;
        }

        public void set_$2(double _$2) {
            this._$2 = _$2;
        }

        public double get_$3() {
            return _$3;
        }

        public void set_$3(double _$3) {
            this._$3 = _$3;
        }

        public double get_$4() {
            return _$4;
        }

        public void set_$4(double _$4) {
            this._$4 = _$4;
        }

        public double get_$5() {
            return _$5;
        }

        public void set_$5(double _$5) {
            this._$5 = _$5;
        }

        public double get_$6() {
            return _$6;
        }

        public void set_$6(double _$6) {
            this._$6 = _$6;
        }

        public double get_$7() {
            return _$7;
        }

        public void set_$7(double _$7) {
            this._$7 = _$7;
        }

        public double get_$8() {
            return _$8;
        }

        public void set_$8(double _$8) {
            this._$8 = _$8;
        }

        public double get_$9() {
            return _$9;
        }

        public void set_$9(double _$9) {
            this._$9 = _$9;
        }

        public double get_$10() {
            return _$10;
        }

        public void set_$10(double _$10) {
            this._$10 = _$10;
        }

        public double get_$11() {
            return _$11;
        }

        public void set_$11(double _$11) {
            this._$11 = _$11;
        }

        public double get_$12() {
            return _$12;
        }

        public void set_$12(double _$12) {
            this._$12 = _$12;
        }
    }
}
