package com.dy.vam.entity;

import java.util.List;

/**
 * Created by James on 2018/3/23.
 */

public class GetTaskOverInfo {
    /**
     * code : 0
     * msg :
     * data : {"title":"用户 1 的任务详情","name":"testteststsetsetsetset","finish_status":2,"finishtime":"2018/03/14","integration":5.5,"final_integration":3.52,"newtask":[{"title":"待审批","time":"03/1613:48"}],"iteration":[{"sort":1,"info":"闪电发货当房间都已经","createtime":"03/2017:49"},{"sort":2,"info":"asdfagafghsdhfgjxfgjn","createtime":"03/2018:00"}],"finishtask":[{"title":"待审批","time":"01/01 08:00"},{"title":"审批通过","time":"01/01 08:00"}],"star":{"star1":3,"star2":4,"star3":5}}
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
         * title : 用户 1 的任务详情
         * name : testteststsetsetsetset
         * finish_status : 2
         * finishtime : 2018/03/14
         * integration : 5.5
         * final_integration : 3.52
         * newtask : [{"title":"待审批","time":"03/1613:48"}]
         * iteration : [{"sort":1,"info":"闪电发货当房间都已经","createtime":"03/2017:49"},{"sort":2,"info":"asdfagafghsdhfgjxfgjn","createtime":"03/2018:00"}]
         * finishtask : [{"title":"待审批","time":"01/01 08:00"},{"title":"审批通过","time":"01/01 08:00"}]
         * star : {"star1":3,"star2":4,"star3":5}
         */

        private String title;
        private String name;
        private int finish_status;
        private String finishtime;
        private String integration;
        private String final_integration;
        private StarBean star;
        private List<NewtaskBean> newtask;
        private List<IterationBean> iteration;
        private List<FinishtaskBean> finishtask;

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

        public int getFinish_status() {
            return finish_status;
        }

        public void setFinish_status(int finish_status) {
            this.finish_status = finish_status;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public String getIntegration() {
            return integration;
        }

        public void setIntegration(String integration) {
            this.integration = integration;
        }

        public String getFinal_integration() {
            return final_integration;
        }

        public void setFinal_integration(String final_integration) {
            this.final_integration = final_integration;
        }

        public StarBean getStar() {
            return star;
        }

        public void setStar(StarBean star) {
            this.star = star;
        }

        public List<NewtaskBean> getNewtask() {
            return newtask;
        }

        public void setNewtask(List<NewtaskBean> newtask) {
            this.newtask = newtask;
        }

        public List<IterationBean> getIteration() {
            return iteration;
        }

        public void setIteration(List<IterationBean> iteration) {
            this.iteration = iteration;
        }

        public List<FinishtaskBean> getFinishtask() {
            return finishtask;
        }

        public void setFinishtask(List<FinishtaskBean> finishtask) {
            this.finishtask = finishtask;
        }

        public static class StarBean {
            /**
             * star1 : 3
             * star2 : 4
             * star3 : 5
             */

            private String star1;
            private String star2;
            private String star3;

            @Override
            public String toString() {
                return "StarBean{" +
                        "star1=" + star1 +
                        ", star2=" + star2 +
                        ", star3=" + star3 +
                        '}';
            }

            public String getStar1() {
                return star1;
            }

            public void setStar1(String star1) {
                this.star1 = star1;
            }

            public String getStar2() {
                return star2;
            }

            public void setStar2(String star2) {
                this.star2 = star2;
            }

            public String getStar3() {
                return star3;
            }

            public void setStar3(String star3) {
                this.star3 = star3;
            }
        }

        public static class NewtaskBean {
            /**
             * title : 待审批
             * time : 03/1613:48
             */

            private String title;
            private String time;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class IterationBean {
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

        public static class FinishtaskBean {
            /**
             * title : 待审批
             * time : 01/01 08:00
             */

            private String title;
            private String time;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
