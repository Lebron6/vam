package com.dy.vam.api;

import com.dy.vam.entity.CompanyInfo;
import com.dy.vam.entity.ConfirmTaskOver;
import com.dy.vam.entity.DepartStaffDetails;
import com.dy.vam.entity.DepartmentList;
import com.dy.vam.entity.DepartmentUsers;
import com.dy.vam.entity.EmailEean;
import com.dy.vam.entity.ExamineApprove;
import com.dy.vam.entity.ExaminegetNewTask;
import com.dy.vam.entity.ExpenseReimbursement;
import com.dy.vam.entity.FinalcinalAmount;
import com.dy.vam.entity.FinalcinalCustomDetails;
import com.dy.vam.entity.FinancialGetCustomList;
import com.dy.vam.entity.FinshTaskExamine;
import com.dy.vam.entity.GetCommissionData;
import com.dy.vam.entity.GetDepartmentControlDetail;
import com.dy.vam.entity.GetDepartmentDisposable;
import com.dy.vam.entity.GetPhoneNum;
import com.dy.vam.entity.GetStarInfo;
import com.dy.vam.entity.GetTaskOverInfo;
import com.dy.vam.entity.GetValueData;
import com.dy.vam.entity.MarketGetCustom;
import com.dy.vam.entity.MarketGetCustomList;
import com.dy.vam.entity.Notice;
import com.dy.vam.entity.NoticeUrl;
import com.dy.vam.entity.OperaList;
import com.dy.vam.entity.Organization;
import com.dy.vam.entity.OtherDepartment;
import com.dy.vam.entity.OtherDepartmentDetailsData;
import com.dy.vam.entity.SummaryDataDetails;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.entity.SummaryGetData;
import com.dy.vam.entity.SummaryGetDataChart;
import com.dy.vam.entity.SummaryGetDepartmentControl;
import com.dy.vam.entity.SummaryGetPersonData;
import com.dy.vam.entity.TaskUpdataData;
import com.dy.vam.entity.Iteration;
import com.dy.vam.entity.UnDistributed;
import com.dy.vam.entity.UpLoadReimbursementPicResult;
import com.dy.vam.entity.UpdatePasswordParameters;
import com.dy.vam.entity.User;
import com.dy.vam.entity.Users;
import com.dy.vam.entity.Verif;
import com.dy.vam.entity.VersionBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by James on 2018/1/4.
 */

public interface VamApi {

    String Content_Type="translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=";

    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<User> userLogin(@Field("phone") String phone, @Field("password") String password);

    /**
     * 获取公司信息
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<CompanyInfo> getCompanyInfo(@Field("token") String token);

    /**
     * 发送验证码
     * @param phone
     * @param device_number     唯一标示
     * @param msg_type          0 注册 1 忘记密码 2验证当前手机号
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> sendMsg(@Field("phone") String phone, @Field("device_number") String device_number, @Field("msg_type") String msg_type);

    /**
     * 用户注册
     * @param phone
     * @param code
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<UpdatePasswordParameters> userRegister(@Field("phone") String phone, @Field("code") String code);

    /**
     * 注册或忘记密码后填写密码
     * @param userid
     * @param verify
     * @param password
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> userResetPassword(@Field("userid") String userid, @Field("verify") String verify, @Field("password") String password);

    /**
     * 忘记密码验证
     * @param phone
     * @param code
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<UpdatePasswordParameters> userForgetPassword(@Field("phone") String phone, @Field("code") String code);

    /**
     * 获取操作列表
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<OperaList> getOperationList(@Field("token") String token);

    /**
     * 获取组织架构数据
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Organization> getOrganization(@Field("token") String token);

    /**
     * 获取部门人员信息
     * @param token
     * @param departmentid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<DepartmentUsers> getDepartmentUsers(@Field("token") String token,@Field("departmentid") int departmentid);

    /**
     * 获取员工信息
     * @param token
     * @param userid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<DepartStaffDetails> getUserInfoById(@Field("token") String token, @Field("userid") int userid,@Field("year") int year);

    /**
     * 添加新部门
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> addDepartment(@Field("token") String token, @Field("name") String departmentName);

    /**
     * 修改部门名称
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> editDepartmentName(@Field("token") String token, @Field("name") String departmentName ,@Field("departmentid") int departmentid );

    /**
     * 删除该部门
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> deleteDepartment(@Field("token") String token, @Field("departmentid") int departmentid );

    /**
     * 获取部门列表
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<DepartmentList> getDepartmentList(@Field("token") String token);

    /**
     * 添加新员工
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> addNewStaff(@Field("token") String token,@Field("username") String username,@Field("phone") String phone,@Field("departmentid") int departmentid,@Field("post") String post);

    /**
     * 获取财务录入信息
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<FinalcinalAmount> getFinalcinalAmount(@Field("token") String token, @Field("year") int year, @Field("month") int month);


    /**
     * 财务录入
     * @param token
     * @param dataJson
     * @param salaryJson
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> finalcinalInput(@Field("token") String token, @Field("details") String dataJson, @Field("details2") String salaryJson);

    /**
     * 获取业务提成客户列表
     * @param token
     * @param type
     * @param page
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<FinancialGetCustomList> financialGetCustomList(@Field("token") String token, @Field("new") int type,@Field("page") int page);

    /**
     * 获取业务提成客户详情
     * @param token
     * @param customid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<FinalcinalCustomDetails> financialGetCustomDetails(@Field("token") String token, @Field("customid") int customid);

    /**
     * 更新客户营业额和毛利率
     * @param token
     * @param customid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> financialEditCustomDetails(@Field("token") String token, @Field("customid") int customid,@Field("turnover") String turnover,@Field("grossprofit") String grossprofit);

    /**
     * 营销获取客户列表
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<MarketGetCustomList> marketGetCustomList(@Field("token") String token);


    /**
     * 获取员工列表
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Users> getUsersList(@Field("token") String token);

    /**
     * 新增客户
     * @param token
     * @param name
     * @param userJson
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> marketAddCustom(@Field("token") String token,@Field("name") String name,@Field("users") String userJson);

    /**
     * 获取客户详情
     * @param token
     * @param customid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<MarketGetCustom> marketGetCustomInfo(@Field("token") String token, @Field("customid") int customid);

    /**
     * 编辑客户
     * @param token
     * @param customid
     * @param name
     * @param userJson
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> marketEditCustomInfo(@Field("token") String token, @Field("customid") int customid, @Field("name") String name,@Field("users") String userJson);


    /**
     * 删除客户
     * @param token
     * @param customid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> marketDeleteCustom(@Field("token") String token, @Field("customid") int customid);

    /**
     * 获取费用审批列表
     * @param token
     * @param type
     * @param page
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ExamineApprove> examineGetExamineList(@Field("token") String token, @Field("type") int type, @Field("page") int page);

    /**
     * 获取费用报销列表
     * @param token
     * @param page
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ExpenseReimbursement> examineGetExpenseList(@Field("token") String token, @Field("page") int page);

    /**
     * 获取抄送我的列表
     * @param token
     * @param page
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ExamineApprove> examineGetCopyMeList(@Field("token") String token, @Field("page") int page);

    /**
     * 获取首页数据
     * @param token
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryGetData> getHomeData(@Field("token") String token, @Field("year") int year);

    /**
     * 获取公司收入支出详情列表
     * @param token
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryDataDetails> getSummaryGetDataDetail(@Field("token") String token, @Field("year") int year,@Field("type") int type);

    /**
     * 获取公司月份图表数据
     * @param token
     * @param year
     * @param termid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryGetDataChart> getSummaryGetDataChart(@Field("token") String token, @Field("year") int year, @Field("title") String termid);

    /**
     * 获取部门首页数据
     * @param token
     * @param departmentid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryDepartmentData> getSummaryDepartmentData(@Field("token") String token,@Field("departmentid") int departmentid, @Field("year") int year);

    /**
     * 获取部门可支配收入详情
     * @param token
     * @param departmentid
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetDepartmentDisposable> getSummaryDepartmentDisposableData(@Field("token") String token,@Field("year") int year, @Field("departmentid") int departmentid);

    /**
     * 获取部门客户收入图表数据
     * @param token
     * @param departmentid
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryGetDataChart> getDepartmentCustomIncome(@Field("token") String token,@Field("year") int year, @Field("departmentid") int departmentid);

    /**
     * 获取部门可控制成本图表数据
     * @param token
     * @param departmentid
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryGetDepartmentControl> summaryGetDepartmentControl(@Field("token") String token, @Field("year") int year, @Field("departmentid") int departmentid);

    /**
     * 获取部门可控制成本详情
     * @param token
     * @param departmentid
     * @param year
     * @param type
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetDepartmentControlDetail> summaryGetDepartmentControlDetails(@Field("token") String token, @Field("year") int year,@Field("type") int type, @Field("departmentid") int departmentid);

    /**
     * 获取部门可控制成本详情
     * @param token
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<SummaryGetPersonData> summaryGetPersonData(@Field("token") String token, @Field("year") int year);

    /**
     * 修改头像
     * @param token
     * @param img
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> updataUserIcon(@Field("token") String token, @Field("img") String img);

    /**
     * 修改密码
     * @param token
     * @param oldPassword
     * @param newPassword
     * @param conPassword
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> updataPassword(@Field("token") String token, @Field("old_password") String oldPassword, @Field("new_password") String newPassword, @Field("con_password") String conPassword);

    /**
     * 获取当前手机号
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetPhoneNum> getNowPhoneNum(@Field("token") String token);

    /**
     * 验证当前手机号
     * @param token
     * @param code
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> verifyPhone(@Field("token") String token,@Field("code") String code);

    /**
     * 绑定新手机号
     * @param token
     * @param phone
     * @param code
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> bindNewPhoneNew(@Field("token") String token,@Field("phone") String phone,@Field("code") String code);

    /**
     * 意见反馈
     * @param token
     * @param msg
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> feedBack(@Field("token") String token,@Field("msg") String msg);

    /**
     * 退出登录
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> loginOut(@Field("token") String token);

    /**
     * 其它部门
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<OtherDepartment> otherDepartment(@Field("token") String token);

    /**
     * 其它部门详情
     * @param token
     * @param departmentid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<OtherDepartmentDetailsData> otherDepartmentData(@Field("token") String token, @Field("departmentid") int departmentid);

    /**
     * 价值分配表
     * @param token
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetValueData> getValueData(@Field("token") String token, @Field("year") int year);

    /**
     * 价值分配
     * @param token
     * @param userids
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> valueDistrubution(@Field("token") String token,@Field("year") int year,@Field("userids") String userids);

    /**
     * 提成分配表
     * @param token
     * @param year
     * @param quarter
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetCommissionData> getCommissionData(@Field("token") String token, @Field("year") int year,@Field("quarter") int quarter);

    /**
     * 提成分配
     * @param token
     * @param cuids
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> commissionDistrubution(@Field("token") String token,@Field("year") int year,@Field("cuids") String cuids);

    /**
     * 删除员工
     * @param token
     * @param userid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> adminDeleteUser(@Field("token") String token, @Field("userid") int userid);

    /**
     * 编辑员工
     * @param token
     * @param userid
     * @param phone
     * @param departmentId
     * @param post
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> adminEditUser(@Field("token") String token, @Field("userid") int userid,@Field("phone") String phone,@Field("departmentid") int departmentId,@Field("post") String post,@Field("isdirector") int isdirector);

    /**
     * 获取新增任务审批数据
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ExaminegetNewTask> examineGetNewTask(@Field("token") String token, @Field("taskid") int taskid);

    /**
     * 新增任务审批通过
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> examinePassNewtask(@Field("token") String token ,@Field("taskid") int taskid );

    /**
     * 更改新增任务获取基础数据
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<TaskUpdataData> getUpdataTaskData(@Field("token") String token , @Field("taskid") int taskid );

    /**
     * 更改新增任务
     * @param token
     * @param taskid
     * @param name
     * @param finishtime
     * @param standard1
     * @param standard2
     * @param standard3
     * @param standard4
     * @param standard5
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> updataTaskData(@Field("token") String token, @Field("taskid") int taskid ,
                               @Field("name") String name ,@Field("finishtime") String finishtime,
                               @Field("standard1") int standard1,@Field("standard2") int standard2,
                               @Field("standard3") int standard3,@Field("standard4") int standard4,
                               @Field("standard5") int standard5);

    /**
     * 获取完成任务审批数据
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<FinshTaskExamine> getFinshTaskExamineData(@Field("token") String token , @Field("taskid") int taskid );

    /**
     * 确认完成任务
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ConfirmTaskOver> confirmTaskFinish(@Field("token") String token , @Field("taskid") int taskid );

    /**
     * 提示迭代
     * @param token
     * @param taskid
     * @param info
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> tips(@Field("token") String token ,@Field("taskid") int taskid ,@Field("info") String info);

    /**
     * 迭代记录列表
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Iteration> iteration(@Field("token") String token , @Field("taskid") int taskid);

    /**
     * 获取质量考评页面数据
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetStarInfo> getStarInfo(@Field("token") String token , @Field("taskid") int taskid);

    /**
     * 获取质量考评页面数据
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Verif> addStar(@Field("token") String token , @Field("taskid") int taskid, @Field("star1") int star1,@Field("star2") int star2,@Field("star3") int star3);

    /**
     * 获取任务信息页面数据
     * @param token
     * @param taskid
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<GetTaskOverInfo> getTaskOverInfo(@Field("token") String token , @Field("taskid") int taskid);

    /**
     * 获取消息列表
     * @param token
     * @param page
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Notice> getNoticeList(@Field("token") String token, @Field("page") int page);

    /**
     * 获取未分配
     * @param token
     * @param year
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<UnDistributed> getUndistributed(@Field("token") String token, @Field("year") int year);

    /**
     * 上传图片
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<UpLoadReimbursementPicResult> getUpLoadReimbursementPicResult(@Field("token") String token, @Field("img") String img,@Field("type") int type);  /**


     /
     * 版本信息
     * @return
     */
    @GET(Content_Type)
    Call<VersionBean> getVersionInfo();

     /*
     * 版本信息
     * @return
     */
    @GET("main/expense/examine_export/")
    Call<EmailEean> goExport(@Query("token") String token, @Query("expenseid") String expenseid, @Query("email") String email);

    /**
     * 根据通知内容获取网页
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<NoticeUrl> getUrlByNotice(@Field("token") String token, @Field("extras") String extras);


 }