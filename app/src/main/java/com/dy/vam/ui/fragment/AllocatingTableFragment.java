package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetCommissionData;
import com.dy.vam.entity.Verif;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.AllocatingTableAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TimeSelectWindow;

import java.util.ArrayList;
import java.util.Calendar;
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

public class AllocatingTableFragment extends BaseFragment {

    @BindView(R.id.rv_allocating_table)
    RecyclerView rvAllocatingTable;
    @BindView(R.id.hv_allocting_table)
    HorizontalScrollView hvAlloctingTable;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.cb_checked_all)
    CheckBox cbCheckedAll;
    @BindView(R.id.tv_time_banner)
    TextView tvTimeBanner;
    Unbinder unbinder;
    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.layout_one)
    LinearLayout layoutOne;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.layout_check_all)
    RelativeLayout layoutCheckAll;
    @BindView(R.id.tv_quarter_banner)
    TextView tvQuarterBanner;
    @BindView(R.id.layout_show_quarter_window)
    RelativeLayout layoutShowQuarterWindow;
    @BindView(R.id.tv_see_unassigned)
    TextView tvSeeUnassigned;
    Unbinder unbinder1;
    private AllocatingTableAdapter adapter;
    private int year;   //年份
    private int season;//季度
    private GetCommissionData commissionData;
    private List<String> years;
    private List<Integer> seasons;
    private ArrayAdapter yearsAdapter;
    private ArrayAdapter seasonsAdapter;
    private String type;

    public AllocatingTableFragment(String type) {
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_allocating_table;
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
        final Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        season = Utils.getSeason(new Date());

    }

    @Override
    public void onStart() {
        super.onStart();
        getDataByTime(year, season);
    }

    private void getDataByTime(final int year, final int season) {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getFinancialGetCommissionDataUrl()).getCommissionData(PreferenceUtils.getInstance().getUserToken(), year, season).enqueue(new Callback<GetCommissionData>() {
            @Override
            public void onResponse(Call<GetCommissionData> call, Response<GetCommissionData> response) {
                LoadDialog.dismiss(getActivity());
                commissionData = response.body();
                if (commissionData.getCode() == Constant.SUCCESS) {
                    if (commissionData.getData() != null && commissionData.getData().getYears() != null && commissionData.getData().getYears().size() > 0) {
                        years = new ArrayList<>();
                        for (int i = 0; i < commissionData.getData().getYears().size(); i++) {
                            String initTime = commissionData.getData().getYears().get(i).getYear() + "年";
                            if (commissionData.getData().getYears().get(i).getSelected() == 1) {
                                tvTimeBanner.setText(initTime);
                            }
                            years.add(initTime);
                        }
                        yearsAdapter = new ArrayAdapter(getActivity(), R.layout.item_question, R.id.tv_popqusetion, years);
                        seasons = new ArrayList<Integer>();
                        for (int i = 0; i < 4; i++) {
                            seasons.add(i + 1);
                        }
                        seasonsAdapter = new ArrayAdapter(getActivity(), R.layout.item_question, R.id.tv_popqusetion, seasons);
                    }
                    if (commissionData.getData() != null) {
                        AllocatingTableFragment.this.season = commissionData.getData().getQuarter();
                        tvQuarterBanner.setText("第" + commissionData.getData().getQuarter() + "季度");
                    }
                    if (commissionData.getData() != null && commissionData.getData().getCustom() != null && commissionData.getData().getCustom().size() > 0) {
                        rvAllocatingTable.setVisibility(View.VISIBLE);
                        adapter = new AllocatingTableAdapter(getActivity(), type);
                        adapter.setOnItemClickLitener(new AllocatingTableAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                Toasty.info(getActivity(), "TTTTT").show();
                            }
                        });
                        adapter.setDatas(commissionData);
                        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvAllocatingTable, true, adapter);
                    } else {
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

    @Override
    protected void initDatas() {
    }



    @OnClick({R.id.layout_show_quarter_window, R.id.layout_show_time_window, R.id.layout_check_all, R.id.btn_setting, R.id.tv_see_unassigned})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_show_time_window:
                TimeSelectWindow timeSelectWindow = new TimeSelectWindow(getActivity());
                timeSelectWindow.showView(layoutShowTimeWindow, yearsAdapter, yearSelectedListener);
                break;
            case R.id.layout_show_quarter_window:
                TimeSelectWindow seasonSelectWindow = new TimeSelectWindow(getActivity());
                seasonSelectWindow.showView(layoutShowQuarterWindow, seasonsAdapter, seasonSelectedListener);
                break;
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
//                UndistributedActivity.actionStart(getActivity(), "提成未分配人员", 1, year);
                break;
        }
    }

    private void setting(List<Integer> checkedList) {
        btnSetting.setEnabled(false);
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getCommissionDistrubutionUrl()).commissionDistrubution(PreferenceUtils.getInstance().getUserToken(), year, checkedList.toString()).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                btnSetting.setEnabled(true);
                LoadDialog.dismiss(getActivity());
                Log.e("提成分配：", response.body().toString());
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(getActivity(), "分配成功").show();
                        getDataByTime(year, season);
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


    OnTimeSelectedListener yearSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            cbCheckedAll.setChecked(false);
            year = commissionData.getData().getYears().get(postion).getYear();
            tvTimeBanner.setText(years.get(postion));
            getDataByTime(year, season);
        }
    };
    OnTimeSelectedListener seasonSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            cbCheckedAll.setChecked(false);
            season = seasons.get(postion);
            tvQuarterBanner.setText("第" + season + "季度");
            getDataByTime(year, season);
        }
    };


}
