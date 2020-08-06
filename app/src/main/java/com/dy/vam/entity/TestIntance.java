package com.dy.vam.entity;

/**
 * Created by James on 2018/8/21.
 */
public class TestIntance {

    private String userName=null;
    private static TestIntance testIntance;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static TestIntance getIntance(){
        if (testIntance==null){
            return new TestIntance();
        }

        return testIntance;
    }
}
