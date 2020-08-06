package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.Organization;
import com.dy.vam.ui.activity.UpdateDepartmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dy.vam.config.Constant.OperaConstant.MANAGE_ORGANIZATION;

public class EditorialDepartMentAdapter extends RecyclerView.Adapter {


    private Context context;
    private Organization organization;
    private  String type;

    public EditorialDepartMentAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(Organization organization) {
        this.organization = organization;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_editorial_department, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
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
        if (type.equals(MANAGE_ORGANIZATION)) {
            viewHolder.imgDeleteDepart.setVisibility(View.VISIBLE);
        }else{
            viewHolder.imgDeleteDepart.setVisibility(View.INVISIBLE);
        }
        viewHolder.tvDepartName.setText(organization.getData().getDepartment().get(position).getName()+"("+organization.getData().getDepartment().get(position).getCount()+")");
        viewHolder.imgDeleteDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDepartmentActivity.actionStart(context,"编辑部门",organization.getData().getDepartment().get(position).getDepartmentid(),organization.getData().getDepartment().get(position).getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return organization.getData().getDepartment().size();
    }

    private OnItemClickLitener mOnItemClickLitener;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_depart_name)
        TextView tvDepartName;
        @BindView(R.id.img_delete_depart)
        ImageView imgDeleteDepart;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}