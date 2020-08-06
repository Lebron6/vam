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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_FINANCIAL;

public class FinancialInputIncomAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<FinalcinalAmount.DataBean.ListBean>  amount;
    private String type;

    public FinancialInputIncomAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
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

        if (type.equals(VIEW_FINANCIAL)){
            viewHolder.etMoney.setEnabled(false);
        }else{
            viewHolder.etMoney.setEnabled(true);
        }

        final TextWatcher watcher = new SimpeTextWather() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    amount.get(position).setMoney(0+"");
                } else {
                    amount.get(position).setMoney(String.valueOf(s));
                }
            }
        };

        viewHolder.etMoney.addTextChangedListener(watcher);
        viewHolder.tvTitle.setText(amount.get(position).getTitle()+"");
        viewHolder.etMoney.setText(amount.get(position).getMoney()+"");

    }

    @Override
    public int getItemCount() {
        return amount.size();
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
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}