package com.dy.vam.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.entity.ContentBean;
import com.dy.vam.entity.Notice;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.GlideCircleTransform;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private Notice notice;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(Notice notice) {
        this.notice = notice;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

        Glide.with(context).load(notice.getData().getList().getData().get(position).getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imagePic);
        viewHolder.tvType.setText(notice.getData().getList().getData().get(position).getMsg());
        viewHolder.tvStatusContent.setText(notice.getData().getList().getData().get(position).getMsg());


        Notice.DataBeanX.ListBean.DataBean.ContentBean contentBean = notice.getData().getList().getData().get(position).getContent();


        viewHolder.tvUserInfo.setText(contentBean.getLine1());
        viewHolder.tvContentTitle.setText(contentBean.getLine2());

        if (TextUtils.isEmpty(contentBean.getLine3())) {
            viewHolder.tvContent.setVisibility(View.GONE);
        } else {
            viewHolder.tvContent.setVisibility(View.VISIBLE);
            viewHolder.tvContent.setText(contentBean.getLine3());
        }


        viewHolder.tvTime.setText(notice.getData().getList().getData().get(position).getCreatetime());

        if (!TextUtils.isEmpty(notice.getData().getList().getData().get(position).getStatus())) {
            viewHolder.tvStatus.setVisibility(View.VISIBLE);

            String color = notice.getData().getList().getData().get(position).getColor();
            if (!TextUtils.isEmpty(color)) {
                viewHolder.tvStatus.setTextColor(Color.parseColor(notice.getData().getList().getData().get(position).getColor()));
            }
            viewHolder.tvStatus.setText(notice.getData().getList().getData().get(position).getStatus());
        } else {
            viewHolder.tvStatus.setVisibility(View.GONE);
        }

//        if (notice.getData().getList().get(position).getType() == Constant.Notice.NOTICE_FOR_APPROVAL_OF_REIMBURSEMENT) {
        viewHolder.tvMainType.setText(notice.getData().getList().getData().get(position).getTitle());
        viewHolder.tvType.setText(notice.getData().getList().getData().get(position).getTitle());
//        } else if (notice.getData().getList().get(position).getType() == Constant.Notice.REIMBURSEMENT_FOR_APPROVAL_AFTER_APPROVAT) {
//            viewHolder.tvMainType.setText("抄送");
//            viewHolder.tvType.setText("抄送");
//        } else if (notice.getData().getList().get(position).getType() == Constant.Notice.NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_APPROVAL_OF_THE_REIMBURSEMENT) {
//            viewHolder.tvMainType.setText("审计打款");
//            viewHolder.tvType.setText("审计打款");
//        } else if (notice.getData().getList().get(position).getType() == Constant.Notice.NPTIFICATION_TASK_APPROVAL) {
//            viewHolder.tvMainType.setText("任务审批");
//            viewHolder.tvType.setText("任务审批");
//        } else if (notice.getData().getList().get(position).getType() == Constant.Notice.THE_APPLICATION_OF_THE_TASK_ID_APPROVED_BT_THE_NOTIFICATION) {
//            viewHolder.tvMainType.setText("任务审批");
//            viewHolder.tvType.setText("任务审批");
//        } else if (notice.getData().getList().get(position).getType() == Constant.Notice.NOTIFY_THE_SUPERVISOR_FOR_APPROVAL_AFTER_THE_COMPLETION_OF_THE_TASK) {
//            viewHolder.tvMainType.setText("任务审批");
//            viewHolder.tvType.setText("任务审批");
//        } else if (notice.getData().getList().get(position).getType() == Constant.Notice.THE_SUPERVISOR_NOTIFIES_ME_OF_THE_COMPLETION_OF_THE_TASK) {
//            viewHolder.tvMainType.setText("任务审批");
//            viewHolder.tvType.setText("任务审批");
//        }else if(notice.getData().getList().get(position).getType() == Constant.Notice.THE_SUPERVISOR_FILLS_IN_WITH_AN_ITERATIVE_AMENDMENT_TO_INFORM_ME){
//            viewHolder.tvMainType.setText("任务审批");
//            viewHolder.tvType.setText("任务审批");
//        }else if(notice.getData().getList().get(position).getType() == Constant.Notice.NOTICE_FOR_APPROVAL_OF_Audit_to_play_money){
//            viewHolder.tvMainType.setText("审计打款");
//            viewHolder.tvType.setText("审计打款");
//        }else if (notice.getData().getList().get(position).getType() == Constant.Notice.NOTIFY_ME_AFTER_THE_APPROVAL_OF_THE_REIMBURSEMENT_ID_REJECTED) {
//            viewHolder.tvMainType.setText("报销审批");
//            viewHolder.tvType.setText("报销审批");
//        }

    }

    @Override
    public int getItemCount() {
        return notice.getData().getList().getData().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_pic)
        ImageView imagePic;
        @BindView(R.id.tv_main_type)
        TextView tvMainType;
        @BindView(R.id.layout_title)
        LinearLayout layoutTitle;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_status_content)
        TextView tvStatusContent;
        @BindView(R.id.tv_user_info)
        TextView tvUserInfo;
        @BindView(R.id.tv_content_title)
        TextView tvContentTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_see_more)
        TextView tvSeeMore;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}