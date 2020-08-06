package com.dy.vam.entity;

/**
 * @author Rain
 * created:2019/4/10 15:44
 * desc:类作用描述
 */
public class ContentBean {


    /**
     * msg_content : 2
     * expenseid : 1148
     * line1 : 报销人：用户1
     * line2 : 报销类型：办公费
     * line3 : 报销金额：1元
     */

    private int msg_content;
    private int expenseid;
    private int taskid;
    private String line1;
    private String line2;
    private String line3;

    public int getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(int msg_content) {
        this.msg_content = msg_content;
    }

    public int getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(int expenseid) {
        this.expenseid = expenseid;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }
}
