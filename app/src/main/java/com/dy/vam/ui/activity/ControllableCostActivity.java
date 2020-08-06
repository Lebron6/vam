package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SummaryGetDepartmentControl;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.ControllableCostAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by James on 2018/1/29.
 * 部门可控制成本
 */

public class ControllableCostActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String DEPARTMENT_ID = "departmentid";
    public static final String DEPARTMENT_NAME = "departmentName";
    public static final String ALL_MONEY_NUMBER="all_money_number";
//    @BindView(R.id.tv_time)
//    TextView tvTime;
//    @BindView(R.id.layout_show_time_window)
//    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.chart_pie)
    PieChart pieChart;
    @BindView(R.id.rv_cc)
    RecyclerView rvCc;
    private ArrayAdapter adapter;

    private List<String> times;
    private ArrayAdapter arrayAdapter;
    private int[] colors;
    private String[] types;
    private float[] proportions;
    private SummaryGetDepartmentControl summaryGetDepartmentControl;
    private ControllableCostAdapter controllableCostAdapter;

    public static void actionStart(Context context, String title, int departmentid,String departmentName,String moneyNumber) {
        Intent intent = new Intent(context, ControllableCostActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(DEPARTMENT_ID, departmentid);
        intent.putExtra(DEPARTMENT_NAME, departmentName);
        intent.putExtra(ALL_MONEY_NUMBER, moneyNumber);
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
//        manger.setTitle(getIntent().getStringExtra(DEPARTMENT_NAME));
        manger.setTitle("部门可控制费用");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_controllable_cost;
    }

    @Override
    protected void initViews() {

        getDataByTime();
        controllableCostAdapter = new ControllableCostAdapter(ControllableCostActivity.this);

    }

    private void getDataByTime() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentControlUrl()).summaryGetDepartmentControl(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(DEPARTMENT_ID, 0)).enqueue(new Callback<SummaryGetDepartmentControl>() {
            @Override
            public void onResponse(Call<SummaryGetDepartmentControl> call, Response<SummaryGetDepartmentControl> response) {
                LoadDialog.dismiss(ControllableCostActivity.this);
                summaryGetDepartmentControl = response.body();
                Log.e("饼状图数据",new Gson().toJson(summaryGetDepartmentControl));
                if (summaryGetDepartmentControl.getCode() == Constant.SUCCESS) {
                    if (summaryGetDepartmentControl.getData() != null && summaryGetDepartmentControl.getData().getYears() != null && summaryGetDepartmentControl.getData().getYears().size() > 0) {
                        times = new ArrayList<>();
                    }else{
//                        Toasty.error(ControllableCostActivity.this, "获取时间失败").show();
                    }
                    if (summaryGetDepartmentControl.getData() != null && summaryGetDepartmentControl.getData().getCost() != null && summaryGetDepartmentControl.getData().getCost().size() > 0){
                      List<SummaryGetDepartmentControl.DataBean.CostBean> deleteCostBeanList=new ArrayList<>();
                        for (int i = 0; i < summaryGetDepartmentControl.getData().getCost().size() ; i++) {
                            if ((summaryGetDepartmentControl.getData().getCost().get(i).getProportion())>=0){
                                deleteCostBeanList.add(summaryGetDepartmentControl.getData().getCost().get(i));
                            }
                        }
//                        summaryGetDepartmentControl.getData().getCost().removeAll(deleteCostBeanList);
                        colors = new int[deleteCostBeanList.size()];
                        types = new String[deleteCostBeanList.size()];
                        proportions = new float[deleteCostBeanList.size()];
                        for (int j = 0; j < deleteCostBeanList.size(); j++) {
                            colors[j]= Color.parseColor(deleteCostBeanList.get(j).getColor());
                            types[j]= deleteCostBeanList.get(j).getTitle();
                            proportions[j]=(float) deleteCostBeanList.get(j).getProportion();
                        }
                        initChart(colors,types,proportions,summaryGetDepartmentControl.getData().getTotal());
                        controllableCostAdapter.setDatas(summaryGetDepartmentControl);
                        RecyclerViewHelper.initRecyclerViewV(ControllableCostActivity.this, rvCc, false, controllableCostAdapter);
                        controllableCostAdapter.setOnItemClickLitener(new ControllableCostAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ControllableCostDetailsActivity.actionStart(ControllableCostActivity.this, "可控制成本详情",summaryGetDepartmentControl.getData().getCost().get(position).getType(),getIntent().getIntExtra(DEPARTMENT_ID,1),getIntent().getStringExtra(DEPARTMENT_NAME));
                            }
                        });
                        ViewGroup.LayoutParams layoutParams = rvCc.getLayoutParams();
                        layoutParams.height = getRecyHeight();
                        rvCc.setLayoutParams(layoutParams);
                    }else{
//                        Toasty.error(ControllableCostActivity.this, "暂无数据").show();
                    }
                } else {
                    Toasty.error(ControllableCostActivity.this, summaryGetDepartmentControl.getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<SummaryGetDepartmentControl> call, Throwable t) {
                LoadDialog.dismiss(ControllableCostActivity.this);
                Toasty.warning(ControllableCostActivity.this, getString(R.string.net_error)+":获取部门可控制成本图表数据失败").show();
                Log.e("获取部门可控制成本图表数据失败：", t.toString());
            }
        });
    }
    private int getRecyHeight() {
        int height = 0;
            height = height + (summaryGetDepartmentControl.getData().getCost().size()*(int)getResources().getDimension(R.dimen.x230));

        return height;
    }
    private void initChart(int []colors,String []types,float[] proportions,String total) {
        Log.e("colors:",colors.length+"");
        Log.e("types:",types.length+"");
        Log.e("proportionsSize:",proportions.length+"");
        pieChart.setExtraOffsets(5, 10, 5, 5);
//        pieChart.setExtraOffsets(Utils.px2dip(this, getResources().getDimension(R.dimen.x60)), Utils.px2dip(this, getResources().getDimension(R.dimen.x1)), Utils.px2dip(this, getResources().getDimension(R.dimen.x60)), Utils.px2dip(this, getResources().getDimension(R.dimen.x70)));
        // 设置饼图是否接收点击事件，默认为true
        pieChart.setTouchEnabled(false);
        //设置饼图是否使用百分比
        pieChart.setUsePercentValues(false);
        //设置饼图右下角的文字描述
//        pieChart.setDescription("测试");
        //设置饼图右下角的文字大小
//        pieChart.setDescriptionTextSize(16);
        //设置取消饼图右下角的文字
        pieChart.getDescription().setEnabled(false);
        //是否显示圆盘中间文字，默认显示
        pieChart.setDrawCenterText(true);
        //设置圆盘中间文字的大小
        pieChart.setCenterTextSize(Utils.px2dip(this, getResources().getDimension(R.dimen.x36)));
        //设置圆盘中间文字的颜色
        pieChart.setCenterTextColor(getResources().getColor(R.color.colorTextTheme));
        //设置圆盘中间文字
        pieChart.setCenterText("总计\n"+"￥"+total);
        //设置圆盘中间文字的字体
        pieChart.setCenterTextTypeface(Typeface.DEFAULT);
        //设置中间圆盘的颜色
        pieChart.setHoleColor(getResources().getColor(R.color.colorTheme));
        //设置中间圆盘的半径,值为所占饼图的百分比
        pieChart.setHoleRadius(42);
        pieChart.setDrawEntryLabels(false);

//        设置中间透明圈的半径,值为所占饼图的百分比
        pieChart.setTransparentCircleRadius(50);
        pieChart.setTransparentCircleColor(getResources().getColor(R.color.colorPieBGTrans));

        //是否显示饼图中间空白区域，默认显示
        pieChart.setDrawHoleEnabled(true);
        //设置圆盘是否转动，默认转动
        pieChart.setRotationEnabled(true);
        //设置初始旋转角度
        pieChart.setRotationAngle(0);
//        mLegend.setYEntrySpace(5f);
        pieChart.getLegend().setEnabled(false);
        //设置X轴动画
        pieChart.animateX(1800);
//        //设置y轴动画
        pieChart.animateY(1800);
//        //设置xy轴一起的动画
        pieChart.animateXY(1800, 1800);
//        Legend l = pieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setPosition(BELOW_CHART_RIGHT);
//        l.setForm(Legend.LegendForm.CIRCLE);
//        l.setTextColor(getResources().getColor(R.color.colorWhite));
//        l.setWordWrapEnabled(true);
//        List<LegendEntry> legendEntrys = new ArrayList<>();
//        for (int i = 0; i < proportions.length; i++) {
//            LegendEntry legenEntry = new LegendEntry();
//            legenEntry.formColor = colors[i];
//            legenEntry.label = types[i];
//            legendEntrys.add(legenEntry);
//        }
//
//        l.setCustom(legendEntrys);
//        l.setEnabled(true);
        //绑定数据
        bindData(colors,types,proportions);

        // 设置一个选中区域监听
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    /**
     * 绑定数据
     * @param colors
     * @param types
     */
    private void bindData(int[] colors, String[] types,float[] proportions) {
        /**
         * valueList将一个饼形图分成三部分，各个区域的百分比的值
         * Entry构造函数中
         * 第一个值代表所占比例，
         * 第二个值代表区域位置
         * （可以有第三个参数，表示携带的数据object）这里没用到
         */
        List<PieEntry> valueList = new ArrayList<>();
        for (int i = 0; i < proportions.length; i++) {
            valueList.add(new PieEntry(proportions[i], i));
        }

        //显示在比例图上
        PieDataSet dataSet = new PieDataSet(valueList, "");
        //设置个饼状图之间的距离
        dataSet.setSliceSpace(0f);
        // 部分区域被选中时多出的长度
        dataSet.setSelectionShift(5f);

        // 设置饼图各个区域颜色
        ArrayList<Integer> colorList = new ArrayList<Integer>();
        for (int i = 0; i <proportions.length; i++) {
            colorList.add(colors[i]);
        }
        dataSet.setColors(colors);
        dataSet.setValueLinePart1OffsetPercentage(70.f);
        dataSet.setValueLinePart1Length(0.6f);
        dataSet.setValueLinePart2Length(0.8f);
        dataSet.setValueTextSize(Utils.px2dip(this, getResources().getDimension(R.dimen.x22)));
        dataSet.setValueLineColor(getResources().getColor(R.color.colorPieLine));
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        PieData data = new PieData(dataSet);
        data.setDrawValues(false);
        //设置以百分比显示
        data.setValueFormatter(new PercentFormatter());
        //区域文字的大小
        data.setValueTextSize(Utils.px2dip(this, getResources().getDimension(R.dimen.x22)));
        //设置区域文字的颜色
        data.setValueTextColor(getResources().getColor(R.color.colorTextTheme));
        //设置区域文字的字体
//        data.setValueTypeface(Typeface.DEFAULT);

        pieChart.setData(data);

        for (IDataSet<?> set : pieChart.getData().getDataSets()) {
            set.setDrawValues(set.isDrawValuesEnabled());
        }
        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


    @Override
    protected void initDatas() {

    }


//    @OnClick(R.id.layout_show_time_window)
//    public void onViewClicked() {
//        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(ControllableCostActivity.this);
//        timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
//    }

//    OnTimeSelectedListener listener = new OnTimeSelectedListener() {
//        @Override
//        public void select(int postion) {
//            year = summaryGetDepartmentControl.getData().getYears().get(postion).getYear();
//            tvTime.setText(times.get(postion));
//            getDataByTime(year);
//        }
//    };

}
