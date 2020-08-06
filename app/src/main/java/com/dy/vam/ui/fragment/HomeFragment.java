package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.HomeRecylerData;
import com.dy.vam.entity.SummaryGetData;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.tools.GlideCircleTransform;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.activity.CompanyTRevenueActivity;
import com.dy.vam.ui.activity.MessageActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.adapter.HomeIERecyAdapter;
import com.dy.vam.ui.adapter.NewDynamicRecyAdapter;
import com.dy.vam.ui.widget.IeBarComponent;
import com.dy.vam.ui.widget.InterpretationComponent;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TimeSelectWindow;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
 * Created by James on 2018/1/9.
 * 首页
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.rv_ie_bar)
    RecyclerView rvIeBar;
    @BindView(R.id.rv_new_dynamic)
    RecyclerView rvNewDynamic;
    @BindView(R.id.image_message)
    ImageView imageMessage;
    @BindView(R.id.layout_message)
    RelativeLayout layoutMessage;
    @BindView(R.id.tv_company_revenue)
    TextView tvCompanyRevenue;
    @BindView(R.id.tv_company_expenditure)
    TextView tvCompanyExpenditure;
    @BindView(R.id.tv_disposable_income)
    TextView tvDisposableIncome;
    @BindView(R.id.tv_company_revenue_num)
    TextView tvCompanyRevenueNum;
    @BindView(R.id.tv_company_expenditure_num)
    TextView tvCompanyExpenditureNum;
    @BindView(R.id.tv_disposable_income_num)
    TextView tvDisposableIncomeNum;
    @BindView(R.id.tv_see_detail)
    Button tvSeeDetails;
    @BindView(R.id.image_user_icon)
    ImageView imageUserIcon;
    @BindView(R.id.image_to_noun_interpretation)
    ImageButton imageToNounInterpretation;
    @BindView(R.id.tv_total_distributable_number)
    TextView tvTotalDistributableNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_lunch_more)
    TextView tvLunchMore;
    @BindView(R.id.layout_ie_unit)
    LinearLayout layoutIeUnit;
    @BindView(R.id.layout_ie_number)
    LinearLayout layoutIeNumber;
    @BindView(R.id.layout_recy)
    LinearLayout layoutRecy;
    @BindView(R.id.layout_my_value)
    RelativeLayout layoutMyValue;
    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.tv_time_banner)
    TextView tvTimeBanner;
    Unbinder unbinder;
    @BindView(R.id.view_guide)
    View viewGuide;
    @BindView(R.id.layout_spinner)
    RelativeLayout layoutSpinner;
    Unbinder unbinder1;
    @BindView(R.id.layout_to_noun_interpretation)
    RelativeLayout layoutToNounInterpretation;
    @BindView(R.id.tv_total_type)
    TextView tvTotalType;
    private HomeIERecyAdapter homeIEAdapter;
    private ArrayAdapter arrayAdapter;
    private SummaryGetData summaryGetData;
    private NewDynamicRecyAdapter newDynamicAdapter;
    private List<String> times;
    private int type;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        loadDatas(INCOME);
        homeIEAdapter = new HomeIERecyAdapter(getActivity());
        newDynamicAdapter = new NewDynamicRecyAdapter(getActivity());

        layoutRecy.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        Log.e("Linea宽高",layoutRecy.getHeight()+"");
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
                showGuideView2();
            }
        });

        builder.addComponent(new IeBarComponent(getActivity()));
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(getActivity());
    }

    public void showGuideView2() {
        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(layoutToNounInterpretation)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setOverlayTarget(false)
                .setExitAnimationId(android.R.anim.fade_out)
                .setOutsideTouchable(false);
        builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
            }
        });

        builder1.addComponent(new InterpretationComponent(getActivity()));
        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(getActivity());
    }

    /**
     * 获取首页数据
     * @param type 标识当前选择的是 总收入、总支出、还是可支配收入
     */
    public void loadDatas(final int type) {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getHomeDataUrl()).getHomeData(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryGetData>() {
            @Override
            public void onResponse(Call<SummaryGetData> call, Response<SummaryGetData> response) {
                Log.e("回调里的Home数据",new Gson().toJson(response.body()));
                LoadDialog.dismiss(getActivity());
                summaryGetData = response.body();
                if (summaryGetData != null && summaryGetData.getData() != null) {
                    if (summaryGetData.getData().getUser() != null) {
                        if (summaryGetData.getData().getUser().getRole() == 2) {
                            tvTotalType.setText("公司可分配价值总额：");
                        }
                        tvPosition.setText(summaryGetData.getData().getUser().getCompany());
                        tvTotalDistributableNumber.setText("￥" + summaryGetData.getData().getUser().getDistribution());
                        Glide.with(getActivity()).load(summaryGetData.getData().getUser().getHeadimg()).bitmapTransform(new GlideCircleTransform(getActivity())).into(imageUserIcon);
                    } else {
                        Toasty.error(getActivity(), "获取当前用户信息失败").show();
                    }
                    if (summaryGetData.getData().getIncome() != null) {
                        tvCompanyRevenueNum.setText(summaryGetData.getData().getIncome().getSum() + "");
                    }
                    if (summaryGetData.getData().getOutput() != null) {
                        tvCompanyExpenditureNum.setText(summaryGetData.getData().getOutput().getSum() + "");
                    }
                    if (summaryGetData.getData().getControl() != null) {
                        tvDisposableIncomeNum.setText(summaryGetData.getData().getControl().getSum() + "");
                    }
                    switch (type) {
                        case INCOME:
                            if (summaryGetData.getData().getIncome() != null && summaryGetData.getData().getIncome().getList() != null) {
                                String incomeJson = new Gson().toJson(summaryGetData.getData().getIncome());
                                fillDataToIeRecycler(incomeJson);
                            }
                            break;
                        case OUTPUT:
                            if (summaryGetData.getData().getOutput() != null && summaryGetData.getData().getOutput().getList() != null) {
                                String outputJson = new Gson().toJson(summaryGetData.getData().getOutput());
                                fillDataToIeRecycler(outputJson);
                            }
                            break;
                        case CONTRO:
                            if (summaryGetData.getData().getControl() != null && summaryGetData.getData().getControl().getList() != null) {
                                String controlJson = new Gson().toJson(summaryGetData.getData().getControl());
                                fillDataToIeRecycler(controlJson);
                            }
                            break;
                    }
                    if (summaryGetData.getData().getTrends() != null) {
                        tvTime.setText(summaryGetData.getData().getTrends().getTime());
                    }

                    if (summaryGetData.getData().getTrends().getList() != null && summaryGetData.getData().getTrends().getList().size() > 0) {
                        newDynamicAdapter.setDatas(summaryGetData);
                        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvNewDynamic, false, newDynamicAdapter);
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

    /**
     * 根据不同json 刷新柱状图View  在该type被选中时调用
     *
     * @param json
     */
    public void fillDataToIeRecycler(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("list");
        List<HomeRecylerData> testLinesBeans = new ArrayList<>();
        Double[] sorts = new Double[12];
        for (int i = 0; i < 12; i++) {
            HomeRecylerData testBean = new HomeRecylerData();
            testBean.setMonth(i + 1 + "月");
            if (jsonObject.get(String.valueOf(i + 1)).getAsDouble() == 0) {
                testBean.setNumber(0 + "");
                sorts[i] = 0.0;
            } else {
                sorts[i] = jsonObject.get(String.valueOf(i + 1)).getAsDouble();
                testBean.setNumber(jsonObject.get(String.valueOf(i + 1)).getAsDouble() + "");
            }
            if (new JsonParser().parse(json).getAsJsonObject().get("sum").getAsInt() != 0) {
                testBean.setPercent((int) (jsonObject.get(String.valueOf(i + 1)).getAsDouble()));
            }
            testLinesBeans.add(testBean);
        }
        homeIEAdapter.setDatas(testLinesBeans, Utils.bubbleSort(sorts)[11]);//冒泡排序筛选最大的那一月份额度
        RecyclerViewHelper.initRecyclerViewH(getActivity(), rvIeBar, false, homeIEAdapter);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_to_noun_interpretation, R.id.layout_show_time_window, R.id.layout_message, R.id.image_message, R.id.tv_see_detail, R.id.image_to_noun_interpretation, R.id.tv_lunch_more, R.id.tv_company_revenue, R.id.tv_company_expenditure, R.id.tv_disposable_income, R.id.tv_company_revenue_num, R.id.tv_company_expenditure_num, R.id.tv_disposable_income_num})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.tv_company_revenue || i == R.id.tv_company_revenue_num) {
            if (summaryGetData != null && summaryGetData.getData() != null) {
                type = INCOME;
                changeColorByChecked(tvCompanyRevenue, tvCompanyRevenueNum);
            } else {
                Toasty.error(getActivity(), getString(R.string.net_error) + ":总收入参数错误,请求失败！").show();
            }
        } else if (i == R.id.tv_company_expenditure || i == R.id.tv_company_expenditure_num) {
            if (summaryGetData != null && summaryGetData.getData() != null) {
                type = OUTPUT;
                changeColorByChecked(tvCompanyExpenditure, tvCompanyExpenditureNum);
            } else {
                Toasty.error(getActivity(), getString(R.string.net_error) + ":总支出参数错误,请求失败！").show();
            }

        } else if (i == R.id.tv_disposable_income || i == R.id.tv_disposable_income_num) {
            if (summaryGetData != null && summaryGetData.getData() != null) {
                type = CONTRO;
                changeColorByChecked(tvDisposableIncome, tvDisposableIncomeNum);
            } else {
                Toasty.error(getActivity(), getString(R.string.net_error) + ":可支配参数错误,请求失败！").show();
            }

        } else if (i == R.id.tv_see_detail) {
            if (summaryGetData != null && summaryGetData.getData() != null && summaryGetData.getData().getYears() != null && summaryGetData.getData().getYears().size() > 1) {
                int[] years = new int[summaryGetData.getData().getYears().size()];
                for (int j = 0; j < summaryGetData.getData().getYears().size(); j++) {
                    years[j] = summaryGetData.getData().getYears().get(j).getYear();
                }
                switch (type) {
                    case INCOME:
                        CompanyTRevenueActivity.actionStart(getActivity(), "公司总收入明细", years,type);
                        break;
                    case OUTPUT:
                        CompanyTRevenueActivity.actionStart(getActivity(), "公司总支出明细", years,type);
                        break;
                    case CONTRO:
                        CompanyTRevenueActivity.actionStart(getActivity(), "公司可支配收入明细", years, type);
                        break;
                }
            } else {
                Toasty.error(getActivity(), "时间列表加载失败！").show();
            }
        } else if (i == R.id.image_to_noun_interpretation || i == R.id.layout_to_noun_interpretation) {
            WebViewActivity.actionStart(getActivity(), "名词解释", summaryGetData.getData().getHelp_url());

        } else if (i == R.id.tv_lunch_more) {
            WebViewActivity.actionStart(getActivity(), "最新动态", summaryGetData.getData().getDynamic_url());

        } else if (i == R.id.image_message || i == R.id.layout_message) {
            MessageActivity.actionStart(getActivity(), "消息");

        } else if (i == R.id.layout_show_time_window) {
            TimeSelectWindow timeSelectWindow = new TimeSelectWindow(getActivity());
            timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
        }
    }

    public void changeColorByChecked(TextView... textViews) {
        int color = getResources().getColor(R.color.white);
        tvDisposableIncome.setTextColor(color);
        tvDisposableIncomeNum.setTextColor(color);
        tvCompanyExpenditure.setTextColor(color);
        tvCompanyExpenditureNum.setTextColor(color);
        tvCompanyRevenue.setTextColor(color);
        tvCompanyRevenueNum.setTextColor(color);
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setTextColor(getResources().getColor(R.color.colorTextTheme));
        }
        if (type == CONTRO) {
            tvSeeDetails.setVisibility(View.GONE);
            fillDataToIeRecycler(new Gson().toJson(summaryGetData.getData().getControl()));
        } else if (type == INCOME) {
            tvSeeDetails.setVisibility(View.VISIBLE);
            fillDataToIeRecycler(new Gson().toJson(summaryGetData.getData().getIncome()));
        } else {
            tvSeeDetails.setVisibility(View.VISIBLE);
            fillDataToIeRecycler(new Gson().toJson(summaryGetData.getData().getOutput()));
        }

    }

    OnTimeSelectedListener listener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            PreferenceUtils.getInstance().setDefaultYear(summaryGetData.getData().getYears().get(postion).getYear());
            tvTimeBanner.setText(times.get(postion));
            loadDatas( type);
        }
    };

}

