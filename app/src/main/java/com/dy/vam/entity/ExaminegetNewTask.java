package com.dy.vam.entity;

/**
 * Created by James on 2018/3/21.
 */

public class ExaminegetNewTask {

    /**
     * code : 0
     * msg :
     * data : {"taskid":15,"title":"用户 1 的新增任务","name":"testteststsetsetsetset","finishtime":"2018/03/14","standard1":{"typeid":202,"name":"sdfghxvb"},"standard2":{"typeid":181,"name":"1 天"},"standard3":{"typeid":187,"name":"不紧张(三天以上)"},"standard4":{"typeid":190,"name":"不重要"},"standard5":{"typeid":194,"name":"正常"},"integration":5.5}
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
         * taskid : 15
         * title : 用户 1 的新增任务
         * name : testteststsetsetsetset
         * finishtime : 2018/03/14
         * standard1 : {"typeid":202,"name":"sdfghxvb"}
         * standard2 : {"typeid":181,"name":"1 天"}
         * standard3 : {"typeid":187,"name":"不紧张(三天以上)"}
         * standard4 : {"typeid":190,"name":"不重要"}
         * standard5 : {"typeid":194,"name":"正常"}
         * integration : 5.5
         */

        private int taskid;
        private String title;
        private String name;
        private String finishtime;
        private Standard1Bean standard1;
        private Standard2Bean standard2;
        private Standard3Bean standard3;
        private Standard4Bean standard4;
        private Standard5Bean standard5;
        private String integration;

        public int getTaskid() {
            return taskid;
        }

        public void setTaskid(int taskid) {
            this.taskid = taskid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public Standard1Bean getStandard1() {
            return standard1;
        }

        public void setStandard1(Standard1Bean standard1) {
            this.standard1 = standard1;
        }

        public Standard2Bean getStandard2() {
            return standard2;
        }

        public void setStandard2(Standard2Bean standard2) {
            this.standard2 = standard2;
        }

        public Standard3Bean getStandard3() {
            return standard3;
        }

        public void setStandard3(Standard3Bean standard3) {
            this.standard3 = standard3;
        }

        public Standard4Bean getStandard4() {
            return standard4;
        }

        public void setStandard4(Standard4Bean standard4) {
            this.standard4 = standard4;
        }

        public Standard5Bean getStandard5() {
            return standard5;
        }

        public void setStandard5(Standard5Bean standard5) {
            this.standard5 = standard5;
        }

        public String getIntegration() {
            return integration;
        }

        public void setIntegration(String integration) {
            this.integration = integration;
        }

        public static class Standard1Bean {
            /**
             * typeid : 202
             * name : sdfghxvb
             */

            private int typeid;
            private String name;

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
        }

        public static class Standard2Bean {
            /**
             * typeid : 181
             * name : 1 天
             */

            private int typeid;
            private String name;

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
        }

        public static class Standard3Bean {
            /**
             * typeid : 187
             * name : 不紧张(三天以上)
             */

            private int typeid;
            private String name;

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
        }

        public static class Standard4Bean {
            /**
             * typeid : 190
             * name : 不重要
             */

            private int typeid;
            private String name;

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
        }

        public static class Standard5Bean {
            /**
             * typeid : 194
             * name : 正常
             */

            private int typeid;
            private String name;

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
        }
    }
}
