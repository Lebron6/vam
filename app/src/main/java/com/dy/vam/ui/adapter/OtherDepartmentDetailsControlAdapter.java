package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.OtherDepartmentDetailsData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherDepartmentDetailsControlAdapter extends RecyclerView.Adapter {


    private Context context;
    private OtherDepartmentDetailsData otherDepartmentDetailsData;

    public OtherDepartmentDetailsControlAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OtherDepartmentDetailsData otherDepartmentDetailsData) {
        this.otherDepartmentDetailsData = otherDepartmentDetailsData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ie_details_, null);
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

                viewHolder.tvNumber.setText(otherDepartmentDetailsData.getData().getDisposable_list().get(position).getDisposable());
//                viewHolder.tvType.setText(otherDepartmentDetailsData.getData().getList().get(position).getTitle());

        viewHolder.tvTime.setText(otherDepartmentDetailsData.getData().getDisposable_list().get(position).getMonth()+"月份");

    }

    @Override
    public int getItemCount() {
        return otherDepartmentDetailsData.getData().getDisposable_list()==null?0:otherDepartmentDetailsData.getData().getDisposable_list().size();
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