package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.User;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static final String DETAILS_VALUES = "title";

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        editor = sp.edit();
        if (TextUtils.isEmpty(sp.getString("rememberAccount",""))){
            etAccount.getText().clear();
        }else{
            etAccount.setText(sp.getString("rememberAccount",""));
        }
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.image_back, R.id.btn_login, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_login:
                String account =etAccount.getText().toString();
                String password =etPassword.getText().toString();
                if (TextUtils.isEmpty(account)){
                    Toasty.error(LoginActivity.this,"请输入账号").show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toasty.error(LoginActivity.this,"请输入密码").show();
                    return;
                }
                toLogin(account,password);
                break;
            case R.id.tv_forget_password:
                ForgetPasswordVerifActivity.actionStart(this,"忘记并重置密码");
                break;
        }
    }

    private void toLogin(final String account, final String password) {
        btnLogin.setEnabled(false);
        LoadDialog.show(LoginActivity.this);
        createRequest(BaseUrl.getInstence().getUserLoginUrl()).userLogin(account,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                btnLogin.setEnabled(true);
                LoadDialog.dismiss(LoginActivity.this);
                switch (response.body().getCode()){
                    case Constant.SUCCESS:
                        saveUserInfo(response.body(),account,password);
                        MainActivity.actionStart(LoginActivity.this,"Main");
                        finish(); 
                        break;
                    default:
                        Toasty.error(LoginActivity.this,response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                btnLogin.setEnabled(true);
                LoadDialog.dismiss(LoginActivity.this);
                Toasty.warning(LoginActivity.this, getString(R.string.net_error)).show();
                Log.e("loginFailure",t.toString());
            }
        });
    }

    private void saveUserInfo(User body, String account, String password) {
        Log.e("UserInfo",body.getData().toString());
        editor.putString("rememberAccount",account);
        editor.putString("rememberPassword",password);
        editor.commit();
        PreferenceUtils.getInstance().setLoginStatus(true);
        PreferenceUtils.getInstance().setUserToken(body.getData().getToken());
        PreferenceUtils.getInstance().setUserID(body.getData().getId());
        PreferenceUtils.getInstance().setUserName(body.getData().getUsername());
        PreferenceUtils.getInstance().setUserHeadimg(body.getData().getHeadimg());
    }

}
