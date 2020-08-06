package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetValueData;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.UndistributedActivity;
import com.dy.vam.ui.adapter.ValueDistributionTableGrossAdapter;
import com.dy.vam.ui.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_DISTRIBUTION;

/**
 * Created by James on 2018/1/9.
 * 价值分配表
 */

public class ValueDistributionTableCrossFragment extends BaseFragment {

    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.layout_title_type)
    LinearLayout layoutTitleType;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.tv_see_unassigned)
    TextView tvSeeUnassigned;
    Unbinder unbinder;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_cb_vis)
    RelativeLayout layoutCbVis;
    Unbinder unbinder1;
    private GetValueData getValueData;
    private String type;
    private List<Integer> checkList = new ArrayList<>();//选中的用户id列表
    private int checkNum;
    private ValueDistributionTableGrossAdapter adapter;

    public ValueDistributionTableCrossFragment(String type) {
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_value_distribution_cross_table;
    }

    @Override
    protected void initViews() {
        if (type.equals(VIEW_DISTRIBUTION)) {
            layoutCbVis.setVisibility(View.GONE);
            btnSetting.setVisibility(View.GONE);
        } else {
            layoutCbVis.setVisibility(View.VISIBLE);
            btnSetting.setVisibility(View.VISIBLE);
        }
        cbSelectAll.setOnCheckedChangeListener(onCheckedAllStateListener);
        adapter = new ValueDistributionTableGrossAdapter(getActivity(), type);
        adapter.setOnItemClickLitener(listener);
    }

    CompoundButton.OnCheckedChangeListener onCheckedAllStateListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {        //设置全选
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                checkList.clear();
                for (int i = 0; i < getValueData.getData().getList().size(); i++) {
                    ValueDistributionTableGrossAdapter.getIsSelected().put(i, true);
                    checkList.add(getValueData.getData().getList().get(i).getUserid());
                }
                // 数量设为list的长度
                checkNum = getValueData.getData().getList().size();
            } else { //取消所有选择
                checkList.clear();
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < getValueData.getData().getList().size(); i++) {
                    if (ValueDistributionTableGrossAdapter.getIsSelected().get(i)) {
                        ValueDistributionTableGrossAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    }
//                    else {      //这里是反选
//                        ColumnAdapter.getIsSelected().put(i, true);
//                        checkList.add(i);
//                        checkNum++;
//                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    };

    ValueDistributionTableGrossAdapter.OnItemClickLitener listener = new ValueDistributionTableGrossAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, int position) {
            // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
            ValueDistributionTableGrossAdapter.ViewHolder holder = (ValueDistributionTableGrossAdapter.ViewHolder) rvList.getChildViewHolder(view);
            // 改变CheckBox的状态
            holder.cbSelect.toggle();
            // 将CheckBox的选中状况记录下来
            ValueDistributionTableGrossAdapter.getIsSelected().put(position, holder.cbSelect.isChecked());
            // 调整选定条目
            if (holder.cbSelect.isChecked() == true) {
                checkList.add(getValueData.getData().getList().get(position).getUserid());
                checkNum++;
            } else {
                for (int i = 0; i < checkList.size(); i++) {
                    if (checkList.get(i) == getValueData.getData().getList().get(position).getUserid()) {
                        checkList.remove(i);
                    }
                }
                checkNum--;
            }
        }
    };

    @Override
    protected void initDatas() {

    }

    @Override
    public void onStart() {
        super.onStart();
        getDataByTime();
    }


    private void getDataByTime() {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getFinancialGetValueDataUrl()).getValueData(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<GetValueData>() {
            @Override
            public void onResponse(Call<GetValueData> call, Response<GetValueData> response) {
                LoadDialog.dismiss(getActivity());
                getValueData = response.body();
                if (getValueData.getCode() == Constant.SUCCESS) {
                    if (getValueData.getData() != null && getValueData.getData().getList() != null && getValueData.getData().getList().size() > 0) {
                        adapter.setDatas(getValueData);
                        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvList, true, adapter);
                    } else {
                        Toasty.error(getActivity(), "暂无待分配价值列表").show();
                    }
                } else {
                    Toasty.error(getActivity(), getValueData.getMsg()).show();
                }

            }

            @Override
            public void onFailure(Call<GetValueData> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                Log.e("获取价值分配表失败", t.toString());
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取价值分配表失败!").show();
            }
        });

    }


    @OnClick({R.id.btn_setting, R.id.tv_see_unassigned})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                if (checkList.size() <= 0) {
                    Toasty.warning(getActivity(), "请选择分配对象!").show();
                    return;
                }
                commit();
                break;
            case R.id.tv_see_unassigned:
                UndistributedActivity.actionStart(getActivity(), "个人价值未分配人员", 2);
                break;
        }
    }

    private void commit() {
        btnSetting.setEnabled(false);
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getValueDistrubutionUrl()).valueDistrubution(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), checkList.toString()).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                btnSetting.setEnabled(true);
                LoadDialog.dismiss(getActivity());
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(getActivity(), "分配成功").show();
                        getDataByTime();
                        cbSelectAll.setChecked(false);
                        break;
                    default:
                        Toasty.error(getActivity(), response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                btnSetting.setEnabled(true);
                Toasty.warning(getActivity(), getString(R.string.net_error) + ":分配失败!").show();
                Log.e("分配失败", t.toString());
            }
        });
    }
}
