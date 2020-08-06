package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.FinalcinalAmount;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.widget.CustomRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinancialInputOutputAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<FinalcinalAmount.DataBean.ListBean> amount;
    private String type;

    public FinancialInputOutputAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(List<FinalcinalAmount.DataBean.ListBean> amount) {
        this.amount = amount;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_financial_output, null);
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

        ViewGroup.LayoutParams layoutParams =  viewHolder.rvFinancialOutput.getLayoutParams();
//        switch (position){
//            case 0:
//                if (amount.getData().getList().getOutput().getOther()==null||amount.getData().getList().getOutput().getOther().size()<=0){
//                    viewHolder.tvOutTab.setVisibility(View.GONE);
//                }else{
//                    viewHolder.tvOutTab.setVisibility(View.VISIBLE);
//                }
//                viewHolder.tvOutTab.setText("成本");
////                layoutParams.height = ( amount.getData().getList().getOutput().getOther().size())*(int) context.getResources().getDimension(R.dimen.x183);
//                layoutParams.height = ( amount.getData().getList().getOutput().getOther().size()+1)*(int) context.getResources().getDimension(R.dimen.x183);
//
//                break;
//            case 1:
//                if (amount.getData().getList().getOutput().getPublicX()==null||amount.getData().getList().getOutput().getPublicX().size()<=0){
//                    viewHolder.tvOutTab.setVisibility(View.GONE);
//                }else{
//                    viewHolder.tvOutTab.setVisibility(View.VISIBLE);
//                }
//                viewHolder.tvOutTab.setText("总公共费用");
//                layoutParams.height = ( amount.getData().getList().getOutput().getPublicX().size())*(int) context.getResources().getDimension(R.dimen.x183);
//                break;
//            case 2:
//                if (amount.getData().getList().getOutput().getSalary()==null||amount.getData().getList().getOutput().getSalary().size()<=0){
//                    viewHolder.tvOutTab.setVisibility(View.GONE);
//                }else{
//                    viewHolder.tvOutTab.setVisibility(View.VISIBLE);
//                }
                viewHolder.tvOutTab.setText(amount.get(position).getTitle());
                layoutParams.height = ( amount.size())*(int) context.getResources().getDimension(R.dimen.x183);
//                break;
//        }

        viewHolder.rvFinancialOutput.setLayoutParams(layoutParams);
//        FinancialInputOutChildAdapter financialInputOutChildAdapter = new FinancialInputOutChildAdapter(context,position,type);
//        financialInputOutChildAdapter.setDatas(amount);
//        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.rvFinancialOutput, false,financialInputOutChildAdapter);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_out_tab)
        TextView tvOutTab;
        @BindView(R.id.rv_financial_output)
        CustomRecyclerView rvFinancialOutput;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}