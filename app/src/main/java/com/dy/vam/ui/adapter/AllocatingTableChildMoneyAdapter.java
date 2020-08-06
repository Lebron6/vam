package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dy.vam.R;
import com.dy.vam.entity.GetCommissionData;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllocatingTableChildMoneyAdapter extends RecyclerView.Adapter {



    private Context context;
    private GetCommissionData.DataBean.CustomBean customBean;

    public AllocatingTableChildMoneyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GetCommissionData.DataBean.CustomBean customBean) {
        this.customBean = customBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_child_allocation_proportion_and_money_, parent,false);//解决不居中的问题
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
        viewHolder.tvContent.setText(customBean.getUsers().get(position).getMoney());

    }

    @Override
    public int getItemCount() {
        return customBean.getUsers().size();
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
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}