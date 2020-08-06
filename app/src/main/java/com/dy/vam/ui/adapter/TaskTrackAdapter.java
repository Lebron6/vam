package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.GetTaskOverInfo;
import com.dy.vam.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskTrackAdapter extends RecyclerView.Adapter {



    private Context context;
    private GetTaskOverInfo taskOverInfo;
    private int count=0;
    private boolean isOver=false;//标识当前任务是否已结束

    public TaskTrackAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GetTaskOverInfo taskOverInfo,int count,boolean isOver) {
        this.taskOverInfo = taskOverInfo;
        this.count=count;
        this.isOver=isOver;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task_track, null);
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
        if (position==0){
            viewHolder.tvTitle.setText("新增任务");     //首部
        }else if(position==count-1&&taskOverInfo.getData().getFinishtask()!=null&&taskOverInfo.getData().getFinishtask().size()>0){
            viewHolder.tvTitle.setText("完成任务");     //尾部
        }else{
            viewHolder.tvTitle.setText("提示修改"+taskOverInfo.getData().getIteration().get(position-1).getSort()); //body
        }

        if (position==0||(position==count-1&&taskOverInfo.getData().getFinishtask()!=null&&taskOverInfo.getData().getFinishtask().size()>0)){
            viewHolder.tvTipsTime.setVisibility(View.GONE);
            viewHolder.tvTips.setVisibility(View.GONE);
            viewHolder.rvTrackContent.setVisibility(View.VISIBLE);
            TaskTrackEAAdapter eaAdapter=new TaskTrackEAAdapter(context);
            eaAdapter.setDatas(taskOverInfo,position);
            RecyclerViewHelper.initRecyclerViewV(context,viewHolder.rvTrackContent,false,eaAdapter);
        }else{
            viewHolder.tvTipsTime.setVisibility(View.VISIBLE);
            viewHolder.tvTips.setVisibility(View.VISIBLE);
            viewHolder.rvTrackContent.setVisibility(View.GONE);
            if (taskOverInfo.getData().getIteration()!=null&&taskOverInfo.getData().getIteration().size()>0){
                Log.e("position",position+"");
                viewHolder.tvTipsTime.setText(taskOverInfo.getData().getIteration().get(position-1).getCreatetime());
                viewHolder.tvTips.setText("提示说明："+taskOverInfo.getData().getIteration().get(position-1).getInfo());
            }
        }
        if (position == 0) {
            viewHolder.layoutLine.setPadding(0, (int) context.getResources().getDimension(R.dimen.x16), 0, 0);
        } else {
            viewHolder.layoutLine.setPadding(0, 0, 0, 0);
        }
        if (position == count-1) {
viewHolder.viewLine.setVisibility(View.GONE);
        }else{
            viewHolder.viewLine.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return count;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_tips_time)
        TextView tvTipsTime;
        @BindView(R.id.tv_tips)
        TextView tvTips;
        @BindView(R.id.rv_track_content)
        RecyclerView rvTrackContent;
        @BindView(R.id.layout_line)
        RelativeLayout layoutLine;
        @BindView(R.id.view_line)
        View viewLine;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}