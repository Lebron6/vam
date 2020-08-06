package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.FinancialGetCustomList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessPromotionAdapter extends RecyclerView.Adapter {



    private Context context;
    private FinancialGetCustomList financialGetCustomList;

    public BusinessPromotionAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(FinancialGetCustomList financialGetCustomList) {
        this.financialGetCustomList = financialGetCustomList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_promotion, null);
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
        FinancialGetCustomList.DataBean.ListBean bean = financialGetCustomList.getData().getList().get(position);
        viewHolder.tvCommiterName.setText("提交人:"+"  "+bean.getPost()+"  "+bean.getUsername());
        viewHolder.tvCustomerName.setText(bean.getName());
        viewHolder.tvTime.setText(bean.getCreatetime());
    }

    @Override
    public int getItemCount() {
        return financialGetCustomList.getData().getList().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_customer_type)
        TextView tvCustomerType;
        @BindView(R.id.tv_customer_name)
        TextView tvCustomerName;
        @BindView(R.id.tv_commiter_name)
        TextView tvCommiterName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}