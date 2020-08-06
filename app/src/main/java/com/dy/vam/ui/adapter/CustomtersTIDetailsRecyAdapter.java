package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryGetDataChart;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomtersTIDetailsRecyAdapter extends RecyclerView.Adapter {



    private Context context;
    private SummaryGetDataChart.DataBean data ;
    private JsonObject asJsonObject;

    public CustomtersTIDetailsRecyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SummaryGetDataChart.DataBean data) {
        this.data = data;
        asJsonObject = new JsonParser().parse(new Gson().toJson(data)).getAsJsonObject();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customers_total_income_details, null);
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
        viewHolder.tvMonth.setText(position+1+"月份");
        viewHolder.tvNumber.setText(asJsonObject.get(String.valueOf(position+1)).getAsFloat()+"");
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}