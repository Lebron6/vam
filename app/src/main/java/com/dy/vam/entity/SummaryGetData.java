package com.dy.vam.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by James on 2018/2/23.
 */

public class SummaryGetData {

    /**
     * code : 0
     * msg :
     * data : {"user":{"headimg":"http://192.168.1.14:8081/uploads/user/head.png","distribution":1000,"company":"大洋供应链","department":"企划研发中心1"},"income":{"list":{"11":1068.6,"12":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0},"sum":1068.6},"output":{"list":{"11":293.56,"12":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0},"sum":293.56},"control":{"sum":775.03,"list":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":775.03,"12":0}},"trends":{"time":"2018/02/23","list":[{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"}]},"years":[{"year":2017,"selected":1},{"year":2018,"selected":0}]}
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
         * user : {"headimg":"http://192.168.1.14:8081/uploads/user/head.png","distribution":1000,"company":"大洋供应链","department":"企划研发中心1"}
         * income : {"list":{"11":1068.6,"12":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0},"sum":1068.6}
         * output : {"list":{"11":293.56,"12":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0},"sum":293.56}
         * control : {"sum":775.03,"list":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":775.03,"12":0}}
         * trends : {"time":"2018/02/23","list":[{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"}]}
         * years : [{"year":2017,"selected":1},{"year":2018,"selected":0}]
         */

        private UserBean user;
        private IncomeBean income;
        private OutputBean output;
        private ControlBean control;
        private TrendsBean trends;
        private List<YearsBean> years;
        private  String help_url;
        private  String dynamic_url;


        public String getDynamic_url() {
            return dynamic_url;
        }

        public void setDynamic_url(String dynamic_url) {
            this.dynamic_url = dynamic_url;
        }

        public String getHelp_url() {
            return help_url;
        }

        public void setHelp_url(String help_url) {
            this.help_url = help_url;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public IncomeBean getIncome() {
            return income;
        }

        public void setIncome(IncomeBean income) {
            this.income = income;
        }

        public OutputBean getOutput() {
            return output;
        }

        public void setOutput(OutputBean output) {
            this.output = output;
        }

        public ControlBean getControl() {
            return control;
        }

        public void setControl(ControlBean control) {
            this.control = control;
        }

        public TrendsBean getTrends() {
            return trends;
        }

        public void setTrends(TrendsBean trends) {
            this.trends = trends;
        }

        public List<YearsBean> getYears() {
            return years;
        }

        public void setYears(List<YearsBean> years) {
            this.years = years;
        }

        public static class UserBean {
            /**
             * headimg : http://192.168.1.14:8081/uploads/user/head.png
             * distribution : 1000
             * company : 大洋供应链
             * department : 企划研发中心1
             */

            private String headimg;
            private String distribution;
            private String company;
            private String department;
            private int role;
            private  int notice_count;
            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getDistribution() {
                return distribution;
            }

            public void setDistribution(String distribution) {
                this.distribution = distribution;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public int getNotice_count() {
                return notice_count;
            }

            public void setNotice_count(int notice_count) {
                this.notice_count = notice_count;
            }
        }

        public static class IncomeBean {
            /**
             * list : {"11":1068.6,"12":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0}
             * sum : 1068.6
             */

            private ListBean list;
            private double sum;

            public ListBean getList() {
                return list;
            }

            public void setList(ListBean list) {
                this.list = list;
            }

            public double getSum() {
                return sum;
            }

            public void setSum(double sum) {
                this.sum = sum;
            }

            public static class ListBean {
                /**
                 * 11 : 1068.6
                 * 12 : 0
                 * 1 : 0
                 * 2 : 0
                 * 3 : 0
                 * 4 : 0
                 * 5 : 0
                 * 6 : 0
                 * 7 : 0
                 * 8 : 0
                 * 9 : 0
                 * 10 : 0
                 */

                @SerializedName("11")
                private double _$11;
                @SerializedName("12")
                private double _$12;
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

                public double get_$11() {
                    return _$11;
                }

                public void set_$11(double _$11) {
                    this._$11 = _$11;
                }

                public Double get_$12() {
                    return _$12;
                }

                public void set_$12(Double _$12) {
                    this._$12 = _$12;
                }

                public Double get_$1() {
                    return _$1;
                }

                public void set_$1(Double _$1) {
                    this._$1 = _$1;
                }

                public Double get_$2() {
                    return _$2;
                }

                public void set_$2(Double _$2) {
                    this._$2 = _$2;
                }

                public Double get_$3() {
                    return _$3;
                }

                public void set_$3(Double _$3) {
                    this._$3 = _$3;
                }

                public Double get_$4() {
                    return _$4;
                }

                public void set_$4(Double _$4) {
                    this._$4 = _$4;
                }

                public Double get_$5() {
                    return _$5;
                }

                public void set_$5(Double _$5) {
                    this._$5 = _$5;
                }

                public Double get_$6() {
                    return _$6;
                }

                public void set_$6(Double _$6) {
                    this._$6 = _$6;
                }

                public Double get_$7() {
                    return _$7;
                }

                public void set_$7(Double _$7) {
                    this._$7 = _$7;
                }

                public Double get_$8() {
                    return _$8;
                }

                public void set_$8(Double _$8) {
                    this._$8 = _$8;
                }

                public Double get_$9() {
                    return _$9;
                }

                public void set_$9(Double _$9) {
                    this._$9 = _$9;
                }

                public Double get_$10() {
                    return _$10;
                }

                public void set_$10(int _$10) {
                    this._$10 = _$10;
                }
            }
        }

        public static class OutputBean {
            /**
             * list : {"11":293.56,"12":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0}
             * sum : 293.56
             */

            private ListBeanX list;
            private double sum;

            public ListBeanX getList() {
                return list;
            }

            public void setList(ListBeanX list) {
                this.list = list;
            }

            public double getSum() {
                return sum;
            }

            public void setSum(double sum) {
                this.sum = sum;
            }

            public static class ListBeanX {
                /**
                 * 11 : 293.56
                 * 12 : 0
                 * 1 : 0
                 * 2 : 0
                 * 3 : 0
                 * 4 : 0
                 * 5 : 0
                 * 6 : 0
                 * 7 : 0
                 * 8 : 0
                 * 9 : 0
                 * 10 : 0
                 */

                @SerializedName("11")
                private double _$11;
                @SerializedName("12")
                private Double _$12;
                @SerializedName("1")
                private Double _$1;
                @SerializedName("2")
                private Double _$2;
                @SerializedName("3")
                private Double _$3;
                @SerializedName("4")
                private Double _$4;
                @SerializedName("5")
                private Double _$5;
                @SerializedName("6")
                private Double _$6;
                @SerializedName("7")
                private Double _$7;
                @SerializedName("8")
                private Double _$8;
                @SerializedName("9")
                private Double _$9;
                @SerializedName("10")
                private Double _$10;

                public double get_$11() {
                    return _$11;
                }

                public void set_$11(double _$11) {
                    this._$11 = _$11;
                }

                public Double get_$12() {
                    return _$12;
                }

                public void set_$12(Double _$12) {
                    this._$12 = _$12;
                }

                public Double get_$1() {
                    return _$1;
                }

                public void set_$1(Double _$1) {
                    this._$1 = _$1;
                }

                public Double get_$2() {
                    return _$2;
                }

                public void set_$2(Double _$2) {
                    this._$2 = _$2;
                }

                public Double get_$3() {
                    return _$3;
                }

                public void set_$3(Double _$3) {
                    this._$3 = _$3;
                }

                public Double get_$4() {
                    return _$4;
                }

                public void set_$4(Double _$4) {
                    this._$4 = _$4;
                }

                public Double get_$5() {
                    return _$5;
                }

                public void set_$5(Double _$5) {
                    this._$5 = _$5;
                }

                public Double get_$6() {
                    return _$6;
                }

                public void set_$6(Double _$6) {
                    this._$6 = _$6;
                }

                public Double get_$7() {
                    return _$7;
                }

                public void set_$7(Double _$7) {
                    this._$7 = _$7;
                }

                public Double get_$8() {
                    return _$8;
                }

                public void set_$8(Double _$8) {
                    this._$8 = _$8;
                }

                public Double get_$9() {
                    return _$9;
                }

                public void set_$9(Double _$9) {
                    this._$9 = _$9;
                }

                public Double get_$10() {
                    return _$10;
                }

                public void set_$10(Double _$10) {
                    this._$10 = _$10;
                }
            }
        }

        public static class ControlBean {
            /**
             * sum : 775.03
             * list : {"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":775.03,"12":0}
             */

            private double sum;
            private ListBeanXX list;

            public double getSum() {
                return sum;
            }

            public void setSum(double sum) {
                this.sum = sum;
            }

            public ListBeanXX getList() {
                return list;
            }

            public void setList(ListBeanXX list) {
                this.list = list;
            }

            public static class ListBeanXX {
                /**
                 * 1 : 0
                 * 2 : 0
                 * 3 : 0
                 * 4 : 0
                 * 5 : 0
                 * 6 : 0
                 * 7 : 0
                 * 8 : 0
                 * 9 : 0
                 * 10 : 0
                 * 11 : 775.03
                 * 12 : 0
                 */

                @SerializedName("1")
                private Double _$1;
                @SerializedName("2")
                private Double _$2;
                @SerializedName("3")
                private Double _$3;
                @SerializedName("4")
                private Double _$4;
                @SerializedName("5")
                private Double _$5;
                @SerializedName("6")
                private Double _$6;
                @SerializedName("7")
                private Double _$7;
                @SerializedName("8")
                private Double _$8;
                @SerializedName("9")
                private Double _$9;
                @SerializedName("10")
                private Double _$10;
                @SerializedName("11")
                private double _$11;
                @SerializedName("12")
                private Double _$12;

                public Double get_$1() {
                    return _$1;
                }

                public void set_$1(Double _$1) {
                    this._$1 = _$1;
                }

                public Double get_$2() {
                    return _$2;
                }

                public void set_$2(Double _$2) {
                    this._$2 = _$2;
                }

                public Double get_$3() {
                    return _$3;
                }

                public void set_$3(Double _$3) {
                    this._$3 = _$3;
                }

                public Double get_$4() {
                    return _$4;
                }

                public void set_$4(Double _$4) {
                    this._$4 = _$4;
                }

                public Double get_$5() {
                    return _$5;
                }

                public void set_$5(Double _$5) {
                    this._$5 = _$5;
                }

                public Double get_$6() {
                    return _$6;
                }

                public void set_$6(Double _$6) {
                    this._$6 = _$6;
                }

                public Double get_$7() {
                    return _$7;
                }

                public void set_$7(Double _$7) {
                    this._$7 = _$7;
                }

                public Double get_$8() {
                    return _$8;
                }

                public void set_$8(Double _$8) {
                    this._$8 = _$8;
                }

                public Double get_$9() {
                    return _$9;
                }

                public void set_$9(Double _$9) {
                    this._$9 = _$9;
                }

                public Double get_$10() {
                    return _$10;
                }

                public void set_$10(Double _$10) {
                    this._$10 = _$10;
                }

                public double get_$11() {
                    return _$11;
                }

                public void set_$11(double _$11) {
                    this._$11 = _$11;
                }

                public Double get_$12() {
                    return _$12;
                }

                public void set_$12(Double _$12) {
                    this._$12 = _$12;
                }
            }
        }

        public static class TrendsBean {
            /**
             * time : 2018/02/23
             * list : [{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"},{"title":"庞总办公费支出","money":"-1000.00"}]
             */

            private String time;
            private List<ListBeanXXX> list;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<ListBeanXXX> getList() {
                return list;
            }

            public void setList(List<ListBeanXXX> list) {
                this.list = list;
            }

            public static class ListBeanXXX {
                /**
                 * title : 庞总办公费支出
                 * money : -1000.00
                 */

                private String title;
                private String money;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }
            }
        }

        public static class YearsBean {
            /**
             * year : 2017
             * selected : 1
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
    }
}
