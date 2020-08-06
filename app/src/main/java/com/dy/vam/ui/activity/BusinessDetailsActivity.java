package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.FinalcinalCustomDetails;
import com.dy.vam.entity.Verif;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.CommissionerAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/17.
 * 业务详情、编辑
 */

public class BusinessDetailsActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "id";
    @BindView(R.id.rv_commissioner)
    RecyclerView rvCommissioner;
    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @BindView(R.id.et_turnover)
    EditText etTurnover;
    @BindView(R.id.et_gross_profit)
    EditText etGrossProfit;
    @BindView(R.id.tv_commiter_name)
    TextView tvCommiterName;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    private CommissionerAdapter adapter;


    public static void actionStart(Context context, int id) {
        Intent intent = new Intent(context, BusinessDetailsActivity.class);
        intent.putExtra(DETAILS_VALUES, id);
        context.startActivity(intent);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("新增业务");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_new_business;
    }

    @Override
    protected void initViews() {
        adapter = new CommissionerAdapter(this);

        adapter.setOnItemClickLitener(new CommissionerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                BusinessDetailsActivity.actionStart(BusinessDetailsActivity.this, 1);
            }
        });


    }

    @Override
    protected void initDatas() {
        loadData();
    }

    private void loadData() {
        LoadDialog.show(BusinessDetailsActivity.this);
        createRequest(BaseUrl.getInstence().getCustomDetailsUrl()).financialGetCustomDetails(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DETAILS_VALUES, 1)).enqueue(new Callback<FinalcinalCustomDetails>() {
            @Override
            public void onResponse(Call<FinalcinalCustomDetails> call, Response<FinalcinalCustomDetails> response) {
                LoadDialog.dismiss(BusinessDetailsActivity.this);
                FinalcinalCustomDetails customDetails = response.body();
                switch (customDetails.getCode()) {
                    case Constant.SUCCESS:
                        if (customDetails.getData() != null) {
                            etGrossProfit.setText(customDetails.getData().getGrossprofit() + "");
                            etTurnover.setText(customDetails.getData().getTurnover() + "");
                            tvCustomerName.setText(customDetails.getData().getName() + "");
                            tvCommiterName.setText("提交人:" + "  " + customDetails.getData().getPost() + "   " + customDetails.getData().getUsername() + "");
                            tvCreateTime.setText(customDetails.getData().getCreatetime());
                            if (customDetails.getData().getUsers() != null) {
                                adapter.setDatas(customDetails);
                                RecyclerViewHelper.initRecyclerViewV(BusinessDetailsActivity.this, rvCommissioner, false, adapter);
                            }
                        }
                        break;
                    default:
                        Toasty.error(BusinessDetailsActivity.this, customDetails.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<FinalcinalCustomDetails> call, Throwable t) {
                LoadDialog.dismiss(BusinessDetailsActivity.this);
                Toasty.error(BusinessDetailsActivity.this, getString(R.string.net_error) + ":获取业务提成客户详情失败！").show();
            }
        });
    }


    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        String grossProfit = etGrossProfit.getText().toString();
        String turnover = etTurnover.getText().toString();
        if (TextUtils.isEmpty(grossProfit)) {
            Toasty.warning(BusinessDetailsActivity.this, "请录入毛利率").show();
            return;
        }
        if (TextUtils.isEmpty(turnover)) {
            Toasty.warning(BusinessDetailsActivity.this, "请录入客户营业额").show();
            return;
        }

        commit(grossProfit, turnover);
    }

    private void commit(String grossProfit, String turnover) {
        btnCommit.setEnabled(false);
        LoadDialog.show(BusinessDetailsActivity.this);
        createRequest(BaseUrl.getInstence().getEditCustomUrl()).financialEditCustomDetails(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DETAILS_VALUES, 1), turnover, grossProfit).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(BusinessDetailsActivity.this);
                btnCommit.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(BusinessDetailsActivity.this, "更新客户营业额和毛利率成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(BusinessDetailsActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(BusinessDetailsActivity.this);
                Toasty.warning(BusinessDetailsActivity.this, getString(R.string.net_error)).show();
                Log.e("更新客户营业额和毛利率：", t.toString());
            }
        });
    }

}
