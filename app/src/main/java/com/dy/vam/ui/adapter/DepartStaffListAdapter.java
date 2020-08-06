package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.entity.DepartmentUsers;
import com.dy.vam.tools.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartStaffListAdapter extends RecyclerView.Adapter {



    private Context context;
    private DepartmentUsers departmentUsers;

    public DepartStaffListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(DepartmentUsers departmentUsers) {
        this.departmentUsers = departmentUsers;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_depart_staff, null);
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
        Glide.with(context).load(departmentUsers.getData().get(position).getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imageIcon);
        viewHolder.tvName.setText(departmentUsers.getData().get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return departmentUsers.getData().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_icon)
        ImageView imageIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}