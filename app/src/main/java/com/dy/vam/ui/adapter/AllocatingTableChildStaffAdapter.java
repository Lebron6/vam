package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.GetCommissionData;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_DISTRIBUTION;

public class AllocatingTableChildStaffAdapter extends RecyclerView.Adapter {


    private Context context;
    private GetCommissionData.DataBean.CustomBean customBean;
    private  String type;

    public AllocatingTableChildStaffAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }
    public void setDatas(GetCommissionData.DataBean.CustomBean customBean) {
        this.customBean = customBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_child_allocation_staff, parent,false);//解决不居中的问题
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (customBean.getUsers().get(position).type == customBean.getUsers().get(position).TYPE_CHECKED){
                        customBean.getUsers().get(position).type = customBean.getUsers().get(position).TYPE_NOCHECKED;
                    } else {
                        customBean.getUsers().get(position).type = customBean.getUsers().get(position).TYPE_CHECKED;
                    }
                    //每次点击后数据更新   但checkbox不会随Item点击发生选中状态变化，notifyDataSetChanged();之后数据与View视图同步
                    notifyDataSetChanged();
                }
            });
        if (type.equals(VIEW_DISTRIBUTION)) {
            viewHolder.cbChecked.setVisibility(View.GONE);
        }else{
            viewHolder.cbChecked.setVisibility(View.VISIBLE);
        }
        viewHolder.tvName.setText(customBean.getUsers().get(position).getUsername());
        if(customBean.getUsers().get(position).type ==customBean.getUsers().get(position).TYPE_CHECKED){
            viewHolder.cbChecked.setChecked(true);
        }else{
            viewHolder.cbChecked.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return customBean.getUsers().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position, RecyclerView.ViewHolder viewHolder);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbChecked;
        TextView tvName;
        public ViewHolder(View view) {
            super(view);
            cbChecked=view.findViewById(R.id.cb_checked);
            tvName=view.findViewById(R.id.tv_name);
        }
    }
}