package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.DepartmentUsers;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.DepartStaffListAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/30.
 * 部门成员列表/部门成员管理
 */

public class DepartmentStaffListActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    public static final String DEPARTMENT_ID = "department_id";
    public static final String TYPE = "type";
    @BindView(R.id.rv_staff)
    RecyclerView rvStaff;
    private DepartStaffListAdapter adapter;
    private DepartmentUsers departmentUsers;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String title, int departmentId,String type) {
        Intent intent = new Intent(context, DepartmentStaffListActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(DEPARTMENT_ID, departmentId);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
        loadData();
    }

    private void loadData() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getDepartmentUserUrl()).getDepartmentUsers(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DEPARTMENT_ID, 1)).enqueue(new Callback<DepartmentUsers>() {
            @Override
            public void onResponse(Call<DepartmentUsers> call, Response<DepartmentUsers> response) {
                LoadDialog.dismiss(DepartmentStaffListActivity.this);
                departmentUsers = response.body();
                switch (departmentUsers.getCode()) {
                    case 0:
                        //初始化部门成员列表数据
                        if (departmentUsers.getData() != null) {
                            adapter.setDatas(departmentUsers);
                            RecyclerViewHelper.initRecyclerViewV(DepartmentStaffListActivity.this, rvStaff, false, adapter);
                        } else {
                            Toasty.error(DepartmentStaffListActivity.this, "该部门暂无更多成员").show();
                        }
                        break;
                    default:
                        Toasty.error(DepartmentStaffListActivity.this, departmentUsers.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DepartmentUsers> call, Throwable t) {
                LoadDialog.dismiss(DepartmentStaffListActivity.this);
                Log.e("获取部门成员失败", t.toString());
                Toasty.error(DepartmentStaffListActivity.this, getString(R.string.net_error) + ":获取部门成员失败!").show();
            }
        });

        adapter.setOnItemClickLitener(new DepartStaffListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                StaffDetailsActivity.actionStart(DepartmentStaffListActivity.this, departmentUsers.getData().get(position).getUserid(),getIntent().getStringExtra(TYPE));
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_depart_staff_list;
    }

    @Override
    protected void initViews() {
        adapter = new DepartStaffListAdapter(this);
    }

    @Override
    protected void initDatas() {

    }

}
