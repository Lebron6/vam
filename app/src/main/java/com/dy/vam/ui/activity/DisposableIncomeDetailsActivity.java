package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.SummaryGetDataChart;
import com.dy.vam.tools.MyXFormatter;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.CustomtersTIDetailsRecyAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/11.
 * 可支配收入四种类型 详情
 */

public class DisposableIncomeDetailsActivity extends BaseActivity {
    @BindView(R.id.rv_customers_total_income_details)
    RecyclerView rvCustomersTotalIncomeDetails;

    public static final String TITLE = "title";
    public static final String DEPARTMENT_ID = "departmentId";
    public static final String YEARS = "years";
    public static final String POSITION = "position";
    @BindView(R.id.lc_customers_total_income_details)
    LineChart lcCustomersTotalIncomeDetails;
    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.tv_time_banner)
    TextView tvTimeBanner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ArrayAdapter arrayAdapter;
    private int[] years;
    private SummaryGetDataChart summaryGetDataChart;
    private Call<SummaryGetDataChart> call;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
                .statusBarColor(R.color.colorTheme).init();
    }

    public static void actionStart(Context context, String title, int[] years, int termid, int position) {
        Intent intent = new Intent(context, DisposableIncomeDetailsActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(YEARS, years);
        intent.putExtra(POSITION, position);
        intent.putExtra(DEPARTMENT_ID, termid);
        context.startActivity(intent);

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_customers_total_income_details;
    }

    @Override
    protected void initViews() {
        years = getIntent().getIntArrayExtra(YEARS);
        tvTimeBanner.setText(PreferenceUtils.getInstance().getDefaultYear() + "");
        List<String> yearList = new ArrayList<>();
        for (int i = 0; i < years.length; i++) {
            yearList.add(years[i] + "");
        }
        arrayAdapter = new ArrayAdapter(DisposableIncomeDetailsActivity.this, R.layout.item_question, R.id.tv_popqusetion, yearList);
        loadDatas();
    }

    public void loadDatas() {
        switch (getIntent().getIntExtra(POSITION, 0)) {
            case 0:
                call = createRequest(BaseUrl.getInstence().getSummaryGetDepartmentCustomincomeUrl()).getDepartmentCustomIncome(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(DEPARTMENT_ID, 1));
                break;
            case 1:
                call = createRequest(BaseUrl.getInstence().getSummaryGetDepartmentManageincomeUrl()).getDepartmentCustomIncome(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(DEPARTMENT_ID, 1));
                break;
            case 2:     //服务收入 后续修改
                call = createRequest(BaseUrl.getInstence().getSummaryGetDepartmentManageincomeUrl()).getDepartmentCustomIncome(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(DEPARTMENT_ID, 1));
                break;
            case 3:     //业务收入 后续修改
                call = createRequest(BaseUrl.getInstence().getSummaryGetDepartmentManageincomeUrl()).getDepartmentCustomIncome(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(DEPARTMENT_ID, 1));
                break;
        }
        call.enqueue(new Callback<SummaryGetDataChart>() {
            @Override
            public void onResponse(Call<SummaryGetDataChart> call, Response<SummaryGetDataChart> response) {
                summaryGetDataChart = response.body();
                Log.e("获取部门客户收入图表数据：", new Gson().toJson(summaryGetDataChart));
                if (summaryGetDataChart.getCode() == Constant.SUCCESS) {
                    if (summaryGetDataChart.getData() != null) {
                        initLineChart(summaryGetDataChart.getData());
                        initRecylerData(summaryGetDataChart.getData());
                    } else {
                        Toasty.error(DisposableIncomeDetailsActivity.this, "获取部门客户收入图表数据失败!").show();
                    }
                } else {
                    Toasty.error(DisposableIncomeDetailsActivity.this, summaryGetDataChart.getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<SummaryGetDataChart> call, Throwable t) {
                Toasty.error(DisposableIncomeDetailsActivity.this, getString(R.string.net_error) + ":获取部门客户收入图表数据失败").show();
                Log.e("获取部门客户收入图表数据:", t.toString());
            }
        });
    }

    private void initRecylerData(SummaryGetDataChart.DataBean data) {
        CustomtersTIDetailsRecyAdapter adapter = new CustomtersTIDetailsRecyAdapter(this);
        adapter.setDatas(data);
        RecyclerViewHelper.initRecyclerViewV(this, rvCustomersTotalIncomeDetails, false, adapter);
    }

    private void initLineChart(SummaryGetDataChart.DataBean data) {

        lcCustomersTotalIncomeDetails.setTouchEnabled(true); // 设置是否可以触摸
        lcCustomersTotalIncomeDetails.setDragEnabled(true);// 是否可以拖拽
        lcCustomersTotalIncomeDetails.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
        lcCustomersTotalIncomeDetails.getAxisLeft().setEnabled(false); // 隐藏左边 的坐标轴
        lcCustomersTotalIncomeDetails.setScaleEnabled(true);// 是否可以缩放
        lcCustomersTotalIncomeDetails.getDescription().setEnabled(false);
        lcCustomersTotalIncomeDetails.setDrawGridBackground(false);
        lcCustomersTotalIncomeDetails.setBackgroundColor(getResources().getColor(R.color.colorTheme));//背景色
        lcCustomersTotalIncomeDetails.getLegend().setEnabled(false);        //是否显示Legend（下方标示）

        XAxis xAxis = lcCustomersTotalIncomeDetails.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.colorTextTheme)); // 设置点击某个点时，横竖两条线的颜色);  //设置x轴颜色
        //自定义x轴显示
        MyXFormatter formatter = new MyXFormatter(values);
        //显示个数
        xAxis.setLabelCount(5);
        xAxis.setTextColor(getResources().getColor(R.color.white));
        xAxis.setValueFormatter(formatter);


        // set data
        lcCustomersTotalIncomeDetails.setData(generateDataLine(data));
        lcCustomersTotalIncomeDetails.animateX(750);
        lcCustomersTotalIncomeDetails.setVisibleXRangeMaximum(5);

    }

    protected String[] values = new String[]{
            "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
    };

    /**
     * generates a random ChartData object with just one DataSet
     * 设置数据源
     *
     * @param data
     * @return
     */
    private LineData generateDataLine(SummaryGetDataChart.DataBean data) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        Log.e("DataBeanJson:", new Gson().toJson(data));
        JsonObject asJsonObject = new JsonParser().parse(new Gson().toJson(data)).getAsJsonObject();
        Double[] doubles = new Double[12];
        for (int i = 1; i < 13; i++) {
            e1.add(new Entry(i - 1, asJsonObject.get(String.valueOf(i)).getAsFloat()));
            doubles[i - 1] = asJsonObject.get(String.valueOf(i)).getAsDouble();
        }
        YAxis leftAxis = lcCustomersTotalIncomeDetails.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        Double[] doubles1 = Utils.bubbleSort(doubles);
        leftAxis.setAxisMinimum(doubles1[0].floatValue()); // this replaces setStartAtZero(true)

        YAxis rightAxis = lcCustomersTotalIncomeDetails.getAxisRight();
        rightAxis.setLabelCount(5, false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(doubles1[0].floatValue()); // this replaces setStartAtZero(true)

        LineDataSet d1 = new LineDataSet(e1, "x(月份)");
        d1.setLineWidth(Utils.px2dip(this, getResources().getDimension(R.dimen.x4)));
        d1.setCircleRadius(Utils.px2dip(this, getResources().getDimension(R.dimen.x12)));
        d1.setCircleHoleRadius(Utils.px2dip(this, getResources().getDimension(R.dimen.x8)));
        d1.setHighLightColor(getResources().getColor(R.color.colorTextTheme)); // 设置点击某个点时，横竖两条线的颜色
        d1.setCircleColorHole(getResources().getColor(R.color.colorTheme));     //内圆颜色
        d1.setColor(getResources().getColor(R.color.colorTextTheme));         //线的颜色
        d1.setCircleColor(getResources().getColor(R.color.colorTextTheme));   //圆圈的颜色
        d1.setDrawValues(true);
        d1.setValueTextColor(Color.rgb(255, 255, 255));
        d1.setValueTextSize(Utils.px2dip(this, getResources().getDimension(R.dimen.x28)));

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(sets);
        return cd;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText(getIntent().getStringExtra(TITLE));
    }

    @Override
    protected void initDatas() {


    }

    @OnClick(R.id.layout_show_time_window)
    public void onViewClicked() {

    }

//    OnTimeSelectedListener listener = new OnTimeSelectedListener() {
//        @Override
//        public void select(int postion) {
//            tvTimeBanner.setText(years[postion] + "");
//            loadDatas(years[postion]);
//        }
//    };

    @OnClick({R.id.layout_back, R.id.layout_show_time_window})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_show_time_window:
//                TimeSelectWindow timeSelectWindow = new TimeSelectWindow(DisposableIncomeDetailsActivity.this);
//                timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
