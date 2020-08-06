package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/21.
 */

public class TaskUpdataData {
    /**
     * code : 0
     * msg :
     * data : {"standard1":[{"typeid":202,"name":"sdfghxvb","integral":2,"proportion":0.3},{"typeid":201,"name":"fasdf","integral":2,"proportion":0.3},{"typeid":196,"name":"设计类","integral":10,"proportion":0.3}],"standard2":[{"typeid":181,"name":"1 天","integral":1,"proportion":0.4},{"typeid":182,"name":"1-3 天","integral":3,"proportion":0.4},{"typeid":183,"name":"1 周","integral":5,"proportion":0.4},{"typeid":184,"name":"2 周","integral":10,"proportion":0.4},{"typeid":185,"name":"3 周","integral":20,"proportion":0.4},{"typeid":186,"name":"4 周","integral":30,"proportion":0.4}],"standard3":[{"typeid":187,"name":"不紧张(三天以上)","integral":10,"proportion":0.1},{"typeid":188,"name":"一般紧张(三天内)","integral":20,"proportion":0.1},{"typeid":189,"name":"很紧张(当天)","integral":30,"proportion":0.1}],"standard4":[{"typeid":190,"name":"不重要","integral":10,"proportion":0.05},{"typeid":191,"name":"一般重要","integral":20,"proportion":0.05},{"typeid":192,"name":"很重要","integral":30,"proportion":0.05}],"standard5":[{"typeid":193,"name":"空闲","integral":10,"proportion":0.15},{"typeid":194,"name":"正常","integral":20,"proportion":0.15},{"typeid":195,"name":"超负荷","integral":30,"proportion":0.15}]}
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
        private List<Standard1Bean> standard1;
        private List<Standard2Bean> standard2;
        private List<Standard3Bean> standard3;
        private List<Standard4Bean> standard4;
        private List<Standard5Bean> standard5;

        public List<Standard1Bean> getStandard1() {
            return standard1;
        }

        public void setStandard1(List<Standard1Bean> standard1) {
            this.standard1 = standard1;
        }

        public List<Standard2Bean> getStandard2() {
            return standard2;
        }

        public void setStandard2(List<Standard2Bean> standard2) {
            this.standard2 = standard2;
        }

        public List<Standard3Bean> getStandard3() {
            return standard3;
        }

        public void setStandard3(List<Standard3Bean> standard3) {
            this.standard3 = standard3;
        }

        public List<Standard4Bean> getStandard4() {
            return standard4;
        }

        public void setStandard4(List<Standard4Bean> standard4) {
            this.standard4 = standard4;
        }

        public List<Standard5Bean> getStandard5() {
            return standard5;
        }

        public void setStandard5(List<Standard5Bean> standard5) {
            this.standard5 = standard5;
        }

        public static class Standard1Bean {
            /**
             * typeid : 202
             * name : sdfghxvb
             * integral : 2
             * proportion : 0.3
             */

            private int typeid;
            private String name;
            private int integral;
            private double proportion;

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
            }
        }

        public static class Standard2Bean {
            /**
             * typeid : 181
             * name : 1 天
             * integral : 1
             * proportion : 0.4
             */

            private int typeid;
            private String name;
            private int integral;
            private double proportion;

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
            }
        }

        public static class Standard3Bean {
            /**
             * typeid : 187
             * name : 不紧张(三天以上)
             * integral : 10
             * proportion : 0.1
             */

            private int typeid;
            private String name;
            private int integral;
            private double proportion;

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
            }
        }

        public static class Standard4Bean {
            /**
             * typeid : 190
             * name : 不重要
             * integral : 10
             * proportion : 0.05
             */

            private int typeid;
            private String name;
            private int integral;
            private double proportion;

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
            }
        }

        public static class Standard5Bean {
            /**
             * typeid : 193
             * name : 空闲
             * integral : 10
             * proportion : 0.15
             */

            private int typeid;
            private String name;
            private int integral;
            private double proportion;

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
            }
        }
    }
}
