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
import com.dy.vam.ui.widget.LoadDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
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
 * 存续客户总收入明细
 */

public class CustomersTIDetailsActivity extends BaseActivity {
    @BindView(R.id.rv_customers_total_income_details)
    RecyclerView rvCustomersTotalIncomeDetails;

    public static final String TITLE = "title";
    public static final String TERMID = "termid";
    public static final String YEARS = "years";
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
    private LineDataSet lineDataSet;
    private String stringExtra;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
                .statusBarColor(R.color.colorTheme).init();
    }

    public static void actionStart(Context context, String title, int[] years, int termid) {
        Intent intent = new Intent(context, CustomersTIDetailsActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(YEARS, years);

        intent.putExtra(TERMID, termid);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_customers_total_income_details;
    }

    @Override
    protected void initViews() {
        years = getIntent().getIntArrayExtra(YEARS);
        stringExtra = getIntent().getStringExtra(TITLE);
        tvTitle.setText(stringExtra +"明细");
        Log.e("===============>",stringExtra);
        tvTimeBanner.setText(PreferenceUtils.getInstance().getDefaultYear() + "");
        List<String> yearList = new ArrayList<>();
        for (int i = 0; i < years.length; i++) {
            yearList.add(years[i] + "");
        }
        arrayAdapter = new ArrayAdapter(CustomersTIDetailsActivity.this, R.layout.item_question, R.id.tv_popqusetion, yearList);
        loadDatas();
    }

    public void loadDatas() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getSummaryGetDataChartUrl()).getSummaryGetDataChart(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), stringExtra).enqueue(new Callback<SummaryGetDataChart>() {
            @Override
            public void onResponse(Call<SummaryGetDataChart> call, Response<SummaryGetDataChart> response) {
                LoadDialog.dismiss(CustomersTIDetailsActivity.this);
                summaryGetDataChart = response.body();
                if (summaryGetDataChart.getCode() == Constant.SUCCESS) {
                    if (summaryGetDataChart.getData() != null) {
                        initLineChart(summaryGetDataChart.getData());
                        initRecylerData(summaryGetDataChart.getData());
                    } else {
                        Toasty.error(CustomersTIDetailsActivity.this, "获取公司月份图表数据失败!").show();
                    }
                } else {
                    Toasty.error(CustomersTIDetailsActivity.this, summaryGetDataChart.getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<SummaryGetDataChart> call, Throwable t) {
                LoadDialog.dismiss(CustomersTIDetailsActivity.this);
                Toasty.error(CustomersTIDetailsActivity.this, getString(R.string.net_error) + ":获取公司月份图表数据失败").show();
                Log.e("获取公司月份图表数据:", t.toString());
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
        lcCustomersTotalIncomeDetails.getAxisRight().setEnabled(true); // 隐藏右边 的坐标轴
        lcCustomersTotalIncomeDetails.getAxisLeft().setEnabled(true); // 隐藏左边 的坐标轴
        lcCustomersTotalIncomeDetails.setScaleEnabled(true);// 是否可以缩放
        lcCustomersTotalIncomeDetails.getDescription().setEnabled(false);
        lcCustomersTotalIncomeDetails.setDrawGridBackground(false);
        lcCustomersTotalIncomeDetails.setBackgroundColor(getResources().getColor(R.color.colorTheme));//背景色
        lcCustomersTotalIncomeDetails.getLegend().setEnabled(false);        //是否显示Legend（下方标示）

        lcCustomersTotalIncomeDetails.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                lineDataSet.setCircleColorHo();
//                lineDataSet.setCircleColorHole(getResources().getColor(R.color.ThemeTrans));     //内圆颜色
//                lcCustomersTotalIncomeDetails.invalidate();
            }

            @Override
            public void onNothingSelected() {
            }
        });
        XAxis xAxis = lcCustomersTotalIncomeDetails.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(getResources().getColor(R.color.ThemeTrans)); // 设置点击某个点时，横竖两条线的颜色);  //设置x轴颜色
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
        JsonObject asJsonObject = new JsonParser().parse(new Gson().toJson(data)).getAsJsonObject();
        Double[] doubles = new Double[12];
        for (int i = 1; i < 13; i++) {
            e1.add(new Entry(i - 1, asJsonObject.get(String.valueOf(i)).getAsFloat()));
            doubles[i - 1] = asJsonObject.get(String.valueOf(i)).getAsDouble();
        }


        Double[] doubles1 = Utils.bubbleSort(doubles);
        YAxis leftAxis = lcCustomersTotalIncomeDetails.getAxisLeft();
        leftAxis.setAxisMinimum(doubles1[0].floatValue()); // this replaces setStartAtZero(true)
        leftAxis.setLabelCount(5, false);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.TRANSPARENT);
        leftAxis.setAxisLineColor(Color.TRANSPARENT);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setXOffset(1f);

        YAxis rightAxis = lcCustomersTotalIncomeDetails.getAxisRight();
        rightAxis.setAxisMinimum(doubles1[0].floatValue()); // this replaces setStartAtZero(true)
        rightAxis.setLabelCount(5, false);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(Color.TRANSPARENT);
        rightAxis.setAxisLineColor(Color.TRANSPARENT);
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setXOffset(1f);

        lineDataSet = new LineDataSet(e1, "x(月份)");
        lineDataSet.setLineWidth(Utils.px2dip(this, getResources().getDimension(R.dimen.x3)));
        lineDataSet.setCircleRadius(Utils.px2dip(this, getResources().getDimension(R.dimen.x10)));
        lineDataSet.setCircleHoleRadius(Utils.px2dip(this, getResources().getDimension(R.dimen.x6)));
        lineDataSet.setHighLightColor(getResources().getColor(R.color.colorTextTheme)); // 设置点击某个点时，横竖两条线的颜色
        lineDataSet.setCircleColorHole(getResources().getColor(R.color.colorTheme));     //内圆颜色
        lineDataSet.setColor(getResources().getColor(R.color.colorTextTheme));         //线的颜色
        int[] colors = new int[12];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = getResources().getColor(R.color.colorTextTheme);
        }
        lineDataSet.setCircleColors(colors);   //圆圈的颜色
        lineDataSet.setCircleColorHoles();
        lineDataSet.setValueTextColor(Color.rgb(255, 255, 255));
        lineDataSet.setValueTextSize(Utils.px2dip(this, getResources().getDimension(R.dimen.x28)));

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(lineDataSet);

        LineData cd = new LineData(sets);
        return cd;
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initDatas() {


    }

    @OnClick({R.id.layout_back, R.id.layout_show_time_window})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_show_time_window:
                break;
        }
    }

}
