package com.dy.vam.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.GuidePagerAdapter;
import com.gyf.barlibrary.BarHide;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by James on 2018/1/25.
 */

public class GuideActivity extends BaseActivity {

    private static final int REQUECT_CODE_SDCARD = 1;
    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.tv_guide_content)
    TextView tvGuideContent;
    @BindView(R.id.rg_guide)
    RadioGroup rgGuide;
    @BindView(R.id.rb_one)
    RadioButton rbOne;
    @BindView(R.id.rb_two)
    RadioButton rbTwo;
    @BindView(R.id.rb_three)
    RadioButton rbThree;
    @BindView(R.id.rb_four)
    RadioButton rbFour;
    @BindView(R.id.tv_guide_content_english)
    TextView tvGuideContentEnglish;
    @BindView(R.id.btn_to_login)
    Button btnToLogin;
    @BindView(R.id.tv_to_register)
    TextView tvToRegister;

    @Override
    protected void initTitle() {
        List<View> lists = new ArrayList<>();
        ImageView imageView1 = new ImageView(GuideActivity.this);
        ImageView imageView2 = new ImageView(GuideActivity.this);
        ImageView imageView3 = new ImageView(GuideActivity.this);
        ImageView imageView4 = new ImageView(GuideActivity.this);
        Glide.with(this).load(R.mipmap.img_loading1).into(imageView1);
        Glide.with(this).load(R.mipmap.img_loading2).into(imageView2);
        Glide.with(this).load(R.mipmap.img_loading3).into(imageView3);
        Glide.with(this).load(R.mipmap.img_loading4).into(imageView4);
        lists.add(imageView1);
        lists.add(imageView2);
        lists.add(imageView3);
        lists.add(imageView4);

        GuidePagerAdapter adapter = new GuidePagerAdapter(lists);
        vpGuide.setAdapter(adapter);
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbOne.setChecked(true);
                        tvGuideContent.setText("提 升 经 营 效 益");
                        tvGuideContentEnglish.setText("Promoting the Operating Benefit");
                        break;
                    case 1:
                        rbTwo.setChecked(true);
                        tvGuideContent.setText("培 养 经 营 意 识");
                        tvGuideContentEnglish.setText("Cultivate Business Awareness");
                        break;
                    case 2:
                        rbThree.setChecked(true);
                        tvGuideContent.setText("评 估 个 人 价 值");
                        tvGuideContentEnglish.setText("Assess  One's  Worth");
                        break;
                    case 3:
                        rbFour.setChecked(true);
                        tvGuideContent.setText("共 享 经 营 结 果");
                        tvGuideContentEnglish.setText("Sharing  Of  Operating  Results");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (Utils.checkDeviceHasNavigationBar(this)) {
            mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar).hideBar(BarHide.FLAG_HIDE_BAR)
                    .init();
        } else {
            mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                    .init();
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initViews() {
        tvGuideContent.setText("提 升 经 营 效 益");
        tvGuideContentEnglish.setText("Promoting the Operating Benefit");
        MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA});
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {  //请求获取服务器.apk版本是否与本地版本同步判断是否需要更新
        if (PreferenceUtils.getInstance().getLoginStatus() == true) {
            WelcomeActivity.actionStart(GuideActivity.this, "Main");
            finish();
        }
    }


    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
        Toasty.warning(this, "请设置App运行所需权限!").show();
        finish();
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.btn_to_login, R.id.tv_to_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_to_login:
                LoginActivity.actionStart(this, "登录");
                break;
            case R.id.tv_to_register:
                RegisterVerifActivity.actionStart(GuideActivity.this, "注册");
                break;
        }
    }

}
