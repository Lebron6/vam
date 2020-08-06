package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.FinalcinalAmount;
import com.dy.vam.entity.Verif;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.FinancialInputIncomAdapter;
import com.dy.vam.ui.adapter.FinancialInputIncomAdapter3;
import com.dy.vam.ui.adapter.FinancialInputOutChildAdapter;
import com.dy.vam.ui.adapter.FinancialInputOutputAdapter;
import com.dy.vam.ui.widget.ButtomSelectWindow;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_FINANCIAL;
import static com.dy.vam.config.Constant.SUCCESS;


/**
 * 财务录入
 */

public class FinancialInputActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String TYPE = "type";
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.rv_financial_income)
    RecyclerView rvFinancialIncome;
    @BindView(R.id.rv_financial_output)
    RecyclerView rvFinancialOutput;
    @BindView(R.id.rv_financial_bumen)
    RecyclerView rvFinancialBumen;
    @BindView(R.id.sv_to_top)
    NestedScrollView svToTop;
    @BindView(R.id.layout_commit)
    LinearLayout layoutCommit;
    @BindView(R.id.view_line)
    View viewLine;
    private ArrayAdapter arrayAdapter;
    private List<String> times;
    private List<FinalcinalAmount.DataBean.ListBean> inComeList;
    private List<FinalcinalAmount.DataBean.ListBean> unComeList;
    private List<FinalcinalAmount.DataBean.List2Bean> bumenList;
    private FinalcinalAmount finalcinalAmount;
    private int year;
    private int month;
    private String type;//0可编辑 1不可编辑

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar).statusBarColor(R.color.colorTheme)
                .init();
    }

    public static void actionStart(Context context, String title, String type) {
        Intent intent = new Intent(context, FinancialInputActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(TYPE, type);
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
        return R.layout.activity_financial_input;
    }

    @Override
    protected void initViews() {
        type = getIntent().getStringExtra(TYPE);
        if (type.equals(VIEW_FINANCIAL)) {
            layoutCommit.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initDatas() { //初始化数据为当前月份财务录入信息
        final Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH) + 1;
        getDataByTime(year, month);
    }

    private void getDataByTime(int year, int month) {
        LoadDialog.show(FinancialInputActivity.this);
        inComeList = new ArrayList<>();
        unComeList = new ArrayList<>();
        bumenList = new ArrayList<>();
        createRequest(BaseUrl.getInstence().getFinancialGetAmountUrl()).getFinalcinalAmount(PreferenceUtils.getInstance().getUserToken(), year, month).enqueue(callback);
    }

    Callback<FinalcinalAmount> callback = new Callback<FinalcinalAmount>() {

        @Override
        public void onResponse(Call<FinalcinalAmount> call, Response<FinalcinalAmount> response) {
            LoadDialog.dismiss(FinancialInputActivity.this);
            finalcinalAmount = response.body();

            switch (finalcinalAmount.getCode()) {
                case SUCCESS:
                    if (finalcinalAmount.getData() != null) {
                        if (finalcinalAmount.getData().getTimes() != null) {
                            times = new ArrayList<>();
                            for (int i = 0; i < finalcinalAmount.getData().getTimes().size(); i++) {
                                String initTime = finalcinalAmount.getData().getTimes().get(i).getYear() + "年" + finalcinalAmount.getData().getTimes().get(i).getMonth() + "月";
                                if (finalcinalAmount.getData().getTimes().get(i).getSelected() == 1) {
                                    tvTime.setText(initTime);
                                }
                                times.add(initTime);
                            }
                            arrayAdapter = new ArrayAdapter(FinancialInputActivity.this, R.layout.item_question, R.id.tv_popqusetion, times);
                        }

                        if (finalcinalAmount.getData().getList() != null) {
                            bumenList.addAll(finalcinalAmount.getData().getList2());
                            for (FinalcinalAmount.DataBean.ListBean listBean : finalcinalAmount.getData().getList()) {
                                if (listBean.getIsincome() == 0) {
                                    unComeList.add(listBean);
                                } else {
                                    inComeList.add(listBean);
                                }
                            }

                            FinancialInputIncomAdapter financialInputOutChildAdapter = new FinancialInputIncomAdapter(FinancialInputActivity.this, type);
                            financialInputOutChildAdapter.setDatas(inComeList);
                            RecyclerViewHelper.initRecyclerViewV(FinancialInputActivity.this, rvFinancialIncome, false, financialInputOutChildAdapter);
//                            ViewGroup.LayoutParams layoutParams = rvFinancialOutput.getLayoutParams();
//                            layoutParams.height = getRecyHeight();
//                            rvFinancialBumen.setLayoutParams(layoutParams);
                            FinancialInputIncomAdapter3 financialInputOutChildAdapter3 = new FinancialInputIncomAdapter3(FinancialInputActivity.this, type);
                            financialInputOutChildAdapter3.setDatas(bumenList);
                            RecyclerViewHelper.initRecyclerViewV(FinancialInputActivity.this, rvFinancialBumen, false, financialInputOutChildAdapter3);


//                            layoutParams.height = ((finalcinalAmount.getData().getList().getOutput().getPublicX().size()) * (int) getResources().getDimension(R.dimen.x178))
//                                    + ((finalcinalAmount.getData().getList().getOutput().getSalary().size()) * (int) getResources().getDimension(R.dimen.x178)) +
//                                    ((finalcinalAmount.getData().getList().getOutput().getOther().size()) * (int) getResources().getDimension(R.dimen.x178))
//                                    + (3 * (int) getResources().getDimension(R.dimen.x83)); //这里的总高度是根据Adapter 中的RecyclerView 高度+(3)(textView+magin)

                            FinancialInputOutChildAdapter financialInputOutChildAdapter2 = new FinancialInputOutChildAdapter(FinancialInputActivity.this, type);
                            financialInputOutChildAdapter2.setDatas(unComeList);
                            RecyclerViewHelper.initRecyclerViewV(FinancialInputActivity.this, rvFinancialOutput, false, financialInputOutChildAdapter2);

                        }
//                        if (finalcinalAmount.getData().getList().getOutput() != null) {
//                            ViewGroup.LayoutParams layoutParams = rvFinancialOutput.getLayoutParams();
//                            layoutParams.height = getRecyHeight();
////                            layoutParams.height = ((finalcinalAmount.getData().getList().getOutput().getPublicX().size()) * (int) getResources().getDimension(R.dimen.x178))
////                                    + ((finalcinalAmount.getData().getList().getOutput().getSalary().size()) * (int) getResources().getDimension(R.dimen.x178)) +
////                                    ((finalcinalAmount.getData().getList().getOutput().getOther().size()) * (int) getResources().getDimension(R.dimen.x178))
////                                    + (3 * (int) getResources().getDimension(R.dimen.x83)); //这里的总高度是根据Adapter 中的RecyclerView 高度+(3)(textView+magin)
//                            rvFinancialOutput.setLayoutParams(layoutParams);
//                            FinancialInputOutputAdapter financialInputOutChildAdapter = new FinancialInputOutputAdapter(FinancialInputActivity.this, type);
//                            financialInputOutChildAdapter.setDatas(finalcinalAmount);
//                            RecyclerViewHelper.initRecyclerViewV(FinancialInputActivity.this, rvFinancialOutput, false, financialInputOutChildAdapter);
//                        }
                    } else {
                        Toasty.error(FinancialInputActivity.this, "获取财务录入信息失败").show();
                    }
                    break;
                default:
                    Toasty.error(FinancialInputActivity.this, finalcinalAmount.getMsg()).show();
                    break;
            }
        }

        @Override
        public void onFailure(Call<FinalcinalAmount> call, Throwable t) {
            LoadDialog.dismiss(FinancialInputActivity.this);
            Toasty.error(FinancialInputActivity.this, getString(R.string.net_error) + ":获取财务录入信息失败！").show();
            Log.e("获取财务录入信息失败", t.toString());
        }
    };

    private int getRecyHeight() {
        int height = 0;
//        if (finalcinalAmount.getData().getList().getOutput().getPublicX() != null) {
//            height = height + (finalcinalAmount.getData().getList().getOutput().getPublicX().size()) * (int) getResources().getDimension(R.dimen.x178) + (int) getResources().getDimension(R.dimen.x83);
//        }
//        if (finalcinalAmount.getData().getList().getOutput().getOther() != null) {
//            height = height + (finalcinalAmount.getData().getList().getOutput().getOther().size()) * (int) getResources().getDimension(R.dimen.x178) + (int) getResources().getDimension(R.dimen.x83);
//        }
//        if (finalcinalAmount.getData().getList().getOutput().getSalary() != null) {
//            height = height + (finalcinalAmount.getData().getList().getOutput().getSalary().size()) * (int) getResources().getDimension(R.dimen.x178) + (int) getResources().getDimension(R.dimen.x83);
        height = height + (finalcinalAmount.getData().getList().size() + 1) * (int) getResources().getDimension(R.dimen.x178) + (int) getResources().getDimension(R.dimen.x83);
//        }

        return height;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.layout_show_time_window, R.id.image_add_income_type, R.id.layout_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_show_time_window:
//                TimeSelectWindow timeSelectWindow = new TimeSelectWindow(FinancialInputActivity.this);
//                timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
                if (arrayAdapter == null) {
                    Toasty.error(FinancialInputActivity.this, getString(R.string.net_error) + ":时间列表为空！").show();
                    return;
                }
                ButtomSelectWindow timeSelectWindow = new ButtomSelectWindow(FinancialInputActivity.this);
                timeSelectWindow.showView(viewLine, arrayAdapter, new OnTimeSelectedListener() {
                    @Override
                    public void select(int postion) {
                        svToTop.scrollTo(0, 0);
                        year = finalcinalAmount.getData().getTimes().get(postion).getYear();
                        month = finalcinalAmount.getData().getTimes().get(postion).getMonth();
                        tvTime.setText(times.get(postion));
                        getDataByTime(year, month);
                    }
                });
                break;
            case R.id.image_add_income_type:
//                CustomDialog dialog = new CustomDialog(FinancialInputActivity.this, R.style.MyDialog);
//                dialog.show();
                break;
            case R.id.layout_commit:
                financinalEditAmount();
                break;
        }
    }

    private void financinalEditAmount() {
        LoadDialog.show(FinancialInputActivity.this);
        Map<String, String> dataMap = new HashMap<>();
        Map<String, String> salaryMap = new HashMap<>();
        for (int i = 0; i < finalcinalAmount.getData().getList().size(); i++) {
            if (!TextUtils.isEmpty(finalcinalAmount.getData().getList().get(i).getMoney())) {
                dataMap.put(String.valueOf(finalcinalAmount.getData().getList().get(i).getDetailid()), String.valueOf(finalcinalAmount.getData().getList().get(i).getMoney()));
            }
        }
        for (int i = 0; i < finalcinalAmount.getData().getList().size(); i++) {
            dataMap.put(String.valueOf(finalcinalAmount.getData().getList().get(i).getDetailid()), String.valueOf(finalcinalAmount.getData().getList().get(i).getMoney()));
        }
        for (int i = 0; i < finalcinalAmount.getData().getList().size(); i++) {
            dataMap.put(String.valueOf(finalcinalAmount.getData().getList().get(i).getDetailid()), String.valueOf(finalcinalAmount.getData().getList().get(i).getMoney()));
        }
        for (int i = 0; i < finalcinalAmount.getData().getList2().size(); i++) {
            salaryMap.put(String.valueOf(finalcinalAmount.getData().getList2().get(i).getDetailid()), String.valueOf(finalcinalAmount.getData().getList2().get(i).getMoney()));
        }
//        for (int i = 0; i < finalcinalAmount.getData().getList().getOutput().getSalary().size(); i++) {
//            salaryMap.put(String.valueOf(finalcinalAmount.getData().getList().getOutput().getSalary().get(i).getDepartmentid()), String.valueOf(finalcinalAmount.getData().getList().getOutput().getSalary().get(i).getMoney()));
//        }
//        JsonObject  mJsonObject = new JsonObject();
//        JsonObject  mJsonObject2 = new JsonObject();
//        mJsonObject.add("details", new Gson().toJsonTree(dataMap));
//        mJsonObject2.add("details2", new Gson().toJsonTree(salaryMap));
        String dataJson = new Gson().toJson(dataMap);
        String salaryJson = new Gson().toJson(salaryMap);
        Log.e("提交数据data", dataJson);
        Log.e("提交数据salary", salaryJson);
        createRequest(BaseUrl.getInstence().getFinancialEditAmountUrl()).finalcinalInput(PreferenceUtils.getInstance().getUserToken(), dataJson, salaryJson).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                Log.e("财务录入", new Gson().toJson(response.body()));
                LoadDialog.dismiss(FinancialInputActivity.this);
                if (response != null && response.body() != null) {
                    switch (response.body().getCode()) {
                        case Constant.SUCCESS:
                            Toasty.info(FinancialInputActivity.this, "财务录入成功").show();
                            finish();
                            break;
                        default:
                            Toasty.error(FinancialInputActivity.this, response.body().getMsg()).show();
                            break;
                    }
                } else {
                    Toasty.warning(FinancialInputActivity.this, getString(R.string.net_error)).show();
                }

            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                LoadDialog.dismiss(FinancialInputActivity.this);
                Toasty.warning(FinancialInputActivity.this, getString(R.string.net_error)).show();
                Log.e("财务录入失败：", t.toString());
            }
        });
    }

}
