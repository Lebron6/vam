package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.AppManager;
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
 * 修改密码
 */

public class UpdataPasswordActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";

    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_re_password)
    EditText etRePassword;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context, UpdataPasswordActivity.class);
        intent.putExtra(DETAILS_VALUES, userId);
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
        return R.layout.activity_updata_password;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
    }


    @OnClick({R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                String oldPassword = etOldPassword.getText().toString();
                String password = etPassword.getText().toString();
                String rePassword = etRePassword.getText().toString();
                if (TextUtils.isEmpty(oldPassword)) {
                    Toasty.error(UpdataPasswordActivity.this, "请输入旧密码").show();
                    return;
                }
                if (TextUtils.isEmpty(password) | TextUtils.isEmpty(rePassword)) {
                    Toasty.error(UpdataPasswordActivity.this, "新密码不能为空").show();
                    return;
                }
                if (!password.equals(rePassword)) {
                    Toasty.error(UpdataPasswordActivity.this, "密码不一致,请重新输入").show();
                    return;
                }
                if (password.length() < 6) {
                    Toasty.error(UpdataPasswordActivity.this, "密码长度不应小于六位").show();
                    return;
                }
                setPasswordCommit(oldPassword, password, rePassword);
                break;
        }
    }

    private void setPasswordCommit(String oldpassword, String newPassword, String conPassword) {
        btnCommit.setEnabled(false);
        LoadDialog.show(UpdataPasswordActivity.this);
        createRequest(BaseUrl.getInstence().getUpdataPasswordUrl()).updataPassword(PreferenceUtils.getInstance().getUserToken(), oldpassword, newPassword, conPassword).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(UpdataPasswordActivity.this);
                btnCommit.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(UpdataPasswordActivity.this, "密码修改成功,请重新登录").show();
                        PreferenceUtils.getInstance().loginOut();
                        AppManager.getAppManager().finishAllActivity();
                        startActivity(new Intent(UpdataPasswordActivity.this, GuideActivity.class));
                        break;
                    default:
                        Toasty.error(UpdataPasswordActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(UpdataPasswordActivity.this);
                Toasty.warning(UpdataPasswordActivity.this, getString(R.string.net_error)).show();
                Log.e("修改密码失败", t.toString());
            }
        });
    }

}
