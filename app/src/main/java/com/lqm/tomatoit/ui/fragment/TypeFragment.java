package com.lqm.tomatoit.ui.fragment;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.ui.adapter.ArticleListAdapter;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.presenter.TypePresenter;
import com.lqm.tomatoit.ui.view.TypeView;
import com.lqm.tomatoit.widget.AutoLinefeedLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.Bind;

/**
 * user：lqm
 * desc：第二个模块，分类Fragment
 */

public class TypeFragment extends BaseFragment<TypeView, TypePresenter>
        implements TypeView, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.ll_tag)
    AutoLinefeedLayout llTag;
    @Bind(R.id.layout_blank)
    AutoLinearLayout layoutBlank;
    private ArticleListAdapter mAdapter;

    public static TypeFragment newInstance() {
        return new TypeFragment();
    }

    @Override
    protected TypePresenter createPresenter() {
        return new TypePresenter(getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_type;
    }


    @Override
    public void initView(View rootView) {

        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleListAdapter(getContext(),null);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this,rvContent);

        mPresenter.getTagData();
    }


    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }

    @Override
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    @Override
    public AutoLinefeedLayout getTagLayout() {
        return llTag;
    }


    @Override
    public ArticleListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void getDataError(String message) {
        Snackbar.make(rvContent, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void getRefreshDataSuccess(List<ArticleBean> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void getMoreDataSuccess(List<ArticleBean> data) {
        if (data.size() != 0) {
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }
}
