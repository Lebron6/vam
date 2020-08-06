package com.dy.vam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.entity.SummaryGetData;
import com.dy.vam.tools.BarChartManager;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.activity.CompanyTRevenueActivity;
import com.dy.vam.ui.activity.MessageActivity;
import com.dy.vam.ui.widget.IeBarComponent;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TimeSelectWindow;
import com.github.mikephil.charting.charts.BarChart;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.CONTRO;
import static com.dy.vam.config.Constant.INCOME;
import static com.dy.vam.config.Constant.OUTPUT;

/**
 * Created by James on 2018/5/4.
 */

public class Home1Fragment extends BaseFragment {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_time_banner)
    TextView tvTimeBanner;
    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.image_message)
    ImageButton imageMessage;
    @BindView(R.id.layout_message)
    RelativeLayout layoutMessage;
    @BindView(R.id.layout_company)
    RelativeLayout layoutCompany;
    @BindView(R.id.rb_all_income)
    RadioButton rbAllIncome;
    @BindView(R.id.rb_all_out)
    RadioButton rbAllOut;
    @BindView(R.id.rb_distribution)
    RadioButton rbDistribution;
    @BindView(R.id.rg_select_type)
    RadioGroup rgSelectType;
    @BindView(R.id.chart_bar)
    BarChart chartBar;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_see_detail)
    Button tvSeeDetail;
    @BindView(R.id.image_mini_red)
    ImageView imageView;
    protected String[] values = new String[]{
            "", "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
    };
    Unbinder unbinder;
    @BindView(R.id.tv_money_type)
    TextView tvMoneyType;
    @BindView(R.id.view_guide)
    View viewGuide;
    Unbinder unbinder1;
    private SummaryGetData summaryGetData;

    private BarChartManager barChartManager;
    private ArrayList<Float> xValues;
    private List<Integer> colours;
    private List<String> names;
    private List<String> times;
    private ArrayAdapter arrayAdapter;
    private int type;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home1;
    }

    @Override
    protected void initViews() {
        rgSelectType.setOnCheckedChangeListener(onCheckedChangeListener);
        barChartManager = new BarChartManager(chartBar);

        //设置x轴的数据
        xValues = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            xValues.add((float) i);
        }
        //颜色集合
        colours = new ArrayList<>();
        colours.add(getActivity().getResources().getColor(R.color.colorTextTheme));

        //线的名字集合
        names = new ArrayList<>();
        names.add("折线一");
//        barChartManager.showBarChart(xValues, yValues, names, colours); //创建多条折线的图表
        barChartManager.setXAxis(11f, 0f, 12);
        barChartManager.setXFormatter(values);
//        barChartManager.setDescription("X:月份"+"   "+"Y:金额(元)");

    }


    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_all_income:
                    loadDatas(INCOME);
                    break;
                case R.id.rb_all_out:
                    loadDatas(OUTPUT);
                    break;
                case R.id.rb_distribution:
                    loadDatas(CONTRO);
                    break;
            }
        }
    };

    /**
     * 获取首页数据
     *
     * @param type 标识当前选择的是 总收入、总支出、还是可支配收入
     */
    public void loadDatas(final int type) {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getHomeDataUrl()).getHomeData(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryGetData>() {
            @Override
            public void onResponse(Call<SummaryGetData> call, Response<SummaryGetData> response) {
                LoadDialog.dismiss(getActivity());
                summaryGetData = response.body();
                if (summaryGetData != null && summaryGetData.getData() != null) {
                    Home1Fragment.this.type = type;//此处更新type是因为更换年份时候需要同步数据
                    switch (type) {
                        case INCOME:
                            if (summaryGetData.getData().getIncome() != null && summaryGetData.getData().getIncome().getList() != null) {
                                String incomeJson = new Gson().toJson(summaryGetData.getData().getIncome());
                                fillDataToBar(incomeJson);
                                finllDataToView(summaryGetData);
                            }
                            break;
                        case OUTPUT:
                            if (summaryGetData.getData().getOutput() != null && summaryGetData.getData().getOutput().getList() != null) {
                                String outputJson = new Gson().toJson(summaryGetData.getData().getOutput());
                                fillDataToBar(outputJson);
                                finllDataToView(summaryGetData);
                            }
                            break;
                        case CONTRO:
                            if (summaryGetData.getData().getControl() != null && summaryGetData.getData().getControl().getList() != null) {
                                String controlJson = new Gson().toJson(summaryGetData.getData().getControl());
                                fillDataToBar(controlJson);
                                finllDataToView(summaryGetData);
                            }
                            break;
                    }

                    if (summaryGetData.getData().getUser().getNotice_count() == 0) {
                        imageView.setVisibility(View.GONE);
                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }

                    if (summaryGetData.getData().getYears() != null) {
                        times = new ArrayList<>();
                        for (int i = 0; i < summaryGetData.getData().getYears().size(); i++) {
                            String initTime = summaryGetData.getData().getYears().get(i).getYear() + "";
                            if (summaryGetData.getData().getYears().get(i).getSelected() == 1) {
                                tvTimeBanner.setText(initTime);
                                PreferenceUtils.getInstance().setDefaultYear(summaryGetData.getData().getYears().get(i).getYear());
                            }
                            times.add(initTime);
                        }
                        arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_question, R.id.tv_popqusetion, times);
                    }
                    if (!PreferenceUtils.getInstance().getHomeTipsShowStatus()) {
                        viewGuide.post(new Runnable() {
                            @Override
                            public void run() {
                                PreferenceUtils.getInstance().setHomeTipsShowStatus(true);
                                showGuideView();
                            }
                        });
                    }
                } else {
                    Toasty.error(getActivity(), "获取首页信息失败").show();
                }
            }

            @Override
            public void onFailure(Call<SummaryGetData> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取首页信息失败").show();
                Log.e("获取首页信息:", t.toString());
            }
        });
    }

    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(viewGuide)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
            }
        });

        builder.addComponent(new IeBarComponent(getActivity()));
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(getActivity());
    }

    private void finllDataToView(SummaryGetData summaryGetData) {
        tvCompanyName.setText(summaryGetData.getData().getUser().getCompany());
        switch (type) {
            case INCOME:
                tvSeeDetail.setVisibility(View.VISIBLE);
                tvMoneyType.setText("公司总收入(万元)");
                tvAllMoney.setText(summaryGetData.getData().getIncome().getSum() + "");
                break;
            case OUTPUT:
                tvSeeDetail.setVisibility(View.VISIBLE);
                tvMoneyType.setText("公司总支出(万元)");
                tvAllMoney.setText(summaryGetData.getData().getOutput().getSum() + "");
                break;
            case CONTRO:
                tvSeeDetail.setVisibility(View.GONE);
                tvMoneyType.setText("可支配总收入(万元)");
                tvAllMoney.setText(summaryGetData.getData().getControl().getSum() + "");
                break;
        }
    }

    private void fillDataToBar(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("list");
        List<Float> yValues = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if (jsonObject.get(String.valueOf(i + 1)).getAsDouble() == 0) {
                yValues.add(0f);
            } else {
                yValues.add(jsonObject.get(String.valueOf(i + 1)).getAsFloat());
            }
        }
        barChartManager.showBarChart(xValues, yValues, names.get(0), colours.get(0));
    }

    @Override
    protected void initDatas() {
        rbAllIncome.setChecked(true);
        loadDatas(INCOME);
    }

    @OnClick({R.id.layout_show_time_window, R.id.image_message, R.id.tv_see_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_show_time_window:
                TimeSelectWindow timeSelectWindow = new TimeSelectWindow(getActivity());
                timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
                break;
            case R.id.image_message:
                MessageActivity.actionStart(getActivity(), "消息");
                break;
            case R.id.tv_see_detail:
                if (summaryGetData != null && summaryGetData.getData() != null && summaryGetData.getData().getYears() != null && summaryGetData.getData().getYears().size() >= 1) {
                    int[] years = new int[summaryGetData.getData().getYears().size()];
                    for (int j = 0; j < summaryGetData.getData().getYears().size(); j++) {
                        years[j] = summaryGetData.getData().getYears().get(j).getYear();
                    }
                    switch (type) {
                        case INCOME:
                            CompanyTRevenueActivity.actionStart(getActivity(), "公司总收入明细", years, type);
                            break;
                        case OUTPUT:
                            CompanyTRevenueActivity.actionStart(getActivity(), "公司总支出明细", years, type);
                            break;
                        case CONTRO:
                            CompanyTRevenueActivity.actionStart(getActivity(), "公司可支配收入明细", years, type);
                            break;
                    }
                } else {
                    switch (type) {
                        case INCOME:
                            CompanyTRevenueActivity.actionStart(getActivity(), "公司总收入明细");
                            break;
                        case OUTPUT:
                            CompanyTRevenueActivity.actionStart(getActivity(), "公司总支出明细");
                            break;
                        case CONTRO:
                            CompanyTRevenueActivity.actionStart(getActivity(), "公司可支配收入明细");
                            break;
                    }
//                    Toasty.error(getActivity(), "暂无数据！").show();
                }
                break;
        }
    }

    OnTimeSelectedListener listener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            PreferenceUtils.getInstance().setDefaultYear(summaryGetData.getData().getYears().get(postion).getYear());
            tvTimeBanner.setText(times.get(postion));
            loadDatas(type);
        }
    };


}
