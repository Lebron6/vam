package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.GetCommissionData;
import com.dy.vam.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllocatingTableCrossAdapter extends RecyclerView.Adapter {


    private Context context;
    private GetCommissionData getCommissionData;
    private String type;

    public AllocatingTableCrossAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(GetCommissionData getCommissionData) {
        this.getCommissionData = getCommissionData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_allocating_table_cross_content, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int positionP) {
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
        viewHolder.tvCustomerName.setText(getCommissionData.getData().getCustom().get(positionP).getName());
        viewHolder.tvCustomerTurnover.setText(getCommissionData.getData().getCustom().get(positionP).getTurnover());
        viewHolder.tvGrossProfit.setText(getCommissionData.getData().getCustom().get(positionP).getGrossprofit());

        AllocatingTableChildMoneyAdapter allocatingTableChildMoneyAdapter = new AllocatingTableChildMoneyAdapter(context);
        allocatingTableChildMoneyAdapter.setDatas(getCommissionData.getData().getCustom().get(positionP));
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.lvMoney, false, allocatingTableChildMoneyAdapter);

        AllocatingTableChildProportionAdapter allocatingTableChildProportionAdapter = new AllocatingTableChildProportionAdapter(context);
        allocatingTableChildProportionAdapter.setDatas(getCommissionData.getData().getCustom().get(positionP));
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.lvProportions, false, allocatingTableChildProportionAdapter);

        AllocatingTableChildStaffAdapter allocatingTableChildStaffAdapter = new AllocatingTableChildStaffAdapter(context,type);
        allocatingTableChildStaffAdapter.setDatas(getCommissionData.getData().getCustom().get(positionP));
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.lvStaff, false,allocatingTableChildStaffAdapter);
        viewHolder.lvMoney.setNestedScrollingEnabled(false);
        viewHolder.lvProportions.setNestedScrollingEnabled(false);
        viewHolder.lvStaff.setNestedScrollingEnabled(false);
//        viewHolder.lvMoney.setOnTouchListener(onTouchListener);
//        viewHolder.lvProportions.setOnTouchListener(onTouchListener);
//        viewHolder.lvStaff.setOnTouchListener(onTouchListener);
    }
    View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    return false;
                default:
                    break;
            }
            return true;
        }
    };


    @Override
    public int getItemCount() {
        return getCommissionData.getData().getCustom().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_customer_name)
        TextView tvCustomerName;
        @BindView(R.id.tv_customer_turnover)
        TextView tvCustomerTurnover;
        @BindView(R.id.tv_gross_profit)
        TextView tvGrossProfit;
        @BindView(R.id.lv_staff)
        RecyclerView lvStaff;
        @BindView(R.id.lv_proportions)
        RecyclerView lvProportions;
        @BindView(R.id.lv_money)
        RecyclerView lvMoney;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}