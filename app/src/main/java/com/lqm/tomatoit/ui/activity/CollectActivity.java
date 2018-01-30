package com.lqm.tomatoit.ui.activity;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.base.BasePresenter;

/**
 * user：lqm
 * desc：我的收藏界面
 */

public class CollectActivity extends BaseActivity{
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_collect;
    }
}
