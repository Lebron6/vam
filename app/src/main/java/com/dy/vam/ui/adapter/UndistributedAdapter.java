package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.UnDistributed;
import com.dy.vam.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UndistributedAdapter extends RecyclerView.Adapter {


    private Context context;
    private UnDistributed unDistributedUser;
    private int num;

    public UndistributedAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(UnDistributed unDistributedUser,int num) {
        this.unDistributedUser = unDistributedUser;
        this.num = num;
        Log.e("数量",num+"");
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_undistributed, null);
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
        UndistributedUserAdapter adapter = new UndistributedUserAdapter(context, position);
        switch (position) {
            case 0:
                if (unDistributedUser.getData().get_$1().size() > 0) {
                    viewHolder.tvTitle.setText("第一季度");
                    viewHolder.tvTitle.setVisibility(View.VISIBLE); viewHolder.rvUser.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvTitle.setVisibility(View.GONE); viewHolder.rvUser.setVisibility(View.GONE);
                }
                break;
            case 1:
                if (unDistributedUser.getData().get_$2().size() > 0) {
                    viewHolder.tvTitle.setText("第二季度");
                    viewHolder.tvTitle.setVisibility(View.VISIBLE); viewHolder.rvUser.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvTitle.setVisibility(View.GONE); viewHolder.rvUser.setVisibility(View.GONE);
                }
                break;
            case 2:
                if (unDistributedUser.getData().get_$3().size() > 0) {
                    viewHolder.tvTitle.setText("第三季度");
                    viewHolder.tvTitle.setVisibility(View.VISIBLE); viewHolder.rvUser.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvTitle.setVisibility(View.GONE); viewHolder.rvUser.setVisibility(View.GONE);
                }
                break;
            case 3:
                if (unDistributedUser.getData().get_$4().size() > 0) {
                    viewHolder.tvTitle.setText("第四季度");
                    viewHolder.tvTitle.setVisibility(View.VISIBLE);
                    viewHolder.rvUser.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvTitle.setVisibility(View.GONE);
                    viewHolder.rvUser.setVisibility(View.GONE);
                }
                break;
        }

        adapter.setDatas(unDistributedUser.getData());
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.rvUser, false, adapter);
    }

    @Override
    public int getItemCount() {
     return num;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rv_user)
        RecyclerView rvUser;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}