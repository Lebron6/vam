package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.OtherDepartment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherDepartmentsAdapter extends RecyclerView.Adapter {



    private Context context;
    private OtherDepartment otherDepartment;

    public OtherDepartmentsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OtherDepartment otherDepartment) {
        this.otherDepartment = otherDepartment;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_other_departments, null);
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
        viewHolder.tvDepartmentsName.setText(otherDepartment.getData().get(position).getName());
        viewHolder.tvDistributionRatio.setText("分配比例:"+otherDepartment.getData().get(position).getDistrubution());
    }

    @Override
    public int getItemCount() {
        return otherDepartment.getData()==null?0:otherDepartment.getData().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_departments_name)
        TextView tvDepartmentsName;
        @BindView(R.id.tv_distribution_ratio)
        TextView tvDistributionRatio;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}