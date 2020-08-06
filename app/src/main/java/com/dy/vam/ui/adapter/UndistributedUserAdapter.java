package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.UnDistributed;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UndistributedUserAdapter extends RecyclerView.Adapter {


    private Context context;
    UnDistributed.DataBean dataBean;
    private int positionP;
    public UndistributedUserAdapter(Context context,int positionP) {
        this.context = context;
        this.positionP = positionP;
    }

    public void setDatas(UnDistributed.DataBean dataBean) {
        this.dataBean = dataBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_undistributed_user, null);
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
        if (positionP==0){
          if (dataBean.get_$1().size()>0){
              viewHolder.tvName.setText(dataBean.get_$1().get(position).getUsername());
              viewHolder.tvMoney.setText(dataBean.get_$1().get(position).getMoney());
          }

        }else if(positionP==1){
            if (dataBean.get_$2().size()>0){
                viewHolder.tvName.setText(dataBean.get_$2().get(position).getUsername());
                viewHolder.tvMoney.setText(dataBean.get_$2().get(position).getMoney());
            }

        }else if(positionP==2){
            if (dataBean.get_$3().size()>0){
                viewHolder.tvName.setText(dataBean.get_$3().get(position).getUsername());
                viewHolder.tvMoney.setText(dataBean.get_$3().get(position).getMoney());
            }

        }else {
            if (dataBean.get_$4().size()>0){
                viewHolder.tvName.setText(dataBean.get_$4().get(position).getUsername());
                viewHolder.tvMoney.setText(dataBean.get_$4().get(position).getMoney());
            }

        }

    }

    @Override
    public int getItemCount() {
        switch (positionP){
            case 0:
                return dataBean.get_$1().size();
            case 1:
                return dataBean.get_$2().size();
            case 2:
                return dataBean.get_$3().size();
            case 3:
                return dataBean.get_$4().size();
        }
        return 0;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}