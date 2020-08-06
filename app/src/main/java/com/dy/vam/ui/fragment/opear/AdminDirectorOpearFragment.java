package com.dy.vam.ui.fragment.opear;

import android.view.View;

import com.dy.vam.R;
import com.dy.vam.ui.activity.AddingNewStaffActivity;
import com.dy.vam.ui.activity.ExamineApproveActivity;
import com.dy.vam.ui.activity.OrganizationalStructureActivity;
import com.dy.vam.ui.activity.OtherDepartmentsActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.fragment.BaseFragment;

import butterknife.OnClick;

import static com.dy.vam.config.Constant.OperaConstant.MANAGE_ORGANIZATION;

/**
 * Created by James on 2018/1/15.
 * 行政总监操作
 */

public class AdminDirectorOpearFragment extends BaseFragment {


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_admin_director_opear;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_examination_and_approval, R.id.layout_expense_reimbursement, R.id.layout_other_departments, R.id.layout_organizational_structure, R.id.layout_new_employee, R.id.layout_departmental_allocation, R.id.layout_reimburse_management, R.id.layout_permissions_allocation, R.id.layout_annual_distribution_coefficient})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_examination_and_approval:
                ExamineApproveActivity.actionStart(getActivity(),"审批");
                break;
            case R.id.layout_expense_reimbursement:
                break;
            case R.id.layout_other_departments:
                OtherDepartmentsActivity.actionStart(getActivity(),"其它部门");
                break;
            case R.id.layout_organizational_structure:
                OrganizationalStructureActivity.actionStart(getActivity(),"大洋", MANAGE_ORGANIZATION);
                break;
            case R.id.layout_new_employee:
                AddingNewStaffActivity.actionStart(getActivity(),"添加新成员");
                break;
            case R.id.layout_departmental_allocation:
                WebViewActivity.actionStart(getActivity(),"部门分配设置",null);
                break;
            case R.id.layout_reimburse_management:
                break;
            case R.id.layout_permissions_allocation:
                break;
            case R.id.layout_annual_distribution_coefficient:
                break;
        }
    }
}
