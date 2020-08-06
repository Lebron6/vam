package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.entity.OperaList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OperaViewAdapter extends RecyclerView.Adapter {



    private Context context;
    private OperaList.DataBean dataBean;

    public OperaViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OperaList.DataBean dataBean) {
        this.dataBean = dataBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera_mange,null);
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
                    mOnItemClickLitener.onViewItemClick(holder.itemView, pos);
                }
            });
        }
        Glide.with(context).load(dataBean.getView().get(position).getIcon()).into(viewHolder.imageIcon);
        viewHolder.tvContent.setText(dataBean.getView().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataBean.getView().size();
    }

    private OnViewItemClickLitener mOnItemClickLitener;

    public interface OnViewItemClickLitener {
        void onViewItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnViewItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_icon)
        ImageView imageIcon;
        @BindView(R.id.layout_mange)
        LinearLayout layoutMange;
        @BindView(R.id.tv_tab_title)
        TextView tvContent;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}