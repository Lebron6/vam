package com.dy.vam.config;

/**
 * Created by James on 2018/1/4.
 */

public class Constant {


    /**
     * 总收入
     */
    public static final int INCOME = 0;

    /**
     * 总支出
     */
    public static final int OUTPUT = 1;

    /**
     * 可支配
     */
    public static final int CONTRO = 2;

    /**
     * 费用审批
     */
    public static final int EXAMINE_APPROVE_TYPE_COST = 2301;

    /**
     * 任务审批
     */
    public static final int EXAMINE_APPROVE_TYPE_TASK = 2302;

    /**
     * 抄送我的
     */
    public static final int EXAMINE_APPROVE_TYPE_CC = 2303;

    /**
     * 审批中
     */
    public static final int EXAMINE_APPROVE_ING = 0;

    /**
     * 全部
     */
    public static final int EXAMINE_APPROVE_ALL = 1;

    /**
     * 抄送
     */
    public static final int EXAMINE_APPROVE_CC = 2306;

    /**
     * 请求成功
     */
    public static final int SUCCESS = 0;

    /**
     * 未登录
     */
    public static final int USER_NOT_LOGIN = 1;

    /**
     * 用户不存在
     */
    public static final int USER_DOES_NOT_EXIST = 2;

    /**
     * token 无效
     */
    public static final int TOKEN_INVALID = 3;

    /**
     * 保存错误
     */
    public static final int SAVE_THE_ERRTOR = 6;

    /**
     * 更新错误
     */
    public static final int UPDATE_ERROR = 7;

    /**
     * 注册失败
     */
    public static int REGISTER_FAILURE = 1001;

    /**
     * 手机号或密码错误
     */
    public static int PHONENUM_OR_PASSWORD_ERROR = 1002;

    /**
     * 手机号码不能为空
     */
    public static int PHONENUM_NOT_EMPTY = 1003;

    /**
     * 手机号码已存在
     */
    public static int PHONENUM_ALREADY_EXISTED = 1004;

    /**
     * 密码为必填项
     */
    public static int PASSWORD_IS_REQUIRED = 1005;

    /**
     * 密码长度为 6~20 字符
     */
    public static int PASSWORD_LEANTH_ERROR = 1006;

    /**
     * 确认密码不能为空
     */
    public static int CONFIRM_PASSWORD_IS_EMPTY = 1007;

    /**
     * 两次密码不一致
     */
    public static int TWP_CIPHER_INCONSISTENCIES = 1008;

    /**
     * 下拉刷新
     */
    public static int REFRESH_STATUS = 0;

    /**
     * 加载更多
     */
    public static int LOAD_MORE_STATUS = 1;

    /**
     * 员工
     */
    public static int STAFF = 0;

    /**
     * 提成金额
     */
    public static int PROPORTIONS = 1;

    /**
     * 提成金额
     */
    public static int SUMOFMONEY = 2;

    /**
     * 启动Activity   REQUEST_CODE
     */
    public static final int REQUEST_CODE = 1;

    /**
     * 启动Activity   RESULT_CODE
     */
    public static final int RESULT_CODE = 2;

    /**
     * 上传照片
     */
    public static final int REQUEST_IMAGE=2;

    /**
     * 角色
     */
    public static class Role {

        /**
         * 员工
         */
        public static int STAFF = 0;

        /**
         * 部门主管
         */
        public static int EXECUTIVE = 1;

        /**
         * CEO
         */
        public static int CEO = 2;

    }


    /**
     * 操作界面TAB
     */
    public static class OperaConstant {

        /**
         * 审批
         */
        public static String EXAMINE = "examine";

        /**
         * 财务录入
         */
        public static String FINANCIAL = "financial";

        /**
         * 查看其它部门
         */
        public static String OTHER_DEPARTMENTS = "other_departments";

        /**
         * 业务提成录入
         */
        public static String PROMOTION = "promotion";

        /**
         * 价值分配录入
         */
        public static String DISTRIBUTION = "distribution";

        /**
         * 业务提成分配
         */
        public static String PROPORTIONS = "proportions";

        /**
         * 组织架构
         */
        public static String MANAGE_ORGANIZATION = "manage_organization";

        /**
         * 添加新员工
         */
        public static String MANAGE_ADDEMPLOYEE = "manage_addemployee";

        /**
         * 部门分配设置
         */
        public static String MANAGE_DEPARTMENT = "manage_department";

        /**
         * 报销管理
         */
        public static String MANAGE_REIMBURSEMENT = "manage_reimbursement";

        /**
         * 权限分配
         */
        public static String MANAGE_AUTHORITY = "manage_authority";

        /**
         * 年度分配系数
         */
        public static String MANAGE_PARTITION = "manage_partition";

        /**
         * 财务数据
         */
        public static String VIEW_FINANCIAL = "view_financial";

        /**
         * 价值分配
         */
        public static String VIEW_DISTRIBUTION = "view_distribution";

        /**
         * 组织架构
         */
        public static String VIEW_ORGANIZATION = "view_organization";

        /**
         * 部门分配比例
         */
        public static String VIEW_DEPARTMENTS = "view_departments";

        /**
         * 报销流程
         */
        public static String VIEW_REIMBURSEMENT = "view_reimbursement";

        /**
         * 权限分配
         */
        public static String VIEW_AUTHORITY = "view_authority";

        /**
         * 年度分配系数
         */
        public static String VIEW_PARTITION = "view_partition";

        /**
         * 费用报销
         */
        public static String REIMBURSEMENT = "reimbursement";
    }

    /**
     * 通知类型
     */
    public static class Notice {
        /**
         * 通知报销审批
         */
        public static int NOTICE_FOR_APPROVAL_OF_Audit_to_play_money= 0;
        /**
         * 通知报销审批
         */
        public static int NOTICE_FOR_APPROVAL_OF_REIMBURSEMENT = 1;


        /**
         * 报销审批通过后抄送
         */
        public static int REIMBURSEMENT_FOR_APPROVAL_AFTER_APPROVAT = 2;

        /**
         * 报销审批通过后通知本人
         */
        public static int NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_APPROVAL_OF_THE_REIMBURSEMENT = 3;

        /**
         * 通知任务审批
         */
        public static int NPTIFICATION_TASK_APPROVAL = 4;

        /**
         * 任务申请审批通过通知本人
         */
        public static int THE_APPLICATION_OF_THE_TASK_ID_APPROVED_BT_THE_NOTIFICATION = 5;

        /**
         * 报销审批被拒绝后通知本人
         */
        public static int NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_REIMBURSEMENT_ID_REJECTED= 6;

        /**
         * 任务完成后通知主管审批
         */
        public static int NOTIFY_THE_SUPERVISOR_FOR_APPROVAL_AFTER_THE_COMPLETION_OF_THE_TASK= 7;

        /**
         * 主管确认完成任务后通知本人
         */
        public static int THE_SUPERVISOR_NOTIFIES_ME_OF_THE_COMPLETION_OF_THE_TASK= 8;

        /**
         * 主管填写迭代修改后通知本人
         */
        public static int THE_SUPERVISOR_FILLS_IN_WITH_AN_ITERATIVE_AMENDMENT_TO_INFORM_ME= 9;
    }

    public static class System {
        /**
         * 比例制
         */
        public static int PROPORTION = 0;

        /**
         * 积分制
         */
        public static int INTEGREL = 1;
    }

    public static String ACTION = "ON_USER_VISIVILITY";

    public static String GET_DATA_BY_SEAON="GET_DATA_BY_SEAON";


}
