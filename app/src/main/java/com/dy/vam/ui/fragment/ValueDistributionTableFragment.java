package com.dy.vam.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetValueData;
import com.dy.vam.entity.Verif;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.adapter.ColumnAdapter;
import com.dy.vam.ui.adapter.MyPanelListAdapter;
import com.dy.vam.ui.widget.FullingScrollComponent;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.PanelListLayout;
import com.dy.vam.ui.widget.TimeSelectWindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

public class ValueDistributionTableFragment extends BaseFragment {
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.id_lv_content)
    ListView idLvContent;
    @BindView(R.id.id_pl_root)
    PanelListLayout idPlRoot;
    @BindView(R.id.tv_time_banner)
    TextView tvTimeBanner;
    @BindView(R.id.layout_one)
    RelativeLayout layoutOne;
    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.tv_select_all)
    TextView tvSelectAll;
    @BindView(R.id.view_guide)
    View viewGuide;
    @BindView(R.id.tv_see_unassigned)
    TextView tvSeeUnassigned;
    Unbinder unbinder;

    private List<Map<String, String>> contentList = new ArrayList<>();
    private List<String> columnList;
    private ColumnAdapter columnAdapter; //第一列Adapter

    private List<Integer> checkList = new ArrayList<>();//选中的用户id列表
    private int checkNum; // 记录选中的条目数量
    private List<String> times;
    private ArrayAdapter arrayAdapter;
    private GetValueData getValueData;
    private int year;
    private String type;

    public ValueDistributionTableFragment(String type) {
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_value_distribution_table;
    }

    @Override
    protected void initViews() {
        if (type.equals(VIEW_DISTRIBUTION)) {
            cbSelectAll.setVisibility(View.GONE);
            tvSelectAll.setVisibility(View.GONE);
            btnSetting.setVisibility(View.GONE);
        } else {
            cbSelectAll.setVisibility(View.VISIBLE);
            tvSelectAll.setVisibility(View.VISIBLE);
            btnSetting.setVisibility(View.VISIBLE);
        }
        cbSelectAll.setOnCheckedChangeListener(onCheckedAllStateListener);
        final Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);

    }

    @Override
    public void onStart() {
        super.onStart();
        getDataByTime(year);
    }

    CompoundButton.OnCheckedChangeListener onCheckedAllStateListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {        //设置全选
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                checkList.clear();
                for (int i = 0; i < columnList.size(); i++) {
                    ColumnAdapter.getIsSelected().put(i, true);
                    checkList.add(getValueData.getData().getList().get(i).getUserid());
                }
                // 数量设为list的长度
                checkNum = columnList.size();
            } else { //取消所有选择
                checkList.clear();
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < columnList.size(); i++) {
                    if (ColumnAdapter.getIsSelected().get(i)) {
                        ColumnAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    }
//                    else {      //这里是反选
//                        ColumnAdapter.getIsSelected().put(i, true);
//                        checkList.add(i);
//                        checkNum++;
//                    }
                }
            }
            columnAdapter.notifyDataSetChanged();
        }
    };

    AdapterView.OnItemClickListener onColumClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
            ColumnAdapter.Holder holder = (ColumnAdapter.Holder) view.getTag();
            // 改变CheckBox的状态
            holder.cbChecked.toggle();
            // 将CheckBox的选中状况记录下来
            ColumnAdapter.getIsSelected().put(position, holder.cbChecked.isChecked());
            // 调整选定条目
            if (holder.cbChecked.isChecked() == true) {
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


    private void getDataByTime(int year) {
        idPlRoot.removeAllViews();
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getFinancialGetValueDataUrl()).getValueData(PreferenceUtils.getInstance().getUserToken(), year).enqueue(new Callback<GetValueData>() {
            @Override
            public void onResponse(Call<GetValueData> call, Response<GetValueData> response) {
                LoadDialog.dismiss(getActivity());
                getValueData = response.body();
                if (getValueData.getCode() == Constant.SUCCESS) {
                    if (getValueData.getData() != null && getValueData.getData().getYears() != null) {
                        times = new ArrayList<>();
                        for (int i = 0; i < getValueData.getData().getYears().size(); i++) {
                            String initTime = getValueData.getData().getYears().get(i).getYear() + "";
                            if (getValueData.getData().getYears().get(i).getSelected() == 1) {
                                tvTimeBanner.setText(initTime);
                            }
                            times.add(initTime);
                        }
                        arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_question, R.id.tv_popqusetion, times);
                    }

                    if (getValueData.getData() != null && getValueData.getData().getList() != null && getValueData.getData().getList().size() > 0) {
                        idPlRoot.setVisibility(View.VISIBLE);
                        columnList = new ArrayList<>(contentList.size());
                        for (int i = 0; i < getValueData.getData().getList().size(); i++) {
                            columnList.add(getValueData.getData().getList().get(i).getUsername());//设置表头一列
                        }
                        MyPanelListAdapter adapter = new MyPanelListAdapter(getActivity(), idPlRoot, idLvContent, R.layout.item_value_distribution_table_content, getValueData);
                        adapter.setInitPosition(0);
                        adapter.setSwipeRefreshEnabled(false);
                        adapter.setRowDataList(getRowDataList());   //
                        adapter.setTitle("员工");
                        adapter.setRowColor("#F0F0F2");
                        adapter.setColumnColor("#fdfdfd");
                        adapter.setTitleColor("#F0F0F2");
                        adapter.setTitleHeight((int) getResources().getDimension(R.dimen.x100));
                        adapter.setTitleWidth((int) getResources().getDimension(R.dimen.x154));
                        idPlRoot.setAdapter(adapter, onColumClickListener);
                        columnAdapter = new ColumnAdapter(getActivity(), R.layout.item_column, columnList, type);
                        adapter.setColumnAdapter(columnAdapter);
                        if (!PreferenceUtils.getInstance().getScrollTipsShowStatus()){
                            viewGuide.post(new Runnable() {
                                @Override
                                public void run() {
                                    PreferenceUtils.getInstance().setScrollTipsShowStatus(true);
                                    showGuideView();
                                }
                            });
                        }
                    } else {
                        Toasty.error(getActivity(), "暂无待分配价值列表").show();
                        idPlRoot.setVisibility(View.GONE);
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

    /**
     * 生成一份横向表头的内容
     *
     * @return List<String>
     */
    private List<String> getRowDataList() {
        List<String> rowDataList = new ArrayList<>();
        rowDataList.add("部门");
        rowDataList.add("第一季度");
        rowDataList.add("第二季度");
        rowDataList.add("第三季度");
        rowDataList.add("第四季度");
        rowDataList.add("总额(元)");
        rowDataList.add("已分配");
        rowDataList.add("到期应分配");
        rowDataList.add("可分配余额");
        return rowDataList;
    }

    @Override
    protected void initDatas() {
    }

    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(viewGuide)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setHighTargetCorner(0)
                .setHighTargetPadding(0)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {

            }
        });

        builder.addComponent(new FullingScrollComponent(getActivity()));
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(getActivity());
    }

    @OnClick({R.id.layout_show_time_window, R.id.btn_setting, R.id.tv_see_unassigned})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_show_time_window:
                TimeSelectWindow timeSelectWindow = new TimeSelectWindow(getActivity());
                timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
                break;
            case R.id.btn_setting:
                if (checkList.size() <= 0) {
                    Toasty.warning(getActivity(), "请选择分配对象!").show();
                    return;
                }
                commit();
                break;
            case R.id.tv_see_unassigned:
//                UndistributedActivity.actionStart(getActivity(), "个人价值未分配人员", 2, year);
                break;
        }
    }

    private void commit() {
        btnSetting.setEnabled(false);
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getValueDistrubutionUrl()).valueDistrubution(PreferenceUtils.getInstance().getUserToken(), year, checkList.toString()).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                btnSetting.setEnabled(true);
                LoadDialog.dismiss(getActivity());
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(getActivity(), "分配成功").show();
                        getDataByTime(year);
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

    OnTimeSelectedListener listener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            cbSelectAll.setChecked(false);
            year = getValueData.getData().getYears().get(postion).getYear();
            tvTimeBanner.setText(times.get(postion));
            getDataByTime(year);
        }
    };

}
