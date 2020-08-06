package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.OtherDepartment;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.GridSpacingItemDecoration;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.OtherDepartmentsAdapter;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 其它部门
 */

public class OtherDepartmentsActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.rv_other_departments)
    RecyclerView rvOtherDepartments;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, OtherDepartmentsActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_other_departments;
    }

    @Override
    protected void initViews() {
        createRequest(BaseUrl.getInstence().getOtherDepartmentUrl()).otherDepartment(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<OtherDepartment>() {
            @Override
            public void onResponse(Call<OtherDepartment> call, final Response<OtherDepartment> response) {
                if (response.body().getCode() == Constant.SUCCESS) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {
                        OtherDepartmentsAdapter adapter = new OtherDepartmentsAdapter(OtherDepartmentsActivity.this);
                        adapter.setDatas(response.body());
                        RecyclerViewHelper.initRecyclerViewG(OtherDepartmentsActivity.this, rvOtherDepartments, adapter, 2);
                        rvOtherDepartments.addItemDecoration(new GridSpacingItemDecoration(2, (int) getResources().getDimension(R.dimen.x20), false)); //设置条目间距
                        adapter.setOnItemClickLitener(new OtherDepartmentsAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                OtherDepartmentsDetailsActivity.actionStart(OtherDepartmentsActivity.this, response.body().getData().get(position).getDepartmentid(), response.body().getData().get(position).getName());
                            }
                        });
                    } else {
                        Toasty.error(OtherDepartmentsActivity.this, "暂无其它部门").show();
                    }
                } else {
                    Toasty.error(OtherDepartmentsActivity.this, response.body().getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<OtherDepartment> call, Throwable t) {
                Toasty.error(OtherDepartmentsActivity.this, getString(R.string.net_error) + ":获取其它部门失败").show();
                Log.e("获取其它部门失败:", t.toString());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

}
