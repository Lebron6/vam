package com.dy.vam.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.MarketGetCustomList;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideDepartListAdapter extends RecyclerView.Adapter {



    private Activity context;
    SummaryDepartmentData summaryDepartmentData;
    private int selectPosition=0;

    public SlideDepartListAdapter(Activity context,int selectPosition) {
        this.context = context;
        this.selectPosition = selectPosition;
    }

    public void setDatas(SummaryDepartmentData summaryDepartmentData) {
        this.summaryDepartmentData=summaryDepartmentData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slide_depart, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
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
        if (position==selectPosition){
            viewHolder.tvDepartName.setTextColor(context.getResources().getColor(R.color.colorTheme));
        }
viewHolder.tvDepartName.setText(summaryDepartmentData.getData().getDepartment().get(position).getName());
    }


    @Override
    public int getItemCount() {
        return summaryDepartmentData.getData().getDepartment().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_depart_name)
        TextView tvDepartName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}