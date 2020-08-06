package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.MarketGetCustomList;
import com.dy.vam.config.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemLongClick;

public class BusinessAllocationChildAdapter extends RecyclerView.Adapter {



    private Context context;
    private MarketGetCustomList marketGetCustomList;
    private int type;       //标记是员工 提成比例  提成金额
    private int positionP;

    public BusinessAllocationChildAdapter(Context context,int type,int positionP) {
        this.context = context;
        this.positionP = positionP;
        this.type = type;
    }

    public void setDatas(MarketGetCustomList marketGetCustomList, BusinessAllocationsAdapter.OnItemLongClickListener onItemLongClickListener) {
        this.marketGetCustomList = marketGetCustomList;
        this.mOnItemLongClickListener = onItemLongClickListener;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_allocation_child, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v, positionP);
                    return false;
                }
            });
        }
        if (type==Constant.STAFF){
            viewHolder.tvContent.setText(marketGetCustomList.getData().getList().get(positionP).getUsers().get(position).getUsername()+"");
        }else if(type==Constant.PROPORTIONS){
            viewHolder.tvContent.setText(marketGetCustomList.getData().getList().get(positionP).getUsers().get(position).getProportion()+"%");
        }else if(type==Constant.SUMOFMONEY){
            viewHolder.tvContent.setText(marketGetCustomList.getData().getList().get(positionP).getUsers().get(position).getMoney()+"");
        }
    }

    private BusinessAllocationsAdapter.OnItemLongClickListener mOnItemLongClickListener;


    @Override
    public int getItemCount() {
            return marketGetCustomList.getData().getList().get(positionP).getUsers().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}