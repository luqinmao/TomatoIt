package com.lqm.tomatoit.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.app.AppConst;
import com.lqm.tomatoit.ui.activity.AboutActivity;
import com.lqm.tomatoit.ui.activity.CollectActivity;
import com.lqm.tomatoit.ui.activity.LoginActivity;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.util.PrefUtils;
import com.lqm.tomatoit.util.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：第三个模块，用户模块
 */

public class UserFragment extends BaseFragment{

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.cv_collect)
    CardView cvCollect;
    @Bind(R.id.cv_about)
    CardView cvAbout;
    @Bind(R.id.cv_logou)
    CardView cvLogou;
    @Bind(R.id.tv_logou)
    TextView tvLogou;


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

    @Override
    public void initView(View rootView) {

        if (PrefUtils.getBoolean(getContext(), AppConst.IS_LOGIN_KEY,false) == false){
            tvLogou.setText("点击登录");
            tvName.setText("暂未登录");
        }else{
            tvName.setText(PrefUtils.getString(getContext(),AppConst.USERNAME_KEY,"暂未登录"));
            tvLogou.setText("退出登录");
        }
    }

    @OnClick({R.id.cv_collect, R.id.cv_about, R.id.cv_logou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_collect:
                if (PrefUtils.getBoolean(getContext(),AppConst.IS_LOGIN_KEY,false) == false){
                    T.showShort(getContext(),"请先登录");
                }else{
                    startActivity(new Intent(getActivity(),CollectActivity.class));
                }
                break;
            case R.id.cv_about:
                startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
            case R.id.cv_logou:
                if (PrefUtils.getBoolean(getContext(),AppConst.IS_LOGIN_KEY,false) == false){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else{
                    //注销
                    PrefUtils.setBoolean(getContext(),AppConst.IS_LOGIN_KEY,false);
                    T.showShort(getContext(),"已注销");
                    tvLogou.setText("点击登录");
                    tvName.setText("暂未登录");
                }
                break;
        }
    }


}
