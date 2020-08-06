package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.fragment.AllocatingTableCrossFragment;
import com.dy.vam.ui.widget.StickyScrollView;
import com.dy.vam.ui.widget.TimeSelectWindow;
import com.dy.vam.ui.widget.TitleBarManger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dy.vam.config.Constant.OperaConstant.DISTRIBUTION;

/**
 * Created by James on 2018/1/30.
 * 提成分配表 、财务录入
 */

public class AllocatingTableCrossActivity extends BaseActivity implements StickyScrollView.OnScrollChangeListener{


    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_quarter_banner)
    TextView tvQuarterBanner;
    @BindView(R.id.layout_show_quarter_window)
    RelativeLayout layoutShowQuarterWindow;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.frame_contant)
    FrameLayout frameContant;
    private ArrayAdapter seasonsAdapter;
    private List<String> seasons;

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, AllocatingTableCrossActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar).statusBarColor(R.color.colorWhite)
                .init();
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
        return R.layout.activity_allocating_table_cross;
    }

    @Override
    protected void initViews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_contant, new AllocatingTableCrossFragment(DISTRIBUTION)).commit();
    }

    @Override
    protected void initDatas() {
        seasons = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            seasons.add("第"+(i + 1)+"季度");
        }
        seasonsAdapter = new ArrayAdapter(AllocatingTableCrossActivity.this, R.layout.item_question, R.id.tv_popqusetion, seasons);
        tvQuarterBanner.setText("第" + Utils.getSeason(new Date()) + "季度");
    }

    OnTimeSelectedListener seasonSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            AllocatingTableCrossFragment.sendBroadCast(AllocatingTableCrossActivity.this, postion+1);
            tvQuarterBanner.setText(seasons.get(postion) );
        }
    };

    @OnClick(R.id.layout_show_quarter_window)
    public void onViewClicked() {
        TimeSelectWindow seasonSelectWindow = new TimeSelectWindow(AllocatingTableCrossActivity.this);
        seasonSelectWindow.showView(layoutShowQuarterWindow, seasonsAdapter, seasonSelectedListener);
    }

    @Override
    public void scrollParams(int y) {

    }
}
