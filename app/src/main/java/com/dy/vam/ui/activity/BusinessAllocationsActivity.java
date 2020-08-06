package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.MarketGetCustomList;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.Verif;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.BusinessAllocationsAdapter;
import com.dy.vam.ui.widget.CustomDialog;
import com.dy.vam.ui.widget.CustomerOperationDialog;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/7.
 * 业务提成分配 营销操作
 */

public class BusinessAllocationsActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.rv_allocating_table)
    RecyclerView rvAllocatingTable;
    @BindView(R.id.btn_add_custom)
    ImageButton btnAddCustom;
    private MarketGetCustomList marketGetCustomList;
    private CustomDialog customDialog;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, BusinessAllocationsActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
        loadData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_business_allocations;
    }

    @Override
    protected void initViews() {

    }

    public void loadData() {
        createRequest(BaseUrl.getInstence().getMarketCustomListUrl()).marketGetCustomList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<MarketGetCustomList>() {
            @Override
            public void onResponse(Call<MarketGetCustomList> call, Response<MarketGetCustomList> response) {
                marketGetCustomList = response.body();
                switch (marketGetCustomList.getCode()) {
                    case Constant.SUCCESS:
                        if (marketGetCustomList.getData().getList() != null) {
                            BusinessAllocationsAdapter adapter = new BusinessAllocationsAdapter(BusinessAllocationsActivity.this);
                            adapter.setDatas(marketGetCustomList);
                            RecyclerViewHelper.initRecyclerViewV(BusinessAllocationsActivity.this, rvAllocatingTable, false, adapter);

                            adapter.setOnItemLongClickListener(new BusinessAllocationsAdapter.OnItemLongClickListener() {
                                @Override
                                public void onItemLongClick(View view, int position) {
                                    CustomerOperationDialog dialog = new CustomerOperationDialog(BusinessAllocationsActivity.this, R.style.MyDialog, dialogClickListener, position);
                                    dialog.show();
                                }
                            });
                        }
                        break;
                    default:
                        Toasty.error(BusinessAllocationsActivity.this, marketGetCustomList.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<MarketGetCustomList> call, Throwable t) {
                LoadDialog.dismiss(BusinessAllocationsActivity.this);
                Toasty.error(BusinessAllocationsActivity.this, getString(R.string.net_error) + ":营销获取客户列表失败！").show();
            }
        });
    }

    CustomerOperationDialog.DialogClickListener dialogClickListener = new CustomerOperationDialog.DialogClickListener() {
        @Override
        public void edit(int position) {
            MarkeEditCustomActivity.actionStart(BusinessAllocationsActivity.this, "编辑客户", marketGetCustomList.getData().getList().get(position).getCustomid());
        }

        @Override
        public void delete(int position) {
            deleteCustomer(position);
        }
    };

    private void deleteCustomer(final int position) {
        customDialog = new CustomDialog(BusinessAllocationsActivity.this, R.style.MyDialog, new CustomDialog.DialogClickListener() {
            @Override
            public void sure() {
                LoadDialog.show(BusinessAllocationsActivity.this);
                createRequest(BaseUrl.getInstence().getMarketDeleteCustomUrl()).marketDeleteCustom(PreferenceUtils.getInstance().getUserToken(), marketGetCustomList.getData().getList().get(position).getCustomid()).enqueue(new Callback<Verif>() {
                    @Override
                    public void onResponse(Call<Verif> call, Response<Verif> response) {
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                        Log.e("删除客户:", new Gson().toJson(response.body()));
                        LoadDialog.dismiss(BusinessAllocationsActivity.this);
                        switch (response.body().getCode()) {
                            case Constant.SUCCESS:
                                Toasty.info(BusinessAllocationsActivity.this, "客户已删除").show();
                                loadData();
                                break;
                            default:
                                Toasty.error(BusinessAllocationsActivity.this, response.body().getMsg()).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Verif> call, Throwable t) {
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                        LoadDialog.dismiss(BusinessAllocationsActivity.this);
                        Toasty.warning(BusinessAllocationsActivity.this, BusinessAllocationsActivity.this.getString(R.string.net_error)).show();
                        Log.e("删除员工失败：", t.toString());
                    }
                });
            }
        });
        if (customDialog != null) {
            customDialog.show();
        }
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_add_custom)
    public void onViewClicked() {
        MarketAddCustomActivity.actionStart(BusinessAllocationsActivity.this, "新增业务");
    }
}
