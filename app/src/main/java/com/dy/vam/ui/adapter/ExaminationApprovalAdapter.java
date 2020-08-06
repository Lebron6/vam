package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.entity.ExamineApprove;
import com.dy.vam.tools.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_COST;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_TASK;

public class ExaminationApprovalAdapter extends RecyclerView.Adapter {


    private Context context;
    private ExamineApprove examineApprove;
    private int type;

    public ExaminationApprovalAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(ExamineApprove examineApprove) {
        this.examineApprove = examineApprove;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_examination_approval, null);
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
        Glide.with(context).load(examineApprove.getData().getList().get(position).getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imagePic);
        if (type == EXAMINE_APPROVE_TYPE_TASK) {
            viewHolder.tvExplain.setText(
                    "任务名称:" + examineApprove.getData().getList().get(position).getName());
            viewHolder.tv_content.setText(TextUtils.isEmpty(examineApprove.getData().getList().get(position).getMoney()) ? "总报销金额:" : "总报销金额:"+examineApprove.getData().getList().get(position).getMoney()+"元");
            viewHolder.tvTime.setText(examineApprove.getData().getList().get(position).getCommittime());
            if (examineApprove.getData().getList().get(position).getExamine() == 0) {
                viewHolder.tvStatus.setText("审批中");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextYello));
            } else if (examineApprove.getData().getList().get(position).getExamine() == 1) {
                viewHolder.tvStatus.setText("审批中");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
            } else if(examineApprove.getData().getList().get(position).getExamine() == 2){
                viewHolder.tvStatus.setText("审批通过");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextTheme));
            }else if (examineApprove.getData().getList().get(position).getExamine() == 3){
                viewHolder.tvStatus.setText("已驳回");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextYello));
            }
        } else {
            viewHolder.tvExplain.setText(TextUtils.isEmpty(examineApprove.getData().getList().get(position).getExplain()) ? "报销类型:" : "报销类型:"+examineApprove.getData().getList().get(position).getExplain());
            viewHolder.tv_content.setText(TextUtils.isEmpty(examineApprove.getData().getList().get(position).getMoney()) ? "总报销金额:" : "总报销金额:"+examineApprove.getData().getList().get(position).getMoney()+"元");
            viewHolder.tvTime.setText(examineApprove.getData().getList().get(position).getCreatetime());
            if (examineApprove.getData().getList().get(position).getExamine() == 0) {
                viewHolder.tvStatus.setText("审批中");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
            } else if (examineApprove.getData().getList().get(position).getExamine() == 1) {
                viewHolder.tvStatus.setText("审批通过");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextTheme));
            } else if (examineApprove.getData().getList().get(position).getExamine() == 3){
                viewHolder.tvStatus.setText("已驳回");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
            } else{
                viewHolder.tvStatus.setText("审批不通过");
                viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
            }
        }

        viewHolder.tvType.setText(examineApprove.getData().getList().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return examineApprove.getData().getList().size();
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
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_explain)
        TextView tvExplain;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_content)
        TextView tv_content;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}