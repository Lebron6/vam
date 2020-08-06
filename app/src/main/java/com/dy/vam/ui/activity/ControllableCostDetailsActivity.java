package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.ControllableListChildBean;
import com.dy.vam.entity.GetDepartmentControlDetail;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.DeletePageAdapter;
import com.dy.vam.ui.fragment.CCDetailsForMonthFragment;
import com.dy.vam.ui.widget.NavitationScrollLayout;
import com.dy.vam.ui.widget.TitleBarManger;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by James on 2018/1/30.
 * 可控制成本详情
 */

public class ControllableCostDetailsActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String YEAR = "year";
    public static final String DEPARTMENT_ID = "departmentId";
    public static final String TYPE = "type";
    public static final String DEPARTMENT_NAME = "departmentName";

    @BindView(R.id.layout_show_time_window)
    RelativeLayout layoutShowTimeWindow;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tab_cc_details)
    NavitationScrollLayout tabCcDetails;
    @BindView(R.id.vp_cc_details)
    ViewPager vpCcDetails;
    private List<String> times;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
                .statusBarColor(R.color.colorTheme).init();
    }

    public static void actionStart(Context context, String title, int type, int departmentId,String departmentName) {
        Intent intent = new Intent(context, ControllableCostDetailsActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(TYPE, type);
        intent.putExtra(DEPARTMENT_ID, departmentId);
        intent.putExtra(DEPARTMENT_NAME, departmentName);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        loadDatasByTime();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_controllable_cost_details;
    }

    @Override
    protected void initViews() {

    }

    private void loadDatasByTime() {
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentControlDetailsUrl()).summaryGetDepartmentControlDetails(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear(), getIntent().getIntExtra(TYPE, 1), getIntent().getIntExtra(DEPARTMENT_ID, 1)).enqueue(new Callback<GetDepartmentControlDetail>() {
            @Override
            public void onResponse(Call<GetDepartmentControlDetail> call, Response<GetDepartmentControlDetail> response) {

                GetDepartmentControlDetail controlDetail = response.body();
                if (controlDetail.getCode() == Constant.SUCCESS) {

                    if (controlDetail.getData() != null && controlDetail.getData().getTitle() != null) {
                        TitleBarManger manger = TitleBarManger.getInsetance();
                        manger.setContext(ControllableCostDetailsActivity.this);
                        manger.setTitle(controlDetail.getData().getTitle());
                        manger.setBack();
                    } else {
                        Toasty.error(ControllableCostDetailsActivity.this, "获取标题失败").show();
                    }
                    if (controlDetail.getData() != null && controlDetail.getData().getMonth() != null && controlDetail.getData().getDetails() != null) {
                        getMonthFromJsonObject(controlDetail.getData());
                    } else {
                        Toasty.error(ControllableCostDetailsActivity.this, "获取详细信息失败").show();
                    }

                } else {
                    Toasty.error(ControllableCostDetailsActivity.this, controlDetail.getMsg()).show();
                }

            }

            @Override
            public void onFailure(Call<GetDepartmentControlDetail> call, Throwable t) {
                Toasty.warning(ControllableCostDetailsActivity.this, getString(R.string.net_error) + ":获取部门可控制成本详情数据失败").show();
                Log.e("获取部门可控制成本详情数据：", t.toString());
            }
        });
    }

    private void getMonthFromJsonObject(GetDepartmentControlDetail.DataBean data) {
        List<String> moneyOfMonth = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        JsonObject monthJsonObject = new JsonParser().parse(new Gson().toJson(data)).getAsJsonObject().get("month").getAsJsonObject();
        for (int i = 1; i < 13; i++) {
            moneyOfMonth.add(monthJsonObject.get(String.valueOf(i)).getAsString());
            String title = PreferenceUtils.getInstance().getDefaultYear() + "/" + i;
            titles.add(title);
        }
        List<List<ControllableListChildBean>> controllableListChildBeanLists = new ArrayList<>();
        JsonObject detailsJsonObject = new JsonParser().parse(new Gson().toJson(data)).getAsJsonObject().get("details").getAsJsonObject();
        for (int i = 1; i < 13; i++) {
            List<ControllableListChildBean> controllableListChildBeanList = new ArrayList<>();
            JsonArray asJsonArray = detailsJsonObject.get(String.valueOf(i)).getAsJsonArray();
            for (int j = 0; j < asJsonArray.size(); j++) {
                ControllableListChildBean controllableListChildBean = new ControllableListChildBean();
                if (asJsonArray.get(j).getAsJsonObject().get("explain") != null){
                    controllableListChildBean.setExplain(asJsonArray.get(j).getAsJsonObject().get("explain").getAsString() == null ? "" :asJsonArray.get(j).getAsJsonObject().get("explain").getAsString());

                }
                controllableListChildBean.setMoney(asJsonArray.get(j).getAsJsonObject().get("money").getAsString() == null ? "" :asJsonArray.get(j).getAsJsonObject().get("money").getAsString());
                controllableListChildBean.setName(asJsonArray.get(j).getAsJsonObject().get("name").getAsString()== null ? "" :asJsonArray.get(j).getAsJsonObject().get("name").getAsString());
                controllableListChildBeanList.add(controllableListChildBean);
            }
            controllableListChildBeanLists.add(controllableListChildBeanList);
        }
        List<Fragment> fragments2 = new ArrayList<>();
        fragments2.clear();
        for (int i = 0; i < 12; i++) {
            CCDetailsForMonthFragment fragment = new CCDetailsForMonthFragment(moneyOfMonth.get(i),controllableListChildBeanLists.get(i),getIntent().getStringExtra(DEPARTMENT_NAME));
            fragments2.add(fragment);
        }
        vpCcDetails.removeAllViewsInLayout();//removeAllViews();//赋值之前先将Adapter中的
        DeletePageAdapter viewPagerAdapter2 = new DeletePageAdapter(ControllableCostDetailsActivity.this,getSupportFragmentManager(), fragments2);
        vpCcDetails.setAdapter(viewPagerAdapter2);
        tabCcDetails.setViewPager(this, titles, vpCcDetails, R.color.colorWhite, R.color.colorTextTheme, Utils.px2dip(this, getResources().getDimension(R.dimen.x28)), Utils.px2dip(this, getResources().getDimension(R.dimen.x28)), 12, true, R.color.colorWhite, 0f, 15f, 15f, Utils.px2dip(this, getResources().getDimension(R.dimen.x160)));
        tabCcDetails.setBgLine(this, 1, R.color.colorAccent);
        tabCcDetails.setNavLine(this, 3, R.color.colorTheme);
    }

    @Override
    protected void initDatas() {


    }


    @OnClick(R.id.layout_show_time_window)
    public void onViewClicked() {
//        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(ControllableCostDetailsActivity.this);
//        timeSelectWindow.showView(layoutShowTimeWindow, arrayAdapter, listener);
    }



}
