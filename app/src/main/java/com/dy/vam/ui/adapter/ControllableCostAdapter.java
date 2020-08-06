package com.dy.vam.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryGetDepartmentControl;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ControllableCostAdapter extends RecyclerView.Adapter {



    private Context context;
    private SummaryGetDepartmentControl summaryGetDepartmentControl;

    public ControllableCostAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SummaryGetDepartmentControl summaryGetDepartmentControl) {
        this.summaryGetDepartmentControl = summaryGetDepartmentControl;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ie_details, null);
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
        viewHolder.tvNumber.setText(summaryGetDepartmentControl.getData().getCost().get(position).getMoney() + "");
        viewHolder.tvTime.setText(summaryGetDepartmentControl.getData().getMonth() + "");
        viewHolder.tvType.setText(summaryGetDepartmentControl.getData().getCost().get(position).getTitle() + "(元)");
        viewHolder.imageGreen.setBackgroundColor(Color.parseColor(summaryGetDepartmentControl.getData().getCost().get(position).getColor()));
        viewHolder.tvPersont.setText(summaryGetDepartmentControl.getData().getCost().get(position).getProportion() + "%");
    }

    @Override
    public int getItemCount() {
        return summaryGetDepartmentControl.getData().getCost().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_green)
        RoundedImageView imageGreen;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_persont)
        TextView tvPersont;
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