package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import com.dy.vam.R;
import com.dy.vam.tools.AppManager;
import com.dy.vam.ui.adapter.SimulationPagerAdapter;
import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * Created by James on 2018/4/24.
 * 模拟数据
 */

public class SimulationActivity extends BaseActivity {

    @BindView(R.id.vp_simulation)
    ViewPager vpSimulation;
    private long exitTime;


    public static void actionStart(Context context){
        context.startActivity(new Intent(context,SimulationActivity.class));
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_simulation;
    }

    @Override
    protected void initViews() {
        Toasty.normal(SimulationActivity.this,"尚未加入任意部门,请等待人事安排...").show();
        SimulationPagerAdapter adapter = new SimulationPagerAdapter(getSupportFragmentManager());
        vpSimulation.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toasty.warning(SimulationActivity.this, "再按一次退出").show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit(SimulationActivity.this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
