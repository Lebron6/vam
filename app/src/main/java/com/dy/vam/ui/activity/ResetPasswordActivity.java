package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.AppManager;
import com.dy.vam.ui.widget.LoadDialog;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 重置密码
 */

public class ResetPasswordActivity extends BaseActivity {

    public static final String USER_ID = "user_id";
    public static final String VERIFY = "verify";
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_re_password)
    EditText etRePassword;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private String userId;
    private String verify;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String userId,String verify) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(USER_ID, userId);
        intent.putExtra(VERIFY, verify);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_forget_set_password;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
        userId = getIntent().getStringExtra(USER_ID);
        verify = getIntent().getStringExtra(VERIFY);
    }


    @OnClick({R.id.image_back, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_commit:
                String password=etPassword.getText().toString();
                String rePassword=etRePassword.getText().toString();
                if (TextUtils.isEmpty(password)|TextUtils.isEmpty(rePassword)){
                    Toasty.error(ResetPasswordActivity.this,"密码不能为空").show();
                    return;
                }
                if (!password.equals(rePassword)){
                    Toasty.error(ResetPasswordActivity.this,"密码不一致,请重新输入").show();
                    return;
                }
                if (password.length()<6){
                    Toasty.error(ResetPasswordActivity.this,"密码长度不应小于六位").show();
                    return;
                }
                setPasswordCommit(password,rePassword);
                break;
        }
    }

    private void setPasswordCommit(String password, String rePassword) {
        btnCommit.setEnabled(false);
        LoadDialog.show(ResetPasswordActivity.this);
        createRequest(BaseUrl.getInstence().getUserResetPasswordUrl()).userResetPassword(userId,verify,password).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(ResetPasswordActivity.this);
                btnCommit.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(ResetPasswordActivity.this, "密码保存成功,请登录").show();
                        AppManager.getAppManager().finishAllActivity();
                        LoginActivity.actionStart(ResetPasswordActivity.this,"Login");
                        break;
                    default:
                        Toasty.error(ResetPasswordActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(ResetPasswordActivity.this);
                Toasty.warning(ResetPasswordActivity.this, getString(R.string.net_error)).show();
                Log.e("getPhoneCodeFailure", t.toString());
            }
        });
    }

}
