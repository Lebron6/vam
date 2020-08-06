package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.GetDepartmentDisposable;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.DepartmentalDisposableIncomeAdapter;
import com.dy.vam.ui.widget.TitleBarManger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.tools.Utils.stringToKeep2Point;


/**
 * Created by James on 2018/1/29.
 * 部门可支配收入
 */

public class DepartmentalDisposableIncomeActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String DEPARTMENT_ID = "departmentId";
    @BindView(R.id.rv_departmental_disposable_income)
    RecyclerView rvDepartmentalDisposableIncome;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_percent)
    TextView tvPercent;
    private ArrayAdapter adapter;
    private DepartmentalDisposableIncomeAdapter adapter1;
    private List<Integer> times;
    private ArrayAdapter arrayAdapter;
    private GetDepartmentDisposable departmentDisposable;
    private TitleBarManger manger;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title, int departmentId) {
        Intent intent = new Intent(context, DepartmentalDisposableIncomeActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(DEPARTMENT_ID, departmentId);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_departments_disposable_income;
    }

    @Override
    protected void initViews() {
        adapter1 = new DepartmentalDisposableIncomeAdapter(this);
        loadDatasByTime();
    }

    private void loadDatasByTime() {
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentDisposableUrl()).getSummaryDepartmentDisposableData(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(DEPARTMENT_ID, 0)).enqueue(new Callback<GetDepartmentDisposable>() {
            @Override
            public void onResponse(Call<GetDepartmentDisposable> call, Response<GetDepartmentDisposable> response) {
                departmentDisposable = response.body();
                if (departmentDisposable.getCode() == Constant.SUCCESS) {
                    if (departmentDisposable.getData() != null && departmentDisposable.getData().getList() != null) {
                        tvAllMoney.setText(departmentDisposable.getData().getTotal() + "");
                        tvPercent.setText("分配比例："+stringToKeep2Point(departmentDisposable.getData().getProportion()+"")+"%");
//                        manger.setQuestion(departmentDisposable.getData().getHelp_url());
                        adapter1.setDatas(departmentDisposable);
                        RecyclerViewHelper.initRecyclerViewV(DepartmentalDisposableIncomeActivity.this, rvDepartmentalDisposableIncome, false, adapter1);
                    } else {
                        Toasty.error(DepartmentalDisposableIncomeActivity.this, "获取部门可支配详情失败").show();
                    }
//                    if (departmentDisposable.getData().getYears() != null && departmentDisposable.getData().getYears().size() > 0) {
//                        times = new ArrayList<>();
//                        for (int i = 0; i < departmentDisposable.getData().getYears().size(); i++) {
//                            int initTime = departmentDisposable.getData().getYears().get(i).getYear();
//                            if (departmentDisposable.getData().getYears().get(i).getSelected() == 1) {
////                                tvTime.setText(initTime + "");
//                            }
//                            times.add(initTime);
//                        }
//                        arrayAdapter = new ArrayAdapter(DepartmentalDisposableIncomeActivity.this, R.layout.item_question, R.id.tv_popqusetion, times);
//                    }
                } else {
                    Toasty.error(DepartmentalDisposableIncomeActivity.this, departmentDisposable.getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<GetDepartmentDisposable> call, Throwable t) {
                Toasty.error(DepartmentalDisposableIncomeActivity.this, getString(R.string.net_error) + ":获取部门可支配详情异常!").show();
            }
        });
    }

    @Override
    protected void initDatas() {
//        adapter1.setOnItemClickLitener(new DepartmentalDisposableIncomeAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                int years[] = new int[times.size()];
//                for (int i = 0; i < times.size(); i++) {
//                    years[i] = times.get(i);
//                }
//                if (position == 0) {
//                    DisposableIncomeDetailsActivity.actionStart(DepartmentalDisposableIncomeActivity.this, "客户收入明细", years, getIntent().getIntExtra(DEPARTMENT_ID, 0), position);
//                }
//                if (position == 1) {
//                    DisposableIncomeDetailsActivity.actionStart(DepartmentalDisposableIncomeActivity.this, "管理收入明细", years, getIntent().getIntExtra(DEPARTMENT_ID, 0), position);
//                }
//            }
//        });
    }


//    OnTimeSelectedListener listener = new OnTimeSelectedListener() {
//        @Override
//        public void select(int postion) {
//            year = departmentDisposable.getData().getYears().get(postion).getYear();
//            tvTime.setText(times.get(postion) + "");
//            loadDatasByTime(year);
//        }
//    };


//    @OnClick({R.id.layout_show_time_window})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.layout_show_time_window:
//                TimeSelectWindow timeSelectWindow = new TimeSelectWindow(DepartmentalDisposableIncomeActivity.this);
//                timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
//                break;
//        }
//    }

}
