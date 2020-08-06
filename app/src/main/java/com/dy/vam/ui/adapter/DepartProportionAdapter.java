package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryDepartmentData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartProportionAdapter extends RecyclerView.Adapter {

    private Context context;
    private SummaryDepartmentData summaryDepartmentData;

    public DepartProportionAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SummaryDepartmentData summaryDepartmentData) {
        this.summaryDepartmentData = summaryDepartmentData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_depart_home, null);
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
//        viewHolder.tvMoney.setText(summaryDepartmentData.getData().getUserdist().get(position).getMoney());
//        viewHolder.tvName.setText(summaryDepartmentData.getData().getUserdist().get(position).getName());
//        viewHolder.tvPostion.setText(summaryDepartmentData.getData().getUserdist().get(position).getPost());
//        viewHolder.tvProportion.setText(summaryDepartmentData.getData().getUserdist().get(position).getDistrubution());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_postion)
        TextView tvPostion;
        @BindView(R.id.tv_proportion)
        TextView tvProportion;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}