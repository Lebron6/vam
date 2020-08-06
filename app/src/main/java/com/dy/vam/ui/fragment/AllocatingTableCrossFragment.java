package com.dy.vam.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetCommissionData;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.activity.UndistributedActivity;
import com.dy.vam.ui.adapter.AllocatingTableCrossAdapter;
import com.dy.vam.ui.widget.LoadDialog;

import java.util.ArrayList;
import java.util.Date;
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
 * Created by James on 2018/1/23.
 * 提成分配表
 */

public class AllocatingTableCrossFragment extends BaseFragment {

    @BindView(R.id.rv_allocating_table)
    RecyclerView rvAllocatingTable;
    @BindView(R.id.hv_allocting_table)
    HorizontalScrollView hvAlloctingTable;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.cb_checked_all)
    CheckBox cbCheckedAll;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.layout_check_all)
    RelativeLayout layoutCheckAll;
    @BindView(R.id.tv_see_unassigned)
    TextView tvSeeUnassigned;
    @BindView(R.id.layout_vis)
    RelativeLayout layoutVis;
    Unbinder unbinder;
    private AllocatingTableCrossAdapter adapter;
    private int season;//季度
    private GetCommissionData commissionData;
    private String type;
    public static final String SEASON_KEY = "SEASON_KEY";
    private MyBroadcastReceiver myBroadcastReceiver;

    public AllocatingTableCrossFragment(String type) {
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_allocating_cross_table;
    }

    @Override
    protected void initViews() {
        if (type.equals(VIEW_DISTRIBUTION)) {
            cbCheckedAll.setVisibility(View.GONE);
            btnSetting.setVisibility(View.GONE);
        } else {
            cbCheckedAll.setVisibility(View.VISIBLE);
            btnSetting.setVisibility(View.VISIBLE);
        }
        season = Utils.getSeason(new Date());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.GET_DATA_BY_SEAON);
        myBroadcastReceiver = new MyBroadcastReceiver();
        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void initDatas() {
        getDataByTime(season);
    }

    public static void sendBroadCast(Context context, int season) {
        Intent intent = new Intent(Constant.GET_DATA_BY_SEAON);
        intent.putExtra(SEASON_KEY, season);
        context.sendBroadcast(intent); // 发送广播
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.GET_DATA_BY_SEAON)) {
                cbCheckedAll.setChecked(false);
                season=intent.getIntExtra(SEASON_KEY, 1);
                getDataByTime(season);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }

    private void getDataByTime(final int season) {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getFinancialGetCommissionDataUrl()).getCommissionData(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), season).enqueue(new Callback<GetCommissionData>() {
            @Override
            public void onResponse(Call<GetCommissionData> call, Response<GetCommissionData> response) {
                LoadDialog.dismiss(getActivity());
                commissionData = response.body();
                if (commissionData.getCode() == Constant.SUCCESS) {
                    if (commissionData.getData() != null && commissionData.getData().getCustom() != null && commissionData.getData().getCustom().size() > 0) {
                        layoutVis.setVisibility(View.VISIBLE);
                        rvAllocatingTable.setVisibility(View.VISIBLE);
                        adapter = new AllocatingTableCrossAdapter(getActivity(), type);
                        adapter.setOnItemClickLitener(new AllocatingTableCrossAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                Toasty.info(getActivity(), "TTTTT").show();
                            }
                        });
                        adapter.setDatas(commissionData);
                        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvAllocatingTable, false, adapter);
                    } else {
                        layoutVis.setVisibility(View.GONE);
                        Toasty.error(getActivity(), "暂无待分配提成列表").show();
                        rvAllocatingTable.setVisibility(View.GONE);
                    }
                } else {
                    Toasty.error(getActivity(), commissionData.getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<GetCommissionData> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                Log.e("获取提成分配表失败", t.toString());
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取提成分配表失败!").show();
            }
        });
    }

    @OnClick({R.id.layout_check_all, R.id.btn_setting, R.id.tv_see_unassigned})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_check_all:
                if (commissionData.getData().getCustom().size() <= 0) {
                    Toasty.error(getActivity(), "暂无待分配对象可供选择").show();
                    return;
                }
                // 接收到点击事件后判断checkbox 是否处于选中状态，如未被选中，先设置选中在设置数据，更新adapter等操作、
                if (cbCheckedAll.isChecked() == false) {
                    cbCheckedAll.setChecked(true);
                    // 遍历list的长度，将MyAdapter中的map值全部设为true
                    for (int i = 0; i < commissionData.getData().getCustom().size(); i++) {
                        for (int j = 0; j < commissionData.getData().getCustom().get(i).getUsers().size(); j++) {
                            if (commissionData.getData().getCustom().get(i).getUsers().get(j).type == commissionData.getData().getCustom().get(i).getUsers().get(j).TYPE_NOCHECKED) {
                                commissionData.getData().getCustom().get(i).getUsers().get(j).type = commissionData.getData().getCustom().get(i).getUsers().get(j).TYPE_CHECKED;
                            }
                        }
                    }
                } else { //取消所有选择
                    cbCheckedAll.setChecked(false);
                    // 遍历list的长度，将已选的设为未选，未选的设为已选
                    for (int i = 0; i < commissionData.getData().getCustom().size(); i++) {
                        for (int j = 0; j < commissionData.getData().getCustom().get(i).getUsers().size(); j++) {
                            if (commissionData.getData().getCustom().get(i).getUsers().get(j).type == commissionData.getData().getCustom().get(i).getUsers().get(j).TYPE_CHECKED) {
                                commissionData.getData().getCustom().get(i).getUsers().get(j).type = commissionData.getData().getCustom().get(i).getUsers().get(j).TYPE_NOCHECKED;
                            }

                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_setting:
                List<Integer> checkedList = new ArrayList<>();
                for (int i = 0; i < commissionData.getData().getCustom().size(); i++) {
                    for (int j = 0; j < commissionData.getData().getCustom().get(i).getUsers().size(); j++) {
                        if (commissionData.getData().getCustom().get(i).getUsers().get(j).type == commissionData.getData().getCustom().get(i).getUsers().get(j).TYPE_CHECKED) {
                            checkedList.add(commissionData.getData().getCustom().get(i).getUsers().get(j).getCuid());
                        }
                    }
                }
                if (checkedList != null && checkedList.size() > 0) {
                    setting(checkedList);
                } else {
                    Toasty.error(getActivity(), "未选择待分配对象").show();
                }
                break;
            case R.id.tv_see_unassigned:
                UndistributedActivity.actionStart(getActivity(), "提成未分配人员", 1);
                break;
        }
    }

    private void setting(List<Integer> checkedList) {
        btnSetting.setEnabled(false);
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getCommissionDistrubutionUrl()).commissionDistrubution(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), checkedList.toString()).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                btnSetting.setEnabled(true);
                LoadDialog.dismiss(getActivity());
                Log.e("提成分配：", response.body().toString());
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(getActivity(), "分配成功").show();
                        getDataByTime(season);
                        cbCheckedAll.setChecked(false);
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
