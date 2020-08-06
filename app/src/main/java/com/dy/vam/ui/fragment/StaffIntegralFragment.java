package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.ControllableCostActivity;
import com.dy.vam.ui.activity.DepartmentGetEmployeeListActivity;
import com.dy.vam.ui.activity.DepartmentalDisposableIncomeActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.adapter.DepartIntegralAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2018/1/29.
 * 员工查看 积分制部门
 */

public class StaffIntegralFragment extends BaseFragment {
    @BindView(R.id.rv_depart_top)
    RecyclerView rvDepartTop;
    SummaryDepartmentData body;
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
    Unbinder unbinder;

    public StaffIntegralFragment(SummaryDepartmentData body) {
        this.body = body;
    }

    public StaffIntegralFragment() {
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_staff_depart_integral;
    }

    @Override
    protected void initViews() {
        tvAllMoney.setText("￥" + body.getData().getDistrubution());
        tvControl.setText("￥" + body.getData().getControl());
        tvDisposable.setText("￥" +body.getData().getDisposable());
        tvNetoutput.setText("￥" + body.getData().getNetoutput());
    }

    @Override
    protected void initDatas() {
//        if (body.getData().getUserdist()!=null&&body.getData().getUserdist().size()>0){
//            DepartIntegralAdapter adapter = new DepartIntegralAdapter(getActivity());
//            adapter.setDatas(body);
//            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvDepartTop, false, adapter);
//            rvDepartTop.setFocusable(false);
//        }else {
//            layoutTabFour.setVisibility(View.GONE);
//        }
    }

    @OnClick({R.id.btn_task_panel,R.id.btn_new_task,R.id.layout_see_dedails,R.id.layout_departmental_disposable_income, R.id.layout_controllable_cost, R.id.layout_member_management, R.id.tv_system_name, R.id.tv_lunch_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_see_dedails:
                WebViewActivity.actionStart(getActivity(),"详情",body.getData().getDetail_url());
                break;
            case R.id.layout_departmental_disposable_income:
                DepartmentalDisposableIncomeActivity.actionStart(getActivity(), "部门可支配收入",body.getData().getDepartment().get(0).getDepartmentid());
                break;
            case R.id.layout_controllable_cost:
                ControllableCostActivity.actionStart(getActivity(), "部门可控制成本",body.getData().getDepartment().get(0).getDepartmentid(),body.getData().getDepartment().get(0).getName(),body.getData().getControl());
                break;
            case R.id.layout_member_management:
                DepartmentGetEmployeeListActivity.actionStart(getActivity(), "成员管理", body.getData().getDepartment().get(0).getDepartmentid());
                break;
            case R.id.btn_new_task:
//                WebViewActivity.actionStart(getActivity(),"新增任务",body.getData().getNewtask_url());
                break;
            case R.id.btn_task_panel:
//                WebViewActivity.actionStart(getActivity(),"任务面板",body.getData().getTasklist_url());
                break;
            case R.id.tv_system_name:
//                WebViewActivity.actionStart(getActivity(),"新增任务",body.getData().getTasklist_url());
                break;
            case R.id.tv_lunch_more:
//                WebViewActivity.actionStart(getActivity(),"任务面板",body.getData().getTasklist_url());
                break;
        }
    }
}
