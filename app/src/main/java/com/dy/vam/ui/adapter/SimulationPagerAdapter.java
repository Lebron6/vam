package com.dy.vam.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.dy.vam.ui.fragment.DepartFragment;
import com.dy.vam.ui.fragment.HomeFragment;
import com.dy.vam.ui.fragment.Opera1Fragment;
import com.dy.vam.ui.fragment.SelfFragment;
import com.dy.vam.ui.fragment.simulation.SimulationFragment;

/**
 * Created by James on 2018/1/9.
 * 模拟
 */

public class SimulationPagerAdapter extends FragmentPagerAdapter {

    private SimulationFragment simulationFragment;

    public SimulationPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SimulationFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
