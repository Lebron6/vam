package com.dy.vam.api;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BaseUrl {

    private static BaseUrl baseUrl;

    public static BaseUrl getInstence() {
        if (baseUrl == null) {
            return new BaseUrl();
        }
        return baseUrl;
    }

//    private String ipAddress  = "http://192.168.1.220/";//本地服务器
    private String ipAddress  = "http://yj.tigerisa.com/";   //线上服务器

    /**
     * 用户登录
     * @return
     */
    public String getUserLoginUrl() {
        return ipAddress + "api/user/login/";
    }

    /**
     * 获取公司信息
     * @return
     */
    public String getCompanyInfoUrl() {
        return ipAddress + "api/person/getinfo/";
    }

    /**
     * 获取短信验证码
     * @return
     */
    public String getSendMsgUrl() {
        return ipAddress + "api/user/send_msg/";
    }

    /**
     * 注册
     * @return
     */ 
    public String getUserRegisterUrl() {
        return ipAddress + "api/user/register/";
    }

    /**
     * 重置验证码
     * @return
     */
    public String getUserResetPasswordUrl() {
        return ipAddress + "api/user/update_password/";
    }

    /**
     * 忘记密码
     * @return
     */
    public String getForgetPasswordUrl() {
        return ipAddress + "api/user/forget_password/";
    }


    /**
     * 获取操作列表
     * @return
     */
    public String getOperationListUrl() {
        return ipAddress + "api/operation/get_operation_list/";
    }

    /**
     * 获取组织结构
     * @return
     */
    public String getOrganizationUrl() {
        return ipAddress + "api/admin/get_organization/";
    }

    /**
     * 获取部门人员信息
     * @return
     */
    public String getDepartmentUserUrl() {
        return ipAddress + "api/admin/get_department_user/";
    }

    /**
     * 获取员工信息
     * @return
     */
    public String getUserInfoUrl() {
        return ipAddress + "api/admin/get_user/";
    }

    /**
     * 添加部门
     * @return
     */
    public String getAddDepartUrl() {
        return ipAddress + "api/admin/add_department/";
    }

    /**
     * 编辑部门
     * @return
     */
    public String getEditDepartNameUrl() {
        return ipAddress + "api/admin/edit_department/";
    }

    /**
     * 删除部门
     * @return
     */
    public String getDeleteDepartNameUrl() {
        return ipAddress + "api/admin/delete_department/";
    }

    /**
     * 获取部门列表
     * @return
     */
    public String getDepartmentListUrl() {
        return ipAddress + "api/admin/get_department_list/";
    }

    /**
     * 添加员工
     * @return
     */

    public String getAddNewStaffUrl() {
        return ipAddress + "api/admin/add_employee/";
    }

    /**
     * 获取其它部门
     * @return
     */

    public String getOtherDepartmentUrl() {
        return ipAddress + "api/admin/get_other_department/";
    }

    /**
     * 获取其他部门数据
     * @return
     */
    public String getOtherDepartmentDataUrl() {
        return ipAddress + "api/admin/get_department_data/";
    }

    /**
     * 获取财务录入信息（此接口后续可能有大修改）
     * @return
     */
    public String getFinancialGetAmountUrl() {
        return ipAddress + "api/financial/get_amount/";
    }

    /**
     * 财务录入
     * @return
     */
    public String getFinancialEditAmountUrl() {
        return ipAddress + "api/financial/edit_amount/";
    }

    /**
     * 获取业务提成客户列表
     * @return
     */
    public String getFinancialCustomListUrl() {
        return ipAddress + "api/financial/get_custom_list/";
    }

    /**
     * 获取业务提成客户详情
     * @return
     */
    public String getCustomDetailsUrl() {
        return ipAddress + "api/financial/get_custom/";
    }

    /**
     * 更新客户营业额和毛利率
     * @return
     */
    public String getEditCustomUrl() {
        return ipAddress + "api/financial/edit_custom/";
    }

    /**
     * 获取客户列表
     * @return
     */
    public String getMarketCustomListUrl() {
        return ipAddress + "api/market/get_custom_list/";
    }

    /**
     * 新增客户
     * @return
     */
    public String getMarketAddCustomUrl() {
        return ipAddress + "api/market/add_custom/";
    }

    /**
     * 获取员工列表
     * @return
     */
    public String marketGetUsersUrl() {
        return ipAddress + "api/market/get_users/";
    }

    /**
     * 获取客户详细信息
     * @return
     */
    public String getMarketGetCustomUrl() {
        return ipAddress + "api/market/get_custom/";
    }

    /**
     * 编辑客户
     * @return
     */
    public String getMarketEditCustomUrl() {
        return ipAddress + "api/market/edit_custom/";
    }

    /**
     * 删除客户
     * @return
     */
    public String getMarketDeleteCustomUrl() {
        return ipAddress + "api/market/delete_custom/";
    }

    /**
     * 获取费用审批列表
     * @return
     */
    public String getEamineListUrl() {
        return ipAddress + "api/examine/get_examine_list/";
    }

    /**
     * 获取费用报销列表
     * @return
     */
    public String getExpenceListUrl() {
        return ipAddress + "api/examine/get_expense_list/";
    }

    /**
     * 获取首页数据
     * @return
     */
    public String getHomeDataUrl() {
        return ipAddress + "api/summary/get_data/";
    }

    /**
     * 获取公司收入支出详情列表
     * @return
     */
    public String getSummaryGetDataDetailUrl() {
        return ipAddress + "api/summary/get_data_detail/";
    }

    /**
     * 获取公司月份图表数据
     * @return
     */
    public String getSummaryGetDataChartUrl() {
        return ipAddress + "api/summary/get_data_chart/";
    }

    /**
     * 获取部门首页数据
     * @return
     */
    public String getSummaryGetDepartmentDataUrl() {
        return ipAddress + "api/summary/get_department_data/";
    }

    /**
     * 获取部门可支配收入详情
     * @return
     */
    public String getSummaryGetDepartmentDisposableUrl() {
        return ipAddress + "api/summary/get_department_disposable/";
    }

    /**
     * 获取部门客户收入图表数据
     * @return
     */
    public String getSummaryGetDepartmentCustomincomeUrl() {
        return ipAddress + "api/summary/get_department_customincome/";
    }

    /**
     * 获取部门管理收入图表数据
     * @return
     */
    public String getSummaryGetDepartmentManageincomeUrl() {
        return ipAddress + "api/summary/get_department_manageincome/";
    }

    /**
     * 获取部门可控制成本图表数据
     * @return
     */
    public String getSummaryGetDepartmentControlUrl() {
        return ipAddress + "api/summary/get_department_control/";
    }

    /**
     * 获取部门可控制成本费用明细
     * @return
     */
    public String getSummaryGetDepartmentControlDetailsUrl() {
        return ipAddress + "api/summary/get_department_control_detail/";
    }

    /**
     * 获取部门成员列表
     * @return
     */
    public String getEmployeeListUrl() {
        return ipAddress + "api/department/get_employee_list/";
    }

    /**
     * 获取部门成员详细信息
     * @return
     */
    public String getEmployeeUrl() {
        return ipAddress + "api/department/get_employee/";
    }

    /**
     * 个人中心首页
     * @return
     */
    public String getSummaryGetPersonDataUrl() {
        return ipAddress + "api/summary/get_person_data/";
    }

    /**
     * 修改头像
     * @return
     */
    public String getUserUpdataIconUrl() {
        return ipAddress + "api/person/change_headimg/";
    }

    /**
     * 修改密码
     * @return
     */
    public String getUpdataPasswordUrl() {
        return ipAddress + "api/person/update_password/";
    }

    /**
     * 获取当前手机号
     * @return
     */
    public String getPhoneUrl() {
        return ipAddress + "api/person/get_phone/";
    }

    /**
     * 验证当前手机号
     * @return
     */
    public String getVerifyPhoneUrl() {
        return ipAddress + "api/person/verify_phone/";
    }

    /**
     * 绑定新手机号
     * @return
     */
    public String getBindNewPhoneUrl() {
        return ipAddress + "api/person/bind_newphone/";
    }

    /**
     * 意见反馈
     * @return
     */
    public String getFeedBackUrl() {
        return ipAddress + "api/person/feedback/";
    }

    /**
     * 退出登录
     * @return
     */
    public String getLoginOutUrl() {
        return ipAddress + "api/person/logout/";
    }

    /**
     * 软件协议
     * @return
     */
    public String getSoftwareProtocolUrl() {
        return ipAddress + "common/explain.html";
    }

    /**
     * 价值分配表
     * @return
     */
    public String getFinancialGetValueDataUrl() {
        return ipAddress + "api/financial/get_value_data/";
    }

    /**
     * 价值分配
     * @return
     */
    public String getValueDistrubutionUrl() {
        return ipAddress + "api/financial/value_distrubution/";
    }

    /**
     * 提成分配表
     * @return
     */
    public String getFinancialGetCommissionDataUrl() {
        return ipAddress + "api/financial/get_commission_data/";
    }

    /**
     * 提成分配
     * @return
     */
    public String getCommissionDistrubutionUrl() {
        return ipAddress + "api/financial/commission_distrubution/";
    }

    /**
     * 编辑员工
     * @return
     */
    public String getAdminEditUserUrl() {
        return ipAddress + "api/admin/edit_user/";
    }

    /**
     * 删除员工
     * @return
     */
    public String getAdminDeleteUserUrl() {
        return ipAddress + "api/admin/delete_user/";
    }

    /**
     * 获取任务审批列表
     * @return
     */
    public String getExamineGetTaskListUrl() {
        return ipAddress + "api/examine/get_task_list/";
    }

    /**
     * 获取任务审批数据
     * @return
     */
    public String getExamineGetNewTaskUrl() {
        return ipAddress + "api/examine/get_newtask_examine/";
    }

    /**
     * 新增任务审批通过
     * @return
     */
    public String getExaminePassNewTaskUrl() {
        return ipAddress + "api/examine/pass_newtask/";
    }

    /**
     * 更改新增任务获取基础数据
     * @return
     */
    public String getExamineGetStandardDataUrl() {
        return ipAddress + "api/examine/get_standard_data/";
    }

    /**
     * 更改新增任务
     * @return
     */
    public String getExamineEditNewTaskUrl() {
        return ipAddress + "api/examine/edit_newtask/";
    }

    /**
     * 获取完成任务审批数据
     * @return
     */
    public String getExamineGetFinishTaskExamineUrl() {
        return ipAddress + "api/examine/get_finishtask_examine/";
    }

    /**
     * 确认完成任务
     * @return
     */
    public String getExamineCinfirmTaskFinishUrl() {
        return ipAddress + "api/examine/confirm_task_finish/";
    }

    /**
     * 添加迭代（提示修改）
     * @return
     */
    public String getExamineAddIterationUrl() {
        return ipAddress + "api/examine/add_iteration/";
    }

    /**
     * 迭代记录列表
     * @return
     */
    public String getExamineGetIterationListUrl() {
        return ipAddress + "api/examine/get_iteration_list/";
    }

    /**
     * 获取质量考评页面数据
     * @return
     */
    public String getExamineGetStarInfoUrl() {
        return ipAddress + "api/examine/get_star_info/";
    }

    /**
     * 提交质量考评数据
     * @return
     */
    public String getExamineAddStarUrl() {
        return ipAddress + "api/examine/add_star/";
    }

    /**
     * 获取任务信息页面数据
     * @return
     */
    public String getExamineGetTaskUrl() {
        return ipAddress + "api/examine/get_task/";
    }

    /**
     * 获取抄送我的列表
     * @return
     */
    public String getExamineGetCopyMeListUrl() {
        return ipAddress + "api/examine/get_copyme_list/";
    }

    /**
     * 获取通知列表
     */
    public String getNoticeListUrl() {
        return ipAddress + "api/notice/get_list/";
    }

    /**
     * 获取未分配价值人员
     */
    public String getValueUndistrubuteUrl() {
        return ipAddress + "api/financial/get_value_undistrubute/";
    }

    /**
     * 获取未分配提成人员
     */
    public String getCommissionUndistrubuteUrl() {
        return ipAddress + "api/financial/get_commission_undistrubute/";
    }

    /**
     * 上传图片
     */
    public String getUploadImgUrl() {
        return ipAddress + "api/open/upload_img/";
    }

    /**
     * 获取版本信息
     */
    public String getVersionInfoUrl() {
        return ipAddress + "api/base/get_version/";
    }

    /**
     * 收到通知后获取 url
     */
    public String getUrlByNoticeUrl() {
        return ipAddress + "api/notice/get_url/";
    }

    /**
     * 导出Excel url
     */
    public String getUrlByExcelUrl() {
        return ipAddress + "main/expense/examine_export/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzcsInJvbGUiOjUsImNvbXBhbnlpZCI6OCwiZXhwIjoxNTg1Mjc3OTUxfQ._il1ombWW13PKGzD1W1yG-C3YoBGjQ_wedebJFthr5Y.html?expenseid=";
    }
    /**
     * 获取费用报销列表
     * @return
     */
    public String getUrlByExcel() {
        return ipAddress;
    }
}
