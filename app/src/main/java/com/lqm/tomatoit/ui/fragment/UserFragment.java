package com.lqm.tomatoit.ui.fragment;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.util.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：第三个模块，用户模块
 */

public class UserFragment extends BaseFragment {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.cv_collect)
    CardView cvCollect;
    @Bind(R.id.cv_about)
    CardView cvAbout;
    @Bind(R.id.cv_logou)
    CardView cvLogou;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_user;
    }


    @OnClick({R.id.cv_collect, R.id.cv_about, R.id.cv_logou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_collect:
                T.showShort(getContext(),"点击");
                break;
            case R.id.cv_about:
                break;
            case R.id.cv_logou:
                break;
        }
    }
}
