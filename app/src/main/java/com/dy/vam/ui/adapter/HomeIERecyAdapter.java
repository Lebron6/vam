package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.HomeRecylerData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeIERecyAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<HomeRecylerData> homeRecylerDatas;
    private Double tag;

    public HomeIERecyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<HomeRecylerData> homeRecylerDatas,Double maximumValue) {
        this.homeRecylerDatas = homeRecylerDatas;
        tag=context.getResources().getDimension(R.dimen.x263)/maximumValue;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ie_bar, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (position == 0) {
            viewHolder.layoutNull.setVisibility(View.GONE);
        } else {
            viewHolder.layoutNull.setVisibility(View.VISIBLE);
        }
        viewHolder.tvMonth.setText(homeRecylerDatas.get(position).getMonth());
        viewHolder.tvNum.setText(homeRecylerDatas.get(position).getNumber());
        viewHolder.layoutProgress.setLayoutParams(new LinearLayout.LayoutParams((int)context.getResources().getDimension(R.dimen.x35),(int)(homeRecylerDatas.get(position).getPercent()*tag)) );
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return homeRecylerDatas.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_null)
        RelativeLayout layoutNull;
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.layout_progress)
        LinearLayout layoutProgress;
        @BindView(R.id.layout_all_height)
        LinearLayout layoutAllHeight;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}