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
import android.widget.LinearLayout;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.MarketGetCustom;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.MarketEditUsersAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/7.
 * 编辑客户 营销操作
 */

public class MarkeEditCustomActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String DETAILS_ID = "customid";
    @BindView(R.id.et_custom_name)
    EditText etCustomName;
    @BindView(R.id.et_turnover)
    EditText etTurnover;
    @BindView(R.id.et_grossprofit)
    EditText etGrossprofit;
    @BindView(R.id.layout_add_staff)
    LinearLayout layoutAddStaff;
    @BindView(R.id.rv_staff_list)
    RecyclerView rvStaffList;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.view_line)
    View viewLine;

    private List<SortModelInfo> mUsers = new ArrayList<>();
    private MarketEditUsersAdapter adapter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title, int customid) {
        Intent intent = new Intent(context, MarkeEditCustomActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(DETAILS_ID, customid);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_market_add_custom;
    }

    @Override
    protected void initViews() {
        adapter = new MarketEditUsersAdapter(this,viewLine);
    }

    @Override
    protected void initDatas() {

        createRequest(BaseUrl.getInstence().getMarketGetCustomUrl()).marketGetCustomInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DETAILS_ID, 1)).enqueue(new Callback<MarketGetCustom>() {
            @Override
            public void onResponse(Call<MarketGetCustom> call, Response<MarketGetCustom> response) {
                MarketGetCustom marketGetCustom = response.body();
                switch (marketGetCustom.getCode()) {
                    case Constant.SUCCESS:
                        if (marketGetCustom.getData() != null) {
                            etCustomName.setText(marketGetCustom.getData().getName());
                            etGrossprofit.setText(marketGetCustom.getData().getGrossprofit() + "");
                            etTurnover.setText(marketGetCustom.getData().getTurnover() + "");
                        }
                        if (marketGetCustom.getData().getUsers() != null) {
                            for (int i = 0; i < marketGetCustom.getData().getUsers().size(); i++) {
                                SortModelInfo sortModelInfo = new SortModelInfo();
                                sortModelInfo.setId(marketGetCustom.getData().getUsers().get(i).getUserid());
                                sortModelInfo.setPercent(marketGetCustom.getData().getUsers().get(i).getProportion());
                                sortModelInfo.setName(marketGetCustom.getData().getUsers().get(i).getUsername());
                                mUsers.add(sortModelInfo);
                            }
                            adapter.setDatas(mUsers);
                            RecyclerViewHelper.initRecyclerViewV(MarkeEditCustomActivity.this, rvStaffList, false, adapter);
                        } else {
                            Toasty.error(MarkeEditCustomActivity.this, "暂无对该客户负责员工").show();
                        }
                        break;
                    default:
                        Toasty.error(MarkeEditCustomActivity.this, response.body().getMsg()).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<MarketGetCustom> call, Throwable t) {
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(MarkeEditCustomActivity.this);
                Toasty.warning(MarkeEditCustomActivity.this, getString(R.string.net_error) + ":获取客户信息失败!").show();
                Log.e("获取客户信息失败：", t.toString());
            }
        });

    }


    @OnClick({R.id.layout_add_staff, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_add_staff:
                SelectStaffActivity.actionStartForResult(MarkeEditCustomActivity.this, "选择员工");
                break;
            case R.id.btn_commit:
                String name = etCustomName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toasty.error(MarkeEditCustomActivity.this, "请填写客户名称").show();
                    return;
                }
                if (mUsers.size() == 0) {
                    Toasty.error(MarkeEditCustomActivity.this, "请选择负责该客户的员工").show();
                    return;
                }
                for (int i = 0; i < mUsers.size(); i++) {
                    if (TextUtils.isEmpty(mUsers.get(i).getPercent())) {
                        Toasty.error(MarkeEditCustomActivity.this, "请为员工“" + mUsers.get(i).getName() + "”设置提成比例").show();
                        return;
                    }
                }
                Map<Integer, String> userMap = new HashMap<>();
                for (int i = 0; i < mUsers.size(); i++) {
                    userMap.put(mUsers.get(i).getId(), mUsers.get(i).getPercent());
                }
                commit(name, userMap);
                break;
        }
    }

    private void commit(String name, Map<Integer, String> userMap) {
        LoadDialog.show(MarkeEditCustomActivity.this);
        btnCommit.setEnabled(false);
        createRequest(BaseUrl.getInstence().getMarketEditCustomUrl()).marketEditCustomInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DETAILS_ID, 1), name, new Gson().toJson(userMap)).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(MarkeEditCustomActivity.this);
                btnCommit.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(MarkeEditCustomActivity.this, "客户信息更新成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(MarkeEditCustomActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(MarkeEditCustomActivity.this);
                Toasty.warning(MarkeEditCustomActivity.this, getString(R.string.net_error)).show();
                Log.e("客户信息更新失败：", t.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.RESULT_CODE) {
            if (requestCode == Constant.REQUEST_CODE) {        //假如两次选择了同一个人  还需要从List中移除
                List<SortModelInfo> users = (List<SortModelInfo>) data.getSerializableExtra("users");
                for (int i = 0; i < mUsers.size(); i++) {
                    for (int j = 0; j < users.size(); j++) {
                        if (mUsers.get(i).getId() == users.get(j).getId()) {
                            users.remove(j);
                        }
                    }
                }
                mUsers.addAll(users);
                Log.e("本次选择了哪些员工", users.toString());

                adapter.setDatas(mUsers);
                RecyclerViewHelper.initRecyclerViewV(this, rvStaffList, false, adapter);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
