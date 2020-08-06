package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryDataDetails;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IEDetailsAdapter extends RecyclerView.Adapter {


    private Context context;
    private SummaryDataDetails summaryDataDetails;

    public IEDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SummaryDataDetails summaryDataDetails) {
        this.summaryDataDetails = summaryDataDetails;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ie_details, null);
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
        viewHolder.tvNumber.setText(summaryDataDetails.getData().getList().get(position).getMoney()+"");
        viewHolder.tvTime.setText(summaryDataDetails.getData().getList().get(position).getMonth()+"");
        viewHolder.tvType.setText(summaryDataDetails.getData().getList().get(position).getTitle()+"");

    }

    @Override
    public int getItemCount() {
        return summaryDataDetails.getData().getList().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}