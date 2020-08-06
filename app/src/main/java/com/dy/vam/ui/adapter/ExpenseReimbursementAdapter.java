package com.dy.vam.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.entity.ExpenseReimbursement;
import com.dy.vam.tools.GlideCircleTransform;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpenseReimbursementAdapter extends RecyclerView.Adapter {


    private List<String> idStr = new ArrayList<>();
    private SparseBooleanArray mCheckStates=new SparseBooleanArray();

    private Context context;
    private ExpenseReimbursement expenseReimbursement;

    public List<String> getIdStr() {
        return idStr;
    }



    private boolean flag;
    public ExpenseReimbursementAdapter(Context context) {
        this.context = context;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setDatas(ExpenseReimbursement expenseReimbursement) {
        this.expenseReimbursement = expenseReimbursement;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense_reimbursement, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mCb.setTag(position);//在最开始适配的时候，将每一个CheckBox设置一个当前的Tag值，这样每个CheckBox都有了一个固定的标识
        viewHolder.mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckBox) {
                int pos= (int) buttonView.getTag();//得到当前CheckBox的Tag值，由于之前保存过，所以不会出现索引错乱
                if (isCheckBox){
                    //点击时将当前CheckBox的索引值和Boolean存入SparseBooleanArray中
                    mCheckStates.put(pos,true);
                    if (!idStr.contains(expenseReimbursement.getData().getList().get(position).getExpenseid()+"")) {
                        idStr.add(expenseReimbursement.getData().getList().get(position).getExpenseid() + "");
                    }
                }else {
                    //否则将 当前CheckBox对象从SparseBooleanArray中移除
                    mCheckStates.delete(pos);
                    if (idStr.contains(expenseReimbursement.getData().getList().get(position).getExpenseid()+"")) {
                        idStr.remove(expenseReimbursement.getData().getList().get(position).getExpenseid()+"");
                    }

                }
            }
        });
        //得到CheckBox的Boolean值后，将当前索引的CheckBox状态改变
        viewHolder.mCb.setChecked(mCheckStates.get(position,false));
//        if(viewHolder.mCb.isChecked()){
//            System.out.println("==选中了=");
//
//        }else{
//            System.out.println("==取消了=");
//
//        }
//
//
        if (flag){
            viewHolder.alCb.setVisibility(View.VISIBLE);
        }else {
            viewHolder.alCb.setVisibility(View.GONE);
        }
//
//        if (expenseReimbursement.getData().getList().get(position).getFlag() == 0){
//            viewHolder.mCb.setChecked(false);
//        }else {
//            viewHolder.mCb.setChecked(true);
//        }
//
//        viewHolder.mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    expenseReimbursement.getData().getList().get(position).setFlag(1);
//                    idStr.add(expenseReimbursement.getData().getList().get(position).getExpenseid()+"");
//                }else {
//                    expenseReimbursement.getData().getList().get(position).setFlag(0);
//                    idStr.remove(expenseReimbursement.getData().getList().get(position).getExpenseid()+"");
//                }
//
//
//                for (String s : idStr){
//                    Log.e("ssss",s);
//                }
//
//
//            }
//        });

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvExplain.setText("报销类型:"+expenseReimbursement.getData().getList().get(position).getExplain());
        viewHolder.tvStatus.setText(expenseReimbursement.getData().getList().get(position).getExamineinfo().getName());
        viewHolder.tvStatus.setTextColor(Color.parseColor(expenseReimbursement.getData().getList().get(position).getExamineinfo().getColor()));
//        if (expenseReimbursement.getData().getList().get(position).getExamine()==0){
//            viewHolder.alCb.setVisibility(View.GONE);
//            viewHolder.tvStatus.setText("审批中");
//            viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
//        }else if(expenseReimbursement.getData().getList().get(position).getExamine()==1 && expenseReimbursement.getData().getList().get(position).getPay()==1){
//            viewHolder.tvStatus.setText("已付款");
//            viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextTheme));
//        }else if(expenseReimbursement.getData().getList().get(position).getExamine()==1 && expenseReimbursement.getData().getList().get(position).getPay()==0){
//            viewHolder.tvStatus.setText("待付款");
//            viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
//        }else if(expenseReimbursement.getData().getList().get(position).getExamine()==3 /*&& expenseReimbursement.getData().getList().get(position).getPay()==0*/){
//            viewHolder.tvStatus.setText("审批驳回");
//            viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
//        }else{
//            viewHolder.alCb.setVisibility(View.GONE);
//            viewHolder.tvStatus.setText("审批不通过");
//            viewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorTextRed));
//        }
        Glide.with(context).load(expenseReimbursement.getData().getList().get(position).getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imagePic);
        viewHolder.tvTime.setText(expenseReimbursement.getData().getList().get(position).getCreatetime());
        viewHolder.tvAllMoney.setText("总报销金额:"+expenseReimbursement.getData().getList().get(position).getMoney()+"元");
        viewHolder.tvType.setText(expenseReimbursement.getData().getList().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return expenseReimbursement.getData().getList().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_pic)
        ImageView imagePic;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_explain)
        TextView tvExplain;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_all_money)
        TextView tvAllMoney;
        @BindView(R.id.al_select_)
        RelativeLayout alCb;
        @BindView(R.id.cb_select)
        CheckBox mCb;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}