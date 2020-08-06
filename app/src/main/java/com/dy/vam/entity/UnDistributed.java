package com.dy.vam.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by James on 2018/4/4.
 */

public class UnDistributed {
    /**
     * code : 0
     * msg :
     * data : {"3":[{"username":"用户1","money":385327.3},{"username":"用户 2","money":192663.64},{"username":"陈小希","money":192663.64},{"username":"接口","money":192663.64},{"username":"","money":192663.64},{"username":"用户3","money":0},{"username":"","money":0},{"username":"卢西奥","money":0},{"username":"王勇","money":0}],"4":[{"username":"用户1","money":591769.75},{"username":"用户 2","money":295884.86},{"username":"陈小希","money":295884.86},{"username":"接口","money":295884.86},{"username":"","money":295884.86},{"username":"用户3","money":0},{"username":"","money":0},{"username":"卢西奥","money":0},{"username":"王勇","money":0}],"1":[],"2":[]}
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
        @SerializedName("3")
        private List<_$3Bean> _$3;
        @SerializedName("4")
        private List<_$4Bean> _$4;
        @SerializedName("1")
        private List<_$1Bean> _$1;
        @SerializedName("2")
        private List<_$2Bean> _$2;

        public List<_$3Bean> get_$3() {
            return _$3;
        }

        public void set_$3(List<_$3Bean> _$3) {
            this._$3 = _$3;
        }

        public List<_$4Bean> get_$4() {
            return _$4;
        }

        public void set_$4(List<_$4Bean> _$4) {
            this._$4 = _$4;
        }

        public List<_$1Bean> get_$1() {
            return _$1;
        }

        public void set_$1(List<_$1Bean> _$1) {
            this._$1 = _$1;
        }

        public List<_$2Bean> get_$2() {
            return _$2;
        }

        public void set_$2(List<_$2Bean> _$2) {
            this._$2 = _$2;
        }
        public static class _$1Bean {
            /**
             * username : 用户1
             * money : 385327.3
             */

            private String username;
            private String money;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

        public static class _$2Bean {
            /**
             * username : 用户1
             * money : 385327.3
             */

            private String username;
            private String money;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

        public static class _$3Bean {
            /**
             * username : 用户1
             * money : 385327.3
             */

            private String username;
            private String money;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

        public static class _$4Bean {
            /**
             * username : 用户1
             * money : 591769.75
             */

            private String username;
            private String money;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
