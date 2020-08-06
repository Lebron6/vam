package com.dy.vam.ui.fragment.opear;

import android.view.View;

import com.dy.vam.R;
import com.dy.vam.ui.activity.BusinessPromotionActivity;
import com.dy.vam.ui.activity.ExamineApproveActivity;
import com.dy.vam.ui.activity.ExpenseReimbursementActivity;
import com.dy.vam.ui.activity.FinancialInputActivity;
import com.dy.vam.ui.activity.ValueDistributionInputCrossActivity;
import com.dy.vam.ui.fragment.BaseFragment;

import butterknife.OnClick;

import static com.dy.vam.config.Constant.OperaConstant.FINANCIAL;
import static com.dy.vam.config.Constant.OperaConstant.VIEW_DISTRIBUTION;

/**
 * Created by James on 2018/1/15.
 * 财务总监操作
 */

public class FinancialControllerOpearFragment extends BaseFragment {

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_financial_controller_opear;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_examination_and_approval, R.id.layout_expense_reimbursement, R.id.layout_financial_input, R.id.layout_business_promotion, R.id.layout_value_distribution_input})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_examination_and_approval:
                ExamineApproveActivity.actionStart(getActivity(),"审批");
                break;
            case R.id.layout_expense_reimbursement:
                ExpenseReimbursementActivity.actionStart(getActivity(),"费用报销记录");
                break;
            case R.id.layout_financial_input:
                FinancialInputActivity.actionStart(getActivity(), "财务录入",FINANCIAL);
                break;
            case R.id.layout_business_promotion:
                BusinessPromotionActivity.actionStart(getActivity(), "业务提成");
                break;
            case R.id.layout_value_distribution_input:
                ValueDistributionInputCrossActivity.actionStart(getActivity(),"价值分配录入", VIEW_DISTRIBUTION);
                break;
        }
    }
}
