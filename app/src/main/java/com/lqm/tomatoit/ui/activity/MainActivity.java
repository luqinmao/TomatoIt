package com.lqm.tomatoit.ui.activity;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.base.BasePresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {


    }
}
