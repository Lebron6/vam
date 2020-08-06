package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.CompanyInfo;
import com.dy.vam.entity.TestIntance;
import com.dy.vam.entity.VersionBean;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.EmulatorDetector;
import com.dy.vam.tools.VersionUpdataUtils;
import com.dy.vam.tools.AppManager;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.SetAlias;
import com.dy.vam.ui.adapter.MainPagerAdapter;
import com.dy.vam.ui.widget.AppTxtDialog;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_opera)
    RadioButton rbOpera;
    @BindView(R.id.rb_department)
    RadioButton rbDepartment;
    @BindView(R.id.rb_mcenter)
    RadioButton rbMcenter;
    @BindView(R.id.rg_group)
    RadioGroup rgContent;
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    private long exitTime;
    private int position = 0;


    public static final String DETAILS_VALUES = "title";

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        switch (position) {
            case 0:
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
            case 1:
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
            case 2:
//                viewStatusBar.setVisibility(View.VISIBLE);
//                mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
//                        .statusBarColor(R.color.colorTheme).init();
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
            case 3:
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
        }

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
//        boolean emulator = EmulatorDetector.isEmulator(this);
//        if (emulator==true){
//            Toasty.warning(this,"这是个模拟器").show();
//        }else{
//            Toasty.warning(this,"这是个手机").show();
//        }
        if (!PreferenceUtils.getInstance().getAppTxtKnow() == true) {
            AppTxtDialog dialog = new AppTxtDialog(MainActivity.this, R.style.MyDialog);
            dialog.show();
        }
        getVersionInfo();
    }

    public void getVersionInfo() {
        createRequest(BaseUrl.getInstence().getVersionInfoUrl()).getVersionInfo().enqueue(new Callback<VersionBean>() {
            @Override
            public void onResponse(Call<VersionBean> call, Response<VersionBean> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == Constant.SUCCESS) {
                        int i = VersionUpdataUtils.versionVerification(response.body().getData().getVersion(), response.body().getData().getDownload(), MainActivity.this);
                        if (i == 1) {
                            VersionUpdataUtils.update(response.body().getData().getDownload(), MainActivity.this, 1);
                        } else {
                            getCompanyInfo();
                        }
                    } else {
                        Toasty.error(MainActivity.this, response.body().getMsg()).show();
                    }
                } else {
                    Toasty.error(MainActivity.this, "获取" + getString(R.string.app_name) + "版本信息失败").show();
                }
            }

            @Override
            public void onFailure(Call<VersionBean> call, Throwable t) {
                Toasty.error(MainActivity.this, getString(R.string.net_error) + "：获取版本信息失败！").show();
            }
        });
    }

    public void getCompanyInfo() {

        createRequest(BaseUrl.getInstence().getCompanyInfoUrl()).getCompanyInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<CompanyInfo>() {
            @Override
            public void onResponse(Call<CompanyInfo> call, Response<CompanyInfo> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == Constant.SUCCESS) {
                        if (response.body().getData().getCompanyid() == 0) {
                            SimulationActivity.actionStart(MainActivity.this);
                            finish();
                        } else {
                            MainPagerAdapter viewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
                            vpContent.setAdapter(viewPagerAdapter);
                            vpContent.setOffscreenPageLimit(2);
                            vpContent.setOnPageChangeListener(onPagerChangerListener);
                            rgContent.setOnCheckedChangeListener(onCheckedChangeListener);
                            rbHome.setChecked(true);
                        }
                    } else {
                        Toasty.error(MainActivity.this, response.body().getMsg()).show();
                        finish();
                    }

                } else {
                    Toasty.error(MainActivity.this, getString(R.string.net_error) + ":获取公司信息失败,请稍后重试！").show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CompanyInfo> call, Throwable t) {
                Toasty.error(MainActivity.this, getString(R.string.net_error) + ":获取公司信息失败,请稍后重试！").show();
                finish();
            }
        });

    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            MainActivity.this.position = position;
            switch (position) {
                case 0:
                    rbHome.setChecked(true);
                    viewStatusBar.setVisibility(View.GONE);
                    mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                    break;
                case 1:
                    rbOpera.setChecked(true);
                    viewStatusBar.setVisibility(View.GONE);
                    mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                    break;
                case 2:

                    rbDepartment.setChecked(true);
//                    viewStatusBar.setVisibility(View.VISIBLE);
//                    mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
//                            .statusBarColor(R.color.colorTheme).init();
                    viewStatusBar.setVisibility(View.GONE);
                    mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                    break;
                case 3:
                    rbMcenter.setChecked(true);
                    viewStatusBar.setVisibility(View.GONE);
                    mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTabSelection(checkedId);
        }
    };

    private void setTabSelection(int checkedId) {

        switch (checkedId) {
            case R.id.rb_home:
                position = 0;
                vpContent.setCurrentItem(0, false);
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
            case R.id.rb_opera:
                position = 1;
                vpContent.setCurrentItem(1, false);
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
            case R.id.rb_department:
                position = 2;
                vpContent.setCurrentItem(2, false);
//                viewStatusBar.setVisibility(View.VISIBLE);
//                mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
//                        .statusBarColor(R.color.colorTheme).init();
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
            case R.id.rb_mcenter:
                position = 3;
                vpContent.setCurrentItem(3, false);
                viewStatusBar.setVisibility(View.GONE);
                mImmersionBar.transparentStatusBar().statusBarAlpha(0.2f).init();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toasty.warning(MainActivity.this, "再按一次退出").show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit(MainActivity.this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initDatas() {
        new SetAlias(this, PreferenceUtils.getInstance().getUserId() + "").setAlias();   //设置极光推送别名
    }

}
