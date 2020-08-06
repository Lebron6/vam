package com.dy.vam.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.dy.vam.ui.fragment.Depart1Fragment;
import com.dy.vam.ui.fragment.Home1Fragment;
import com.dy.vam.ui.fragment.Opera2Fragment;
import com.dy.vam.ui.fragment.Self1Fragment;

/**
 * Created by James on 2018/1/9.
 * 主
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

//    private HomeFragment homeFragment;
    private Home1Fragment homeFragment;
    private Opera2Fragment opera2Fragment;
//    private DepartFragment activitysFragment;
    private Depart1Fragment activitysFragment;
//    private SelfFragment mcenterFragment;
    private Self1Fragment mcenterFragment;

    public MainPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            if(homeFragment==null){
                homeFragment = new Home1Fragment();
                return homeFragment;
            }else{
                return homeFragment;
            }
        }else if (position==1){
            if(opera2Fragment==null){
                opera2Fragment = new Opera2Fragment();
                return opera2Fragment;
            }else{
                return opera2Fragment;
            }
        }else if(position==2){
            if(activitysFragment==null){
                activitysFragment = new Depart1Fragment();
                return activitysFragment;
            }else{
                return activitysFragment;
            }
        }else if(position==3){
            if(mcenterFragment==null){
                mcenterFragment = new Self1Fragment();
                return mcenterFragment;
            }else{
                return mcenterFragment;
            }
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


}
