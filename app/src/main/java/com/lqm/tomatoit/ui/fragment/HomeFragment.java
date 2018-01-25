package com.lqm.tomatoit.ui.fragment;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.base.BasePresenter;

/**
 * user：lqm
 * desc：第一个模块，主页Fragment
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_home;
    }
}
