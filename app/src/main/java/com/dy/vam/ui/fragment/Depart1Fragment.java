package com.dy.vam.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.activity.ControllableCostActivity;
import com.dy.vam.ui.activity.DepartmentGetEmployeeListActivity;
import com.dy.vam.ui.activity.DepartmentalDisposableIncomeActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.adapter.SlideDepartListAdapter;
import com.dy.vam.ui.widget.SlideMenuView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/5/11.
 */

public class Depart1Fragment extends BaseFragment {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.image_slide)
    ImageView imageSlide;
    @BindView(R.id.tv_depart_name)
    TextView tvDepartName;
    @BindView(R.id.image_staff_manger)
    ImageView imageStaffManger;
    @BindView(R.id.layout_top)
    RelativeLayout layoutTop;
    @BindView(R.id.image_outside)
    ImageView imageOutside;
    @BindView(R.id.image_inside)
    ImageView imageInside;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.tv_control_income)
    TextView tvControlIncome;
    @BindView(R.id.layout_income)
    LinearLayout layoutIncome;
    @BindView(R.id.tv_control_cost)
    TextView tvControlCost;
    @BindView(R.id.layout_cost)
    LinearLayout layoutCost;
    @BindView(R.id.image_new_dynamic)
    ImageView imageNewDynamic;
    @BindView(R.id.tv_system_type)
    TextView tvSystemType;
    @BindView(R.id.tv_system_details)
    ImageView tvSystemDetails;
    @BindView(R.id.tv_distribution)
    TextView tvDistribution;
    Unbinder unbinder;
    @BindView(R.id.image_mini_red)
    ImageView imageMiniRed;
    @BindView(R.id.tv_proportion)
    TextView tvProportion;
    @BindView(R.id.tv_company_distrubution)
    TextView tvCompanyDistrubution;
    Unbinder unbinder1;
    private SummaryDepartmentData summaryDepartmentData;
    private String dynamic_url;
    private String allot_url;
    private String distrubution_url;
    private String controlMoney;

    private int position = 0;
    private SlideMenuView slideMenuView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_depart_one;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        loadDepartInfo();
    }

    /**
     * 加载部门首页
     */
    private void loadDepartInfo() {
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentDataUrl()).getSummaryDepartmentData(PreferenceUtils.getInstance().getUserToken(), 0, PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryDepartmentData>() {
            @Override
            public void onResponse(Call<SummaryDepartmentData> call, Response<SummaryDepartmentData> response) {
                summaryDepartmentData = response.body();
                Log.e("部门首页数据", new Gson().toJson(summaryDepartmentData));
                if (summaryDepartmentData != null) {
                    if (summaryDepartmentData.getCode() == Constant.SUCCESS) {
                        if (summaryDepartmentData.getData() != null && summaryDepartmentData.getData().getDepartment() != null && summaryDepartmentData.getData().getDepartment().size() > 0) {
                            initDataByRoleType(summaryDepartmentData.getData().getRole());

                        }
                    } else {
                        Toasty.error(getActivity(), summaryDepartmentData.getMsg()).show();
                    }
                } else {
                    Toasty.error(getActivity(), getString(R.string.net_error) + ":获取部门信息失败").show();
                }

            }

            @Override
            public void onFailure(Call<SummaryDepartmentData> call, Throwable t) {
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取部门信息失败").show();
                Log.e("获取部门信息:", t.toString());
            }
        });
    }

    private void initDataByRoleType(int i) {//这里根据不同角色做处理
        slideMenuView = new SlideMenuView(getActivity(), R.layout.layout_slide_menu, onItemClickLitener, summaryDepartmentData, position);
        loadDataByDepartmentId(position);
        switch (i) {
            case 0:
                imageSlide.setVisibility(View.GONE);
                break;
            case 1:
                imageSlide.setVisibility(View.GONE);
                break;
            case 2:
                imageSlide.setVisibility(View.VISIBLE);
                break;
        }

    }

    @OnClick({R.id.image_outside, R.id.image_slide, R.id.image_staff_manger, R.id.layout_income, R.id.layout_cost, R.id.image_new_dynamic, R.id.tv_system_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_slide:
                slideMenuView.initDatas(position);
                slideMenuView.showMenu();
                break;
            case R.id.image_staff_manger:
                DepartmentGetEmployeeListActivity.actionStart(getActivity(), "成员管理", summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid());
                break;
            case R.id.layout_income:
                DepartmentalDisposableIncomeActivity.actionStart(getActivity(), "部门可支配收入", summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid());
                break;
            case R.id.layout_cost:
                ControllableCostActivity.actionStart(getActivity(), "部门可控制费用", summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid(), summaryDepartmentData.getData().getDepartment().get(position).getName(), controlMoney);
                break;
            case R.id.image_new_dynamic:
                WebViewActivity.actionStart(getActivity(), "最新动态", dynamic_url);
                break;
            case R.id.tv_system_details:
                WebViewActivity.actionStart(getActivity(), "当前分配机制", allot_url);
                break;
            case R.id.image_outside:
                WebViewActivity.actionStart(getActivity(), "部门可分配价值", distrubution_url);
                break;
        }
    }

    SlideDepartListAdapter.OnItemClickLitener onItemClickLitener = new SlideDepartListAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, int position) {
            loadDataByDepartmentId(position);
            slideMenuView.toggle();
        }
    };

    private void loadDataByDepartmentId(int position) {
        Depart1Fragment.this.position = position;
        tvDepartName.setText(summaryDepartmentData.getData().getDepartment().get(position).getName());
        if (summaryDepartmentData.getData().getDepartment().get(position).getAllot() == Constant.System.PROPORTION) {
            tvSystemType.setText("比例制");
        } else {
            tvSystemType.setText("积分制");
        }
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentDataUrl()).getSummaryDepartmentData(PreferenceUtils.getInstance().getUserToken(), summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid(), PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryDepartmentData>() {
            @Override
            public void onResponse(Call<SummaryDepartmentData> call, Response<SummaryDepartmentData> response) {
                if (response.body().getCode() == Constant.SUCCESS) {
                    if (response.body().getData() != null) {

                        tvControlIncome.setText("￥" + response.body().getData().getDisposable());
                        tvControlCost.setText("￥" + response.body().getData().getControl());
                        tvDistribution.setText("￥" + response.body().getData().getDistrubution());
                        tvCompanyDistrubution.setText(response.body().getData().getCompany_distrubution());
                        tvProportion.setText(response.body().getData().getProportion());
                        dynamic_url = response.body().getData().getDynamic_url();
                        allot_url = response.body().getData().getAllot_url();
                        distrubution_url = response.body().getData().getDetail_url();
                        controlMoney = response.body().getData().getControl();
                        imageMiniRed.setVisibility(response.body().getData().getDynamic_status() == 0 ? View.GONE : View.VISIBLE);

                    } else {

                        Toasty.error(getActivity(), "获取部门信息失败！").show();

                    }

                } else {

                    Toasty.error(getActivity(), response.body().getMsg()).show();

                }

            }

            @Override
            public void onFailure(Call<SummaryDepartmentData> call, Throwable t) {

                 //Toasty.error(getActivity(),getString(R.string.net_error)+":CEO获取部门信息失败").show();

            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Animation circle_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_round_rotate_clockwise);
            Animation circle_anim_anti = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_round_rotate_anti_clockwise);
            imageOutside.startAnimation(circle_anim);
            imageInside.startAnimation(circle_anim_anti);

        }

    }
}
