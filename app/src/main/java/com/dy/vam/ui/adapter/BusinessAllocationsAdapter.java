package com.dy.vam.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.MarketGetCustomList;
import com.dy.vam.entity.Verif;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.BaseActivity;
import com.dy.vam.ui.activity.MarkeEditCustomActivity;
import com.dy.vam.ui.widget.CustomDialog;
import com.dy.vam.ui.widget.LoadDialog;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessAllocationsAdapter extends RecyclerView.Adapter {

    private BaseActivity context;
    private MarketGetCustomList marketGetCustomList;
    private CustomDialog dialog;

    public BusinessAllocationsAdapter(BaseActivity context) {
        this.context = context;
    }

    public void setDatas(MarketGetCustomList marketGetCustomList) {
        this.marketGetCustomList = marketGetCustomList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_allocations, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int positionP) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemLongClickListener != null) {
            viewHolder.layoutItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(v, position);
                    return false;
                }
            });
        }
        viewHolder.lvMoney.setClickable(false);
        viewHolder.lvMoney.setPressed(false);
        viewHolder.lvMoney.setEnabled(false);
        viewHolder.lvMoney.setFocusable(false);

        viewHolder.lvStaff.setClickable(false);
        viewHolder.lvStaff.setPressed(false);
        viewHolder.lvStaff.setEnabled(false);
        viewHolder.lvStaff.setFocusable(false);

        viewHolder.lvProportions.setClickable(false);
        viewHolder.lvProportions.setPressed(false);
        viewHolder.lvProportions.setEnabled(false);
        viewHolder.lvProportions.setFocusable(false);

        viewHolder.tvCustomerName.setText(marketGetCustomList.getData().getList().get(positionP).getName());
        viewHolder.tvCustomerTurnover.setText(marketGetCustomList.getData().getList().get(positionP).getTurnover() + "");
        viewHolder.tvGrossProfit.setText(marketGetCustomList.getData().getList().get(positionP).getGrossprofit() + "");
        viewHolder.tvCommitTime.setText("提交时间 ：" + marketGetCustomList.getData().getList().get(positionP).getCreatetime() + "");

        BusinessAllocationChildAdapter proportionsAdapter = new BusinessAllocationChildAdapter(context, Constant.PROPORTIONS, positionP);
        proportionsAdapter.setDatas(marketGetCustomList,mOnItemLongClickListener);
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.lvProportions, false, proportionsAdapter);

        BusinessAllocationChildAdapter staffAdapter = new BusinessAllocationChildAdapter(context, Constant.STAFF, positionP);
        staffAdapter.setDatas(marketGetCustomList,mOnItemLongClickListener);
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.lvStaff, false, staffAdapter);

        BusinessAllocationChildAdapter moneyAdapter = new BusinessAllocationChildAdapter(context, Constant.SUMOFMONEY, positionP);
        moneyAdapter.setDatas(marketGetCustomList,mOnItemLongClickListener);
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.lvMoney, false, moneyAdapter);

        viewHolder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarkeEditCustomActivity.actionStart(context, "编辑客户", marketGetCustomList.getData().getList().get(positionP).getCustomid());
            }
        });
        viewHolder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new CustomDialog(context, R.style.MyDialog, new CustomDialog.DialogClickListener() {
                    @Override
                    public void sure() {
                        LoadDialog.show(context);
                        context.createRequest(BaseUrl.getInstence().getMarketDeleteCustomUrl()).marketDeleteCustom(PreferenceUtils.getInstance().getUserToken(), marketGetCustomList.getData().getList().get(positionP).getCustomid()).enqueue(new Callback<Verif>() {
                            @Override
                            public void onResponse(Call<Verif> call, Response<Verif> response) {
                                if (
                                        dialog != null) {
                                    dialog.dismiss();
                                }
                                Log.e("删除客户:", new Gson().toJson(response.body()));
                                LoadDialog.dismiss(context);
                                switch (response.body().getCode()) {
                                    case Constant.SUCCESS:
                                        Toasty.info(context, "客户已删除").show();
                                        marketGetCustomList.getData().getList().remove(positionP);
                                        notifyDataSetChanged();
                                        break;
                                    default:
                                        Toasty.error(context, response.body().getMsg()).show();
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<Verif> call, Throwable t) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                                LoadDialog.dismiss(context);
                                Toasty.warning(context, context.getString(R.string.net_error)).show();
                                Log.e("删除员工失败：", t.toString());
                            }
                        });
                    }

                });
                if (dialog != null) {
                    dialog.show();
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return marketGetCustomList.getData().getList().size();
    }


    private OnItemLongClickListener mOnItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_item)
        RelativeLayout layoutItem;
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
        @BindView(R.id.image_edit)
        ImageView imageEdit;
        @BindView(R.id.image_delete)
        ImageView imageDelete;
        @BindView(R.id.tv_commit_time)
        TextView tvCommitTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}