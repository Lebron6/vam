package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.ControllableCostActivity;
import com.dy.vam.ui.activity.DepartmentGetEmployeeListActivity;
import com.dy.vam.ui.activity.DepartmentalDisposableIncomeActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.adapter.DepartProportionAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/29.
 * 总经理查看部门、该部门为比例制
 */

public class CEOProportionFragment extends BaseFragment {
    @BindView(R.id.rv_depart_top)
    RecyclerView rvDepartTop;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.layout_see_dedails)
    LinearLayout layoutSeeDedails;
    @BindView(R.id.tv_disposable)
    TextView tvDisposable;
    @BindView(R.id.tv_control)
    TextView tvControl;
    @BindView(R.id.tv_netoutput)
    TextView tvNetoutput;
    @BindView(R.id.layout_departmental_disposable_income)
    LinearLayout layoutDepartmentalDisposableIncome;
    @BindView(R.id.layout_controllable_cost)
    LinearLayout layoutControllableCost;
    @BindView(R.id.layout_member_management)
    LinearLayout layoutMemberManagement;
    @BindView(R.id.tv_system_name)
    TextView tvSystemName;
    @BindView(R.id.layout_tab_four)
    LinearLayout layoutTabFour;
    @BindView(R.id.tv_lunch_more)
    TextView tvLunchMore;
    Unbinder unbinder;
    private SummaryDepartmentData summaryDepartmentData;
    private int position;
    private String detailsUrl;

    public CEOProportionFragment() {
    }

    public CEOProportionFragment(SummaryDepartmentData summaryDepartmentData, int position) {
        this.summaryDepartmentData = summaryDepartmentData;
        this.position = position;
        detailsUrl = summaryDepartmentData.getData().getDetail_url();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_ceo_depart_proportion;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentDataUrl()).getSummaryDepartmentData(PreferenceUtils.getInstance().getUserToken(), summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid(),PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryDepartmentData>() {
            @Override
            public void onResponse(Call<SummaryDepartmentData> call, Response<SummaryDepartmentData> response) {
                if (response.body().getCode() == Constant.SUCCESS) {
                    if (response.body().getData() != null) {
                        tvAllMoney.setText("￥" + response.body().getData().getDistrubution());
                        tvControl.setText("￥" + response.body().getData().getControl());
                        tvDisposable.setText("￥" + response.body().getData().getDisposable());
                        tvNetoutput.setText("￥" + response.body().getData().getNetoutput());
                        detailsUrl = response.body().getData().getDetail_url();
//                        if (response.body().getData().getUserdist() != null && response.body().getData().getUserdist().size() > 0) {
//                            DepartProportionAdapter adapter = new DepartProportionAdapter(getActivity());
//                            adapter.setDatas(response.body());
//                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvDepartTop, false, adapter);
//                            rvDepartTop.setFocusable(false);
//                        } else {
//                            layoutTabFour.setVisibility(View.GONE);
//                        }
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

    @OnClick({R.id.image_question, R.id.layout_see_dedails, R.id.layout_departmental_disposable_income, R.id.layout_controllable_cost, R.id.layout_member_management, R.id.tv_system_name, R.id.tv_lunch_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_question:
                WebViewActivity.actionStart(getActivity(), "名词解释", summaryDepartmentData.getData().getHelp_url());
                break;
            case R.id.layout_see_dedails:
                WebViewActivity.actionStart(getActivity(), "详情", detailsUrl);
                break;
            case R.id.layout_departmental_disposable_income:
                DepartmentalDisposableIncomeActivity.actionStart(getActivity(), "部门可支配收入", summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid());
                break;
            case R.id.layout_controllable_cost:
                ControllableCostActivity.actionStart(getActivity(), "部门可控制成本", summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid(), summaryDepartmentData.getData().getDepartment().get(position).getName(), summaryDepartmentData.getData().getControl());
                break;
            case R.id.layout_member_management:
                DepartmentGetEmployeeListActivity.actionStart(getActivity(), "成员管理", summaryDepartmentData.getData().getDepartment().get(position).getDepartmentid());
                break;

            case R.id.tv_system_name:
                break;
            case R.id.tv_lunch_more:
                break;
        }
    }

}
