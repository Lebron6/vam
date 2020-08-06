package com.dy.vam.tools;

import android.graphics.Color;
import android.graphics.Matrix;

import com.dy.vam.R;
import com.dy.vam.application.VAMApplication;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class BarChartManager {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    public BarChartManager(BarChart barChart) {
        this.mBarChart = barChart;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        //背景颜色
        mBarChart.setBackgroundColor(VAMApplication.getApplication().getResources().getColor(R.color.ThemeTrans));
        //网格
        mBarChart.setDrawGridBackground(false);
        //是否可以拖拽
        mBarChart.setDragEnabled(true);
        // 设置是否可以触摸
        mBarChart.setTouchEnabled(true);
        // 是否可以缩放
        mBarChart.setScaleEnabled(true);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        mBarChart.setHighlightFullBarEnabled(false);
        mBarChart.setHighlightPerTapEnabled(false);
        mBarChart.setHighlightPerDragEnabled(false);
        //显示边界
        mBarChart.setDrawBorders(false);
        //使两侧的柱图完全显示
        mBarChart.setFitBars(true);
        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);

//        折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //XY轴的设置
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setEnabled(false);//网格线、文字一并消失
        leftAxis.setAxisLineWidth(Utils.px2dip(VAMApplication.getApplication(),VAMApplication.getApplication().getResources().getDimension(R.dimen.x1)));
        leftAxis.setAxisLineColor(VAMApplication.getApplication().getResources().getColor(R.color.colorLineHomeleftAxis));
        leftAxis.setTextColor(VAMApplication.getApplication().getResources().getColor(R.color.colorTextHomeleftAxis));
//        leftAxis.setDrawGridLines(false);
        leftAxis.setGridColor(VAMApplication.getApplication().getResources().getColor(R.color.colorLineHomeleftAxis));
        leftAxis.setGridLineWidth(Utils.px2dip(VAMApplication.getApplication(),VAMApplication.getApplication().getResources().getDimension(R.dimen.x1)));

        xAxis.setTextColor(Color.WHITE);
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisLineColor(VAMApplication.getApplication().getResources().getColor(R.color.colorLineHomeleftAxis));
        xAxis.setDrawGridLines(false);

        Matrix m=new Matrix();
        m.postScale(2f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mBarChart.getViewPortHandler().refresh(m, mBarChart, false);//将图表动画显示之前进行缩放

        mBarChart.animateX(1000); // 立即执行的动画,x轴
    }

    /**
     * 展示柱状图(一条)
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showBarChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart();
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new BarEntry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);

        barDataSet.setColor(color);
        barDataSet.setValueTextSize(9f);
        barDataSet.setValueTextColor(VAMApplication.getApplication().getResources().getColor(R.color.ThemeTrans));
        barDataSet.setFormLineWidth(VAMApplication.getApplication().getResources().getDimension(R.dimen.x1));
        barDataSet.setFormSize(15.f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        data.setBarWidth(0.15f);
        //设置X轴的刻度数
        xAxis.setLabelCount(6 , false);
        mBarChart.setData(data);
        mBarChart.getLegend().setEnabled(false);
        mBarChart.getDescription().setEnabled(false);   //表述
    }

    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showBarChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        BarData data = new BarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }
        int amount = yAxisValues.size();

        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet

        // (0.2 + 0.02) * 4 + 0.08 = 1.00 -> interval per "group"
        xAxis.setLabelCount(xAxisValues.size() - 1, false);
        data.setBarWidth(barWidth);

        data.groupBars(0, groupSpace, barSpace);
        mBarChart.setData(data);
    }


    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max+2);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(6, false);
//        mBarChart.invalidate();
    }

    /**
     * 自定义x轴显示
     */
    public void setXFormatter(String[] values){
        MyXFormatter formatter = new MyXFormatter(values);
        //显示个数
        xAxis.setLabelCount(6);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(formatter);
        mBarChart.invalidate();
    }

    /**
     * 设置高限制线
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
        description.setTextColor(Color.WHITE);
        mBarChart.invalidate();
    }
}