package com.dy.vam.ui.fragment.simulation;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.activity.SettingActivity;
import com.dy.vam.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2018/4/24.
 * 模拟界面
 */

public class SimulationFragment extends BaseFragment {

    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.layout_image)
    RelativeLayout layoutImage;
    @BindView(R.id.image_setting)
    ImageView imageSetting;
    private int position;

    public SimulationFragment(int position) {
        this.position = position;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_simulation;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        switch (position) {
            case 0:
                if (!PreferenceUtils.getInstance().getSimulationShowStatus()) {
                    PreferenceUtils.getInstance().setSimulationShowStatus(true);
                    imgPic.setVisibility(View.VISIBLE);
                    imgPic.setBackgroundResource(R.mipmap.img_moni);
                } else {
                    layoutImage.setBackgroundResource(R.mipmap.moni_1);
                }
                imageSetting.setVisibility(View.GONE);
                break;
            case 1:
                imgPic.setVisibility(View.GONE);
                imageSetting.setVisibility(View.GONE);
                layoutImage.setBackgroundResource(R.mipmap.moni_2);
                break;
            case 2:
                imgPic.setVisibility(View.GONE);
                imageSetting.setVisibility(View.GONE);
                layoutImage.setBackgroundResource(R.mipmap.moni_3);
                break;
            case 3:
                imgPic.setVisibility(View.GONE);
                imageSetting.setVisibility(View.VISIBLE);
                layoutImage.setBackgroundResource(R.mipmap.moni_4);
                break;
        }
    }

    @OnClick({R.id.img_pic, R.id.image_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_pic:
                imgPic.setVisibility(View.GONE);
                break;
            case R.id.image_setting:
                SettingActivity.actionStart(getActivity(), "设置");
                break;
        }
    }
}
