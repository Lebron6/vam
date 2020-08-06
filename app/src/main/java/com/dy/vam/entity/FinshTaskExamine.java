package com.dy.vam.entity;

/**
 * Created by James on 2018/3/22.
 */

public class FinshTaskExamine {
    /**
     * code : 0
     * msg :
     * data : {"taskid":15,"name":"testteststsetsetsetset","title":"用户 1的完成任务","finishtime":"2018/03/14","integration":5.5,"final_integration":11,"iteration":1}
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
         * name : testteststsetsetsetset
         * title : 用户 1的完成任务
         * finishtime : 2018/03/14
         * integration : 5.5
         * final_integration : 11
         * iteration : 1
         */

        private int taskid;
        private String name;
        private String title;
        private String finishtime;
        private String integration;
        private String final_integration;
        private String iteration;

        public int getTaskid() {
            return taskid;
        }

        public void setTaskid(int taskid) {
            this.taskid = taskid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getIteration() {
            return iteration;
        }

        public void setIteration(String iteration) {
            this.iteration = iteration;
        }
    }
}
