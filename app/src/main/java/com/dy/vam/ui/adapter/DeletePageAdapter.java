package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeletePageAdapter extends FragmentStatePagerAdapter {//FragmentPagerAdapter

//    private FragmentManager fm;
    private List<Fragment> fragments = null;
    private Context context;

    public DeletePageAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        notifyDataSetChanged();
    }
    
    public DeletePageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        notifyDataSetChanged();
//        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int arg0) {
//        Fragment fragment = new ColourFragment();
//        Bundle args = new Bundle();
//        args.putInt("title", arg0);
//        args.putSerializable("content",hotIssuesList.get(arg0));
//        fragment.setArguments(args);
//        return fragment;
        return fragments.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }
    
    @Override
    public int getCount() {
        return fragments.size();//hotIssuesList.size();
    }
    
}