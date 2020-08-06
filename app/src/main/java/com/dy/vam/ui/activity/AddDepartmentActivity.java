package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/30.
 * 添加部门
 */

public class AddDepartmentActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.et_depart_name)
    EditText etDepartName;
    @BindView(R.id.btn_add_depart)
    Button btnAddDepart;

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, AddDepartmentActivity.class);
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
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_department;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick(R.id.btn_add_depart)
    public void onViewClicked() {
        String departmentName=etDepartName.getText().toString();
        if (TextUtils.isEmpty(departmentName)){
            Toasty.error(AddDepartmentActivity.this,"请输入部门名称").show();
            return;
        }
        addDepartment(departmentName);
    }


    private void addDepartment(String departmentName) {
        btnAddDepart.setEnabled(false);
        LoadDialog.show(AddDepartmentActivity.this);
        createRequest(BaseUrl.getInstence().getAddDepartUrl()).addDepartment(PreferenceUtils.getInstance().getUserToken(),departmentName).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(AddDepartmentActivity.this);
                btnAddDepart.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(AddDepartmentActivity.this, "部门添加成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(AddDepartmentActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnAddDepart.setEnabled(true);
                LoadDialog.dismiss(AddDepartmentActivity.this);
                Toasty.warning(AddDepartmentActivity.this, getString(R.string.net_error)).show();
            }
        });
    }
}
