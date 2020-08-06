package com.dy.vam.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.dy.vam.R;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.activity.ControllableCostActivity;
import com.dy.vam.ui.activity.DepartmentGetEmployeeListActivity;
import com.dy.vam.ui.activity.DepartmentalDisposableIncomeActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.widget.SeeDepartStaffComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2018/1/29.
 * 行政、人事、营销部门查看 积分制部门
 */

public class OtherIntegralFragment extends BaseFragment {
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
    @BindView(R.id.btn_new_task)
    Button btnNewTask;
    @BindView(R.id.btn_task_panel)
    Button btnTaskPanel;
    @BindView(R.id.btn_admin_integral)
    Button btnAdminIntegral;
    @BindView(R.id.layout_panel)
    LinearLayout layoutPanel;
    @BindView(R.id.layout_tab_four)
    LinearLayout layoutTabFour;
    @BindView(R.id.tv_lunch_more)
    TextView tvLunchMore;
    SummaryDepartmentData summaryDepartmentData;
    @BindView(R.id.tv_system_more)
    TextView tvSystemMore;
    Unbinder unbinder;
    private MyBroadcastReceiver myBroadcastReceiver;

    public OtherIntegralFragment() {
    }

    public OtherIntegralFragment(SummaryDepartmentData summaryDepartmentData) {
        this.summaryDepartmentData = summaryDepartmentData;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_other_depart_integral;
    }

    @Override
    protected void initViews() {
        tvAllMoney.setText("￥" + summaryDepartmentData.getData().getDistrubution());
        tvControl.setText("￥" + summaryDepartmentData.getData().getControl());
        tvDisposable.setText("￥" + summaryDepartmentData.getData().getDisposable());
        tvNetoutput.setText("￥" + summaryDepartmentData.getData().getNetoutput());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION);
        myBroadcastReceiver = new MyBroadcastReceiver();
        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!PreferenceUtils.getInstance().getSeeDepartStaffShowStatus()&&intent.getAction().equals(Constant.ACTION)) {
                tvSystemMore.post(new Runnable() {
                    @Override
                    public void run() {
                        PreferenceUtils.getInstance().setSeeDepartStaffShowStatus(true);
                        showGuideView();
                    }
                });
            }
        }
    }

    @Override
    protected void initDatas() {
//        if (summaryDepartmentData.getData().getUserdist() != null && summaryDepartmentData.getData().getUserdist().size() > 0) {
//            DepartIntegralAdapter adapter = new DepartIntegralAdapter(getActivity());
//            adapter.setDatas(summaryDepartmentData);
//            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvDepartTop, false, adapter);
//            rvDepartTop.setFocusable(false);
//        } else {
//            layoutTabFour.setVisibility(View.GONE);
//        }

    }
    public void showGuideView() {
        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(tvSystemMore)
                .setAlpha(150) .setHighTargetPadding(10)
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

        builder1.addComponent(new SeeDepartStaffComponent(getActivity()));
        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(getActivity());
    }
    @OnClick({R.id.tv_system_more, R.id.btn_new_task, R.id.btn_task_panel, R.id.btn_admin_integral, R.id.layout_see_dedails, R.id.layout_departmental_disposable_income, R.id.layout_controllable_cost, R.id.layout_member_management, R.id.tv_system_name, R.id.tv_lunch_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_see_dedails:
                WebViewActivity.actionStart(getActivity(), "详情", summaryDepartmentData.getData().getDetail_url());
                break;
            case R.id.layout_departmental_disposable_income:
                DepartmentalDisposableIncomeActivity.actionStart(getActivity(), "部门可支配收入", summaryDepartmentData.getData().getDepartment().get(0).getDepartmentid());
                break;
            case R.id.layout_controllable_cost:
                ControllableCostActivity.actionStart(getActivity(), "部门可控制成本", summaryDepartmentData.getData().getDepartment().get(0).getDepartmentid(), summaryDepartmentData.getData().getDepartment().get(0).getName(),summaryDepartmentData.getData().getControl());
                break;
            case R.id.layout_member_management:
                DepartmentGetEmployeeListActivity.actionStart(getActivity(), "成员管理", summaryDepartmentData.getData().getDepartment().get(0).getDepartmentid());
                break;
            case R.id.btn_new_task:
//                WebViewActivity.actionStart(getActivity(), "新增任务", summaryDepartmentData.getData().getNewtask_url());
                break;
            case R.id.btn_task_panel:
//                WebViewActivity.actionStart(getActivity(), "任务看板", summaryDepartmentData.getData().getTasklist_url());
                break;
            case R.id.btn_admin_integral:
//                WebViewActivity.actionStart(getActivity(), "任务管理", summaryDepartmentData.getData().getTaskmanage_url());
                break;
            case R.id.tv_system_more:
//                WebViewActivity.actionStart(getActivity(), "设置机制", summaryDepartmentData.getData().getAllotmore_url());
                break;
            case R.id.tv_system_name:
                break;
            case R.id.tv_lunch_more:
//                WebViewActivity.actionStart(getActivity(), "任务看板", summaryDepartmentData.getData().getTasklist_url());
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
