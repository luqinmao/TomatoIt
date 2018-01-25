package com.lqm.tomatoit.ui.fragment;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.base.BasePresenter;

/**
 * user：lqm
 * desc：第二个模块，分类Fragment
 */

public class TypeFragment extends BaseFragment {

    public static TypeFragment newInstance() {
        return new TypeFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_type;
    }
}
