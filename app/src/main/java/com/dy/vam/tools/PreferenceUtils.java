package com.dy.vam.tools;

import android.content.Context;

import com.dy.vam.application.VAMApplication;

/**
 * 具体实现sp 
 * Created by James on 2016/10/30.
 */  
public class PreferenceUtils extends BasePreference {  
    private static PreferenceUtils preferenceUtils;

    /** 
     * 需要增加key就在这里新建 
     */  
    //用户名的key  
    private String USER_NAME = "user_name";

    //用户token的key
    private String USER_TOKEN = "user_token";

    //用户头像的key
    private String USER_HEADIMG = "user_headimg";

    //用户ID的key
    private String USER_ID = "user_id";

    //是否登录的key
    private String LOGIN_STATUS = "login_status";

    //首页新手指引是否已显示
    private String HOME_TIPS_SHOW_STATUS="home_tips_show_status";

    //滑动显示指引是否已显示
    private String SCROLL_TIPS_SHOW_STATUS="scroll_tips_show_status";

    //滑动显示指引是否已显示
    private String TYPE_SEE_DEPART_STAFF_SHOW_STATUS="type_change_tips_show_status";

    //默认接口请求年份
    private String DEFAULT_YEAR="default_year";

    //模拟首页指引是否显示
    private String SIMULATION="simulation";

    //记住账号
    private String REMEMBERACCOUNT="remember_account";

    //记住密码
    private String REMEMBERPASSWORD="remember_password";

    private String APPTXTKNOW="app_txt_know";


    private String EMAIL="app_email";

    public void setAppTxtKnow(boolean know){
        setBoolean(APPTXTKNOW,true);
    }

    public boolean getAppTxtKnow(){
       return getBoolean(APPTXTKNOW);
    }

    public String getRemeberPassword() {
        return getString(REMEMBERPASSWORD);
    }

    public void setRemeberPassword(String password){
        setString(REMEMBERPASSWORD,password);
    }

    public String getRemeberAccount() {
        return getString(REMEMBERACCOUNT);
    }

    public void setRemeberAccount(String account){
        setString(REMEMBERACCOUNT,account);
    }

    public void setSimulationShowStatus(Boolean isShow){
        setBoolean(SIMULATION, isShow);
    }

    public Boolean getSimulationShowStatus() {
        return getBoolean(SIMULATION);
    }

    public void setDefaultYear(int time){
        setInt(DEFAULT_YEAR,time);
    }

    public int getDefaultYear(){
        return getYearInt(DEFAULT_YEAR);
    }

    public void setSeeDepartStaffShowStatus(Boolean status) {
        setBoolean(TYPE_SEE_DEPART_STAFF_SHOW_STATUS, status);
    }

    public Boolean getSeeDepartStaffShowStatus() {
        return getBoolean(TYPE_SEE_DEPART_STAFF_SHOW_STATUS);
    }

    public void setHomeTipsShowStatus(Boolean status) {
        setBoolean(HOME_TIPS_SHOW_STATUS, status);
    }

    public Boolean getHomeTipsShowStatus() {
        return getBoolean(HOME_TIPS_SHOW_STATUS);
    }

    public void setScrollTipsShowStatus(Boolean status) {
        setBoolean(SCROLL_TIPS_SHOW_STATUS, status);
    }

    public Boolean getScrollTipsShowStatus() {
        return getBoolean(SCROLL_TIPS_SHOW_STATUS);
    }

    public void loginOut(){
        userLoginOut();
    }
  
    public void setLoginStatus(boolean isFirst) {
        setBoolean(LOGIN_STATUS,isFirst);
    }

    public boolean getLoginStatus() {
        return getBoolean(LOGIN_STATUS);
    }

    public void setUserID(int id){
        setInt(USER_ID,id);
    }

    public int getUserId(){
        return getInt(USER_ID);
    }

    public String getUserToken() {
        return getString(USER_TOKEN);


    }
    public void setUserToken(String token){
        setString(USER_TOKEN,token);
    }
    public String getEmail() {
        if (getString(EMAIL) == null){
            return "";
        }
        return getString(EMAIL);
    }

    public void setEmail(String email){
        setString(EMAIL,email);
    }

    public void setUserHeadimg(String userHeadimg){
        setString(USER_HEADIMG,userHeadimg);
    }

    public String getUserHeadimg() {
        return getString(USER_HEADIMG);
    }

    public void setUserName(String name) {
        setString(USER_NAME, name);
    }  
  
    public String getUserName() {
        return getString(USER_NAME);  
    }



    private PreferenceUtils(Context context) {
        super(context);
    }
    /**
     * 这里我通过自定义的Application来获取Context对象，所以在获取preferenceUtils时不需要传入Context。
     * @return
     */
    public synchronized static PreferenceUtils getInstance() {
        if (null == preferenceUtils) {
            preferenceUtils = new PreferenceUtils(VAMApplication.getApplication());
        }
        return preferenceUtils;
    }
}