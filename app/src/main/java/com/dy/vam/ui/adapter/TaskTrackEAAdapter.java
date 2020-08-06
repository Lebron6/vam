package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.GetTaskOverInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskTrackEAAdapter extends RecyclerView.Adapter {



    private Context context;
    private GetTaskOverInfo taskOverInfo;
    private int positionP;

    public TaskTrackEAAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GetTaskOverInfo taskOverInfo,int positionP) {
        this.taskOverInfo = taskOverInfo;
        this.positionP = positionP;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task_track_ea, null);
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
        if (positionP == 0) {
            viewHolder.tvContent.setText(taskOverInfo.getData().getNewtask().get(position).getTitle());
            viewHolder.tvTime.setText(taskOverInfo.getData().getNewtask().get(position).getTime());
        }else{
            viewHolder.tvContent.setText(taskOverInfo.getData().getFinishtask().get(position).getTitle());
            viewHolder.tvTime.setText(taskOverInfo.getData().getFinishtask().get(position).getTime());
        }

    }

    @Override
    public int getItemCount() {
        if (positionP==0){
            Log.e("88",""+taskOverInfo.getData().getNewtask().size());
            return taskOverInfo.getData().getNewtask().size();

        }else{
            Log.e("77",""+taskOverInfo.getData().getFinishtask().size());
            return taskOverInfo.getData().getFinishtask().size();
        }
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}