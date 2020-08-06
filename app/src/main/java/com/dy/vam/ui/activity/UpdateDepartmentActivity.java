package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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
 * 删除部门
 */

public class UpdateDepartmentActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    public static final String DEPARTMENT_ID = "id";
    public static final String DEPARTMENT_NAME = "name";
    @BindView(R.id.et_depart_name)
    EditText etDepartName;
    @BindView(R.id.btn_updata_depart)
    Button btnUpdateDepart;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String title, int departmentId, String departmentName) {
        Intent intent = new Intent(context, UpdateDepartmentActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(DEPARTMENT_ID, departmentId);
        intent.putExtra(DEPARTMENT_NAME, departmentName);
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
        return R.layout.activity_updata_department;
    }

    @Override
    protected void initViews() {
        etDepartName.setText(getIntent().getStringExtra(DEPARTMENT_NAME));
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_updata_depart)
    public void onViewClicked() {
        String departName = etDepartName.getText().toString();
        if (TextUtils.isEmpty(departName)) {
            Toasty.error(UpdateDepartmentActivity.this, "请输入您需要修改的部门名称").show();
            return;
        }
        btnUpdateDepart.setEnabled(false);
        LoadDialog.show(UpdateDepartmentActivity.this);
            createRequest(BaseUrl.getInstence().getEditDepartNameUrl()).editDepartmentName(PreferenceUtils.getInstance().getUserToken(), departName,getIntent().getIntExtra(DEPARTMENT_ID,1)).enqueue(callback);
    }

    Callback<Verif> callback = new Callback<Verif>() {
        @Override
        public void onResponse(Call<Verif> call, Response<Verif> response) {
            LoadDialog.dismiss(UpdateDepartmentActivity.this);
            btnUpdateDepart.setEnabled(true);
            switch (response.body().getCode()) {
                case Constant.SUCCESS:
                    Toasty.info(UpdateDepartmentActivity.this, "修改成功").show();
                    finish();
                    break;
                default:
                    Toasty.error(UpdateDepartmentActivity.this, response.body().getMsg()).show();
                    break;
            }
        }

        @Override
        public void onFailure(Call<Verif> call, Throwable t) {
            btnUpdateDepart.setEnabled(true);
            LoadDialog.dismiss(UpdateDepartmentActivity.this);
            Toasty.warning(UpdateDepartmentActivity.this, getString(R.string.net_error)).show();
            Log.e("修改部门名称：", t.toString());
        }

    };
}
