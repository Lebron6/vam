package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.DepartmentUsers;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.DepartStaffListAdapter;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/30.
 * 部门成员列表/部门首页进入
 */

public class DepartmentGetEmployeeListActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    public static final String DEPARTMENT_ID = "department_id";
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
    public static void actionStart(Context context, String title,int departmentId) {
        Intent intent = new Intent(context, DepartmentGetEmployeeListActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(DEPARTMENT_ID, departmentId);
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
        return R.layout.activity_depart_staff_list;
    }

    @Override
    protected void initViews() {
        adapter = new DepartStaffListAdapter(this);
    }

    @Override
    protected void initDatas() {

        createRequest(BaseUrl.getInstence().getEmployeeListUrl()).getDepartmentUsers(PreferenceUtils.getInstance().getUserToken(),getIntent().getIntExtra(DEPARTMENT_ID,1)).enqueue(new Callback<DepartmentUsers>() {
            @Override
            public void onResponse(Call<DepartmentUsers> call, Response<DepartmentUsers> response) {
                departmentUsers = response.body();
                switch (departmentUsers.getCode()) {
                    case 0:
                        //初始化部门成员列表数据
                        if (departmentUsers.getData()!=null){
                            adapter.setDatas(departmentUsers);
                            RecyclerViewHelper.initRecyclerViewV(DepartmentGetEmployeeListActivity.this, rvStaff, false, adapter);
                        }else{
                            Toasty.error(DepartmentGetEmployeeListActivity.this, "该部门暂无更多成员").show();
                        }
                        break;
                    default:
                        Toasty.error(DepartmentGetEmployeeListActivity.this, departmentUsers.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DepartmentUsers> call, Throwable t) {

            }
        });

        adapter.setOnItemClickLitener(new DepartStaffListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                DepartmentGetEmployeeDetailsActivity.actionStart(DepartmentGetEmployeeListActivity.this, departmentUsers.getData().get(position).getUserid());
            }
        });
    }

}
