package com.lqm.tomatoit.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @user  lqm
 * @desc  ViewPager+Fragment 的适配器
 */

public class FragPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public FragPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
