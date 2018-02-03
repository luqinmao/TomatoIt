package com.lqm.tomatoit.ui.activity;

import android.widget.TextView;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.widget.IconFontTextView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：关于我们界面
 */

public class AboutActivity extends BaseActivity {

    @Bind(R.id.tv_return)
    IconFontTextView tvReturn;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_other)
    IconFontTextView tvOther;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        tvTitle.setText("关于我们");
    }

    @OnClick(R.id.tv_return)
    public void onViewClicked() {
        finish();
    }
}
