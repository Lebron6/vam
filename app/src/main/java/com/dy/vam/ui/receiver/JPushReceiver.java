package com.dy.vam.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.dy.vam.entity.Extras;
import com.dy.vam.ui.activity.ExamineTaskOverActivity;
import com.dy.vam.ui.activity.NewTaskToBeEApprovedActivity;
import com.dy.vam.ui.activity.RecevierIntentActivity;
import com.google.gson.Gson;
import cn.jpush.android.api.JPushInterface;
import static com.dy.vam.config.Constant.Notice.NOTICE_FOR_APPROVAL_OF_REIMBURSEMENT;
import static com.dy.vam.config.Constant.Notice.NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_APPROVAL_OF_THE_REIMBURSEMENT;
import static com.dy.vam.config.Constant.Notice.NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_REIMBURSEMENT_ID_REJECTED;
import static com.dy.vam.config.Constant.Notice.NOTIFY_THE_SUPERVISOR_FOR_APPROVAL_AFTER_THE_COMPLETION_OF_THE_TASK;
import static com.dy.vam.config.Constant.Notice.NPTIFICATION_TASK_APPROVAL;
import static com.dy.vam.config.Constant.Notice.REIMBURSEMENT_FOR_APPROVAL_AFTER_APPROVAT;
import static com.dy.vam.config.Constant.Notice.THE_APPLICATION_OF_THE_TASK_ID_APPROVED_BT_THE_NOTIFICATION;
import static com.dy.vam.config.Constant.Notice.THE_SUPERVISOR_FILLS_IN_WITH_AN_ITERATIVE_AMENDMENT_TO_INFORM_ME;
import static com.dy.vam.config.Constant.Notice.THE_SUPERVISOR_NOTIFIES_ME_OF_THE_COMPLETION_OF_THE_TASK;

public class JPushReceiver extends BroadcastReceiver {

    private String TAG = "JPushReceiver";

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.e(TAG, "[MyReceiver] 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {//收到自定义消息

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {//点击了通知
            processingNotifications(context,bundle.getString(JPushInterface.EXTRA_EXTRA));
        } else {
            Log.e(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void processingNotifications(Context context, String notificationsJson) {
//        Log.e("解析通知内容", notificationsJson);
        Extras extras = new Gson().fromJson(notificationsJson, Extras.class);
        if (extras.getMsg_content()==NOTICE_FOR_APPROVAL_OF_REIMBURSEMENT){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }else if(extras.getMsg_content()==REIMBURSEMENT_FOR_APPROVAL_AFTER_APPROVAT){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }else if(extras.getMsg_content()==NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_APPROVAL_OF_THE_REIMBURSEMENT){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }else if(extras.getMsg_content()==NPTIFICATION_TASK_APPROVAL){  //通知任务审批，跳原生界面
            NewTaskToBeEApprovedActivity.actionStart(context,extras.getTitle(), extras.getTaskid());
        }else if(extras.getMsg_content()==THE_APPLICATION_OF_THE_TASK_ID_APPROVED_BT_THE_NOTIFICATION){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }else if(extras.getMsg_content()==NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_REIMBURSEMENT_ID_REJECTED){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }else if(extras.getMsg_content()==NOTIFY_THE_SUPERVISOR_FOR_APPROVAL_AFTER_THE_COMPLETION_OF_THE_TASK){//完成任务待审核
            ExamineTaskOverActivity.actionStart(context,extras.getTitle(), extras.getTaskid());
        }else if(extras.getMsg_content()==THE_SUPERVISOR_NOTIFIES_ME_OF_THE_COMPLETION_OF_THE_TASK){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }else if(extras.getMsg_content()==THE_SUPERVISOR_FILLS_IN_WITH_AN_ITERATIVE_AMENDMENT_TO_INFORM_ME){
            RecevierIntentActivity.actionStart(context,notificationsJson);
        }

    }
}