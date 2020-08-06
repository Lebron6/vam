package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryGetData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewDynamicRecyAdapter extends RecyclerView.Adapter {

    private Context context;
    private SummaryGetData summaryGetData;

    public NewDynamicRecyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SummaryGetData summaryGetData ) {
        this.summaryGetData = summaryGetData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_dynamic, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        NewDynamicRecyAdapter.ViewHolder viewHolder = (NewDynamicRecyAdapter.ViewHolder) holder;
        if (position == 0) {
            viewHolder.viewNull.setVisibility(View.GONE);
        } else {
            viewHolder.viewNull.setVisibility(View.VISIBLE);
        }
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvDynamicDetails.setText( summaryGetData.getData().getTrends().getList().get(position).getTitle());
        viewHolder.tvDynamicNumber.setText(summaryGetData.getData().getTrends().getList().get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return summaryGetData.getData().getTrends().getList().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_null)
        View viewNull;
        @BindView(R.id.tv_dynamic_details)
        TextView tvDynamicDetails;
        @BindView(R.id.tv_dynamic_number)
        TextView tvDynamicNumber;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}