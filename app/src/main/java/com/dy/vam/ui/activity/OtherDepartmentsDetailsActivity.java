package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.OtherDepartmentDetailsData;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.ui.fragment.OtherDepartmentsDetailsFragment;
import com.dy.vam.ui.widget.TabNavitationLayout;
import com.dy.vam.ui.widget.TitleBarManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 其它部门
 */

public class OtherDepartmentsDetailsActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "department_id";
    public static final String DEPARTMENT_NAME = "department_name";
    @BindView(R.id.id_stickynavlayout_indicator)
    TabNavitationLayout idStickynavlayoutIndicator;
    @BindView(R.id.id_stickynavlayout_viewpager)
    ViewPager idStickynavlayoutViewpager;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_question)
    LinearLayout layoutQuestion;
    @BindView(R.id.layout_notice)
    LinearLayout layoutNotice;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.tv_proportion)
    TextView tvProportion;
    @BindView(R.id.tv_distrubution)
    TextView tvDistrubution;
    @BindView(R.id.tv_disposable)
    TextView tvDisposable;
    @BindView(R.id.tv_control)
    TextView tvControl;
    @BindView(R.id.tv_netoutput)
    TextView tvNetoutput;
    @BindView(R.id.id_stickynavlayout_topview)
    LinearLayout idStickynavlayoutTopview;
    String[] titles2 = new String[]{"部门可支配收入", "部门可控制费用"};
    List<Fragment> fragments2 = new ArrayList<>();

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar).statusBarColor(R.color.colorTheme)
                .init();
    }
    public static void actionStart(Context context, int departmentId, String name) {
        Intent intent = new Intent(context, OtherDepartmentsDetailsActivity.class);
        intent.putExtra(DETAILS_VALUES, departmentId);
        intent.putExtra(DEPARTMENT_NAME, name);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DEPARTMENT_NAME));
        manger.setTitleTextColor(OtherDepartmentsDetailsActivity.this.getResources().getColor(R.color.colorTextTheme));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_other_departments_details;
    }

    @Override
    protected void initViews() {

        createRequest(BaseUrl.getInstence().getOtherDepartmentDataUrl()).otherDepartmentData(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DETAILS_VALUES, 1)).enqueue(new Callback<OtherDepartmentDetailsData>() {
            @Override
            public void onResponse(Call<OtherDepartmentDetailsData> call, Response<OtherDepartmentDetailsData> response) {
                if (response.body().getCode() == Constant.SUCCESS) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().getDisposable_list() != null) {
                        tvControl.setText( response.body().getData().getTotal_control());
                        tvDisposable.setText( response.body().getData().getTotal_disposable());
                        tvDistrubution.setText( response.body().getData().getWorth());
//                        tvNetoutput.setText(response.body().getData().getDepartment_data().getNetoutput());
//                        tvProportion.setText("分配比例：" + response.body().getData().getDepartment_data().getProportion());
                    } else {
                        Toasty.error(OtherDepartmentsDetailsActivity.this, "部门详情信息获取失败").show();
                    }
                    if (response.body()!=null&& response.body().getData() != null ){
                        fragments2.add(new OtherDepartmentsDetailsFragment(0,response.body()));
                        fragments2.add(new OtherDepartmentsDetailsFragment(1,response.body()));
                        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager(), fragments2);
                        idStickynavlayoutViewpager.setAdapter(viewPagerAdapter2);
                        idStickynavlayoutIndicator.setViewPager(OtherDepartmentsDetailsActivity.this, titles2, idStickynavlayoutViewpager, R.color.white, R.color.white, R.color.white, R.color.colorTextGray, R.color.colorTextTheme, Utils.px2dip(OtherDepartmentsDetailsActivity.this, getResources().getDimension(R.dimen.x32)), 0, 1f, true);
                    }
                } else {
                    Toasty.error(OtherDepartmentsDetailsActivity.this, response.body().getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<OtherDepartmentDetailsData> call, Throwable t) {
                Toasty.error(OtherDepartmentsDetailsActivity.this, getString(R.string.net_error) + ":获取其它部门详情失败").show();
                Log.e("获取其它部门失败:", t.toString());
            }
        });

    }

    @Override
    protected void initDatas() {
    }
}
