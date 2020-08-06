package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/8.
 */

public class GetCommissionData {
    /**
     * code : 0
     * msg :
     * data : {"years":[{"year":2017,"selected":0},{"year":2018,"selected":1}],"custom":[{"name":"李白2222","turnover":68,"grossprofit":96,"users":[{"cuid":62,"username":"用户1","proportion":"2%","money":1.92,"isallot":0},{"cuid":63,"username":"用户2","proportion":"3%","money":2.88,"isallot":0}]},{"name":"李白2222","turnover":0,"grossprofit":30,"users":[{"cuid":68,"username":"用户361","proportion":"2%","money":0.6,"isallot":0},{"cuid":69,"username":"用户2","proportion":"3%","money":0.9,"isallot":0}]}]}
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
        private List<YearsBean> years;
        private List<CustomBean> custom;
        private int quarter;

        public int getQuarter() {
            return quarter;
        }

        public void setQuarter(int quarter) {
            this.quarter = quarter;
        }

        public List<YearsBean> getYears() {
            return years;
        }

        public void setYears(List<YearsBean> years) {
            this.years = years;
        }

        public List<CustomBean> getCustom() {
            return custom;
        }

        public void setCustom(List<CustomBean> custom) {
            this.custom = custom;
        }

        public static class YearsBean {
            /**
             * year : 2017
             * selected : 0
             */

            private int year;
            private int selected;

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }
        }

        public static class CustomBean {
            /**
             * name : 李白2222
             * turnover : 68
             * grossprofit : 96
             * users : [{"cuid":62,"username":"用户1","proportion":"2%","money":1.92,"isallot":0},{"cuid":63,"username":"用户2","proportion":"3%","money":2.88,"isallot":0}]
             */

            private String name;
            private String turnover;
            private String grossprofit;
            private List<UsersBean> users;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTurnover() {
                return turnover;
            }

            public void setTurnover(String turnover) {
                this.turnover = turnover;
            }

            public String getGrossprofit() {
                return grossprofit;
            }

            public void setGrossprofit(String grossprofit) {
                this.grossprofit = grossprofit;
            }

            public List<UsersBean> getUsers() {
                return users;
            }

            public void setUsers(List<UsersBean> users) {
                this.users = users;
            }

            public static class UsersBean {
                /**
                 * cuid : 62
                 * username : 用户1
                 * proportion : 2%
                 * money : 1.92
                 * isallot : 0
                 */

                private int cuid;
                private String username;
                private String proportion;
                private String money;
                private String isallot;

                public int getCuid() {
                    return cuid;
                }

                public void setCuid(int cuid) {
                    this.cuid = cuid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getProportion() {
                    return proportion;
                }

                public void setProportion(String proportion) {
                    this.proportion = proportion;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getIsallot() {
                    return isallot;
                }

                public void setIsallot(String isallot) {
                    this.isallot = isallot;
                }

                public int type;
                public static final int TYPE_CHECKED = 1;
                public static final int TYPE_NOCHECKED = 0;
            }
        }
    }
}
