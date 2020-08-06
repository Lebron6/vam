package com.dy.vam.ui.widget;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.SlideDepartListAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SlideMenuView extends SlidingMenu {



    private int res;
    private Activity activity;
    private SlideDepartListAdapter adapter;
    SlideDepartListAdapter.OnItemClickLitener onItemClickLitener;
    SummaryDepartmentData summaryDepartmentData;
    private int selectPosition=0;

    public SlideMenuView(Activity context, int view, SlideDepartListAdapter.OnItemClickLitener onItemClickLitener, SummaryDepartmentData summaryDepartmentData,int selectPosition) {
        super(context);
        this.activity = context;
        this.res = view;
        this.onItemClickLitener = onItemClickLitener;
        this.summaryDepartmentData = summaryDepartmentData;
        this.selectPosition = selectPosition;
        initMenu();
    }

    public void initMenu() {
        setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        setShadowWidthRes(R.dimen.shadow_width);
        //menu.setShadowDrawable(R.color.colorAccent);
        // 设置滑动菜单视图的宽度
        setBehindOffsetRes(R.dimen.x330);
        // 设置渐入渐出效果的值
        // 设置渐入渐出效果的值
        //setFadeDegree(1f);
        setFadeEnabled(false);
        attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        setMenu(res);
        initViews();
//        initDatas();
    }

    RecyclerView recyclerView;

    private void initViews() {
        recyclerView = activity.findViewById(R.id.slide_rv_depart);
    }

    public void initDatas(int selectPosition) {
        adapter=new SlideDepartListAdapter(activity,selectPosition);
        adapter.setDatas(summaryDepartmentData);
        RecyclerViewHelper.initRecyclerViewV(activity,recyclerView,false,adapter);
        adapter.setOnItemClickLitener(onItemClickLitener);
    }

}