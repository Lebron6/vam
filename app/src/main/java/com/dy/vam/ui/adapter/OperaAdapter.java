package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.OperaList;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.GridSpacingItemDecoration;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.AddingNewStaffActivity;
import com.dy.vam.ui.activity.BusinessAllocationsActivity;
import com.dy.vam.ui.activity.BusinessPromotionActivity;
import com.dy.vam.ui.activity.ExamineApproveActivity;
import com.dy.vam.ui.activity.ExpenseReimbursementActivity;
import com.dy.vam.ui.activity.FinancialInputActivity;
import com.dy.vam.ui.activity.OrganizationalStructureActivity;
import com.dy.vam.ui.activity.OtherDepartmentsActivity;
import com.dy.vam.ui.activity.ValueDistributionInputCrossActivity;
import com.dy.vam.ui.activity.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dy.vam.config.Constant.OperaConstant.DISTRIBUTION;
import static com.dy.vam.config.Constant.OperaConstant.FINANCIAL;
import static com.dy.vam.config.Constant.OperaConstant.MANAGE_ORGANIZATION;
import static com.dy.vam.config.Constant.OperaConstant.VIEW_DISTRIBUTION;
import static com.dy.vam.config.Constant.OperaConstant.VIEW_FINANCIAL;
import static com.dy.vam.config.Constant.OperaConstant.VIEW_ORGANIZATION;

public class OperaAdapter extends RecyclerView.Adapter {


    private Context context;
    private OperaList operaList;

    public OperaAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OperaList operaList) {
        this.operaList = operaList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        switch (position) {
            case 0:
                if (operaList.getData().getOperation() != null) {
                    viewHolder.tvTabTitle.setText("通用");
                    OperaOperationAdapter adapter = new OperaOperationAdapter(context);
                    adapter.setDatas(operaList.getData());
                    adapter.setOnItemClickLitener(new adapterItemClickListener());
                    RecyclerViewHelper.initRecyclerViewG(context, viewHolder.rvTabOpera, adapter, 2);
                    viewHolder.rvTabOpera.addItemDecoration(new GridSpacingItemDecoration(2, (int) context.getResources().getDimension(R.dimen.x20), false)); //设置条目间距
                }
                break;
            case 1:
                if (operaList.getData().getManage() != null) {
                    viewHolder.tvTabTitle.setText("管理");
                    OperaMangeAdapter mangeAdapter = new OperaMangeAdapter(context);
                    mangeAdapter.setDatas(operaList.getData());
                    mangeAdapter.setOnItemClickLitener(new adapterItemClickListener());
                    RecyclerViewHelper.initRecyclerViewG(context, viewHolder.rvTabOpera, mangeAdapter, 3);
                    viewHolder.rvTabOpera.addItemDecoration(new GridSpacingItemDecoration(3, (int) context.getResources().getDimension(R.dimen.x20), false)); //设置条目间距
                }
                break;
            default:
                if (operaList.getData().getView() != null) {
                    viewHolder.tvTabTitle.setText("查看");
                    OperaViewAdapter otherAdapter = new OperaViewAdapter(context);
                    otherAdapter.setDatas(operaList.getData());
                    otherAdapter.setOnItemClickLitener(new adapterItemClickListener());
                    RecyclerViewHelper.initRecyclerViewG(context, viewHolder.rvTabOpera, otherAdapter, 3);
                    viewHolder.rvTabOpera.addItemDecoration(new GridSpacingItemDecoration(3, (int) context.getResources().getDimension(R.dimen.x20), false)); //设置条目间距
                }
                break;
        }
    }

    class adapterItemClickListener implements OperaOperationAdapter.OnOperaTionItemClickLitener, OperaMangeAdapter.OnMangeItemClickLitener, OperaViewAdapter.OnViewItemClickLitener {

        @Override
        public void onViewItemClick(View view, int position) {
            produceActivityActionStart(context, operaList.getData().getView().get(position).getTab(),position);
        }

        @Override
        public void onMangeItemClick(View view, int position) {
            produceActivityActionStart(context, operaList.getData().getManage().get(position).getTab(),position);
        }

        @Override
        public void onOperaTionItemClick(View view, int position) {
            produceActivityActionStart(context, operaList.getData().getOperation().get(position).getTab(),position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tab_title)
        TextView tvTabTitle;
        @BindView(R.id.rv_tab_opera)
        RecyclerView rvTabOpera;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void produceActivityActionStart(Context context, String tab, int position) {
        if (tab.equals(Constant.OperaConstant.REIMBURSEMENT)) {
            ExpenseReimbursementActivity.actionStart(context,"费用报销记录");
        } else if (tab.equals(Constant.OperaConstant.EXAMINE)) {
            ExamineApproveActivity.actionStart(context, "审批");
        } else if (tab.equals(FINANCIAL)) {
            FinancialInputActivity.actionStart(context, "财务录入",FINANCIAL);
        } else if (tab.equals(Constant.OperaConstant.OTHER_DEPARTMENTS)) {
            OtherDepartmentsActivity.actionStart(context, "其它部门");
        } else if (tab.equals(Constant.OperaConstant.PROMOTION)) {
            BusinessPromotionActivity.actionStart(context, "业务提成");
        } else if (tab.equals(DISTRIBUTION)) {
            ValueDistributionInputCrossActivity.actionStart(context, "价值分配录入",DISTRIBUTION);
        } else if (tab.equals(Constant.OperaConstant.PROPORTIONS)) {
            BusinessAllocationsActivity.actionStart(context, "业务提成设置");
        } else if (tab.equals(MANAGE_ORGANIZATION)) {
            OrganizationalStructureActivity.actionStart(context, "组织架构",MANAGE_ORGANIZATION);
        } else if (tab.equals(Constant.OperaConstant.MANAGE_ADDEMPLOYEE)) {
            AddingNewStaffActivity.actionStart(context, "添加新员工");
        } else if (tab.equals(Constant.OperaConstant.MANAGE_DEPARTMENT)) {
            WebViewActivity.actionStart(context,"部门分配设置",operaList.getData().getManage().get(position).getUrl());
        } else if (tab.equals(Constant.OperaConstant.MANAGE_REIMBURSEMENT)) {
            WebViewActivity.actionStart(context,"报销管理",operaList.getData().getManage().get(position).getUrl());
        } else if (tab.equals(Constant.OperaConstant.MANAGE_AUTHORITY)) {
            WebViewActivity.actionStart(context,"权限分配",operaList.getData().getManage().get(position).getUrl());
        } else if (tab.equals(Constant.OperaConstant.MANAGE_PARTITION)) {
            WebViewActivity.actionStart(context,"年度分配系数",operaList.getData().getManage().get(position).getUrl());
        } else if (tab.equals(VIEW_FINANCIAL)) {
           FinancialInputActivity.actionStart(context,"查看财务数据",VIEW_FINANCIAL);
        } else if (tab.equals(VIEW_DISTRIBUTION)) {
            ValueDistributionInputCrossActivity.actionStart(context, "查看价值分配",VIEW_DISTRIBUTION);
        } else if (tab.equals(VIEW_ORGANIZATION)) {
            OrganizationalStructureActivity.actionStart(context, "查看组织架构",VIEW_ORGANIZATION);
        } else if (tab.equals(Constant.OperaConstant.VIEW_DEPARTMENTS)) {
            WebViewActivity.actionStart(context,"部门分配比例",operaList.getData().getView().get(position).getUrl());
        } else if (tab.equals(Constant.OperaConstant.VIEW_REIMBURSEMENT)) {
            WebViewActivity.actionStart(context,"报销流程",operaList.getData().getView().get(position).getUrl());
        } else if (tab.equals(Constant.OperaConstant.VIEW_AUTHORITY)) {
            WebViewActivity.actionStart(context,"权限分配",operaList.getData().getView().get(position).getUrl());
        } else if (tab.equals(Constant.OperaConstant.VIEW_PARTITION)) {
            WebViewActivity.actionStart(context,"年度分配系数",operaList.getData().getView().get(position).getUrl());
        }
    }
}