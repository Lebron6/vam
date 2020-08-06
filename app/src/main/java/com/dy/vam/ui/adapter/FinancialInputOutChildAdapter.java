package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.FinalcinalAmount;
import com.dy.vam.tools.SimpeTextWather;
import com.dy.vam.ui.activity.AllocatingTableCrossActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_FINANCIAL;

public class FinancialInputOutChildAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<FinalcinalAmount.DataBean.ListBean> amount;
    private int positionType;   //标记是 财务录入中的支出的三项： 0总公共费用、1工资社保、2其它
    private String roleType;   //标记是角色 判断EditText是否可编辑

    public FinancialInputOutChildAdapter(Context context, String roleType) {
        this.context = context;
        this.positionType = positionType;
        this.roleType = roleType;
    }

    public void setDatas(List<FinalcinalAmount.DataBean.ListBean> amount) {
        this.amount = amount;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_financial_income, null);
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
        if (roleType.equals(VIEW_FINANCIAL)) {
            viewHolder.etMoney.setEnabled(false);
        } else {
            viewHolder.etMoney.setEnabled(true);
        }
//        switch (positionType) {
//            case 0:
                viewHolder.tvTitle.setText(amount.get(position).getTitle() + "");
                viewHolder.etMoney.setText(amount.get(position).getMoney() + "");
//                break;
//            case 1:
//                if (amount.getData().getList().getOutput().getPublicX().get(position).getTitle().equals("业务提成")) {
//                    viewHolder.tvViewDetails.setVisibility(View.VISIBLE);
//                    viewHolder.etMoney.setTextColor(context.getResources().getColor(R.color.colorTheme));
//                    viewHolder.etMoney.setEnabled(false);
//                }
//                viewHolder.tvTitle.setText(amount.getData().getList().getOutput().getPublicX().get(position).getTitle() + "");
//                viewHolder.etMoney.setText(amount.getData().getList().getOutput().getPublicX().get(position).getMoney() + "");
//                break;
//            case 2:
//                viewHolder.tvTitle.setText(amount.getData().getList().getOutput().getSalary().get(position).getTitle() + "");
//                viewHolder.etMoney.setText(amount.getData().getList().getOutput().getSalary().get(position).getMoney() + "");
//
//                break;
//
//
//        }

        final TextWatcher watcher = new SimpeTextWather() {

            @Override
            public void afterTextChanged(Editable s) {

                switch (positionType) {
                    case 0:
                        if (TextUtils.isEmpty(s)) {
                            amount.get(position).setMoney(0 + "");
                        } else {
                            amount.get(position).setMoney(String.valueOf(s));
                        }
                        break;
                    case 1:
                        if (TextUtils.isEmpty(s)) {
                            amount.get(position).setMoney(0 + "");
                        } else {
                            amount.get(position).setMoney(String.valueOf(s));
                        }
                        break;
                    case 2:
                        if (TextUtils.isEmpty(s)) {
                            amount.get(position).setMoney(0 + "");
                        } else {
                            amount.get(position).setMoney(String.valueOf(s));
                        }

                        break;
                }

            }
        };
        viewHolder.etMoney.addTextChangedListener(watcher);
        viewHolder.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllocatingTableCrossActivity.actionStart(context, "提成分配表");
            }
        });
    }

    @Override
    public int getItemCount() {
//        switch (positionType) {
//            case 0:
                return amount.size();
//            case 1:
//                return amount.getData().getList().getOutput().getPublicX().size();
//            case 2:
//                return amount.getData().getList().getOutput().getSalary().size();
//        }
//        return 0;

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
        @BindView(R.id.et_money)
        EditText etMoney;
        @BindView(R.id.tv_view_details)
        TextView tvViewDetails;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}