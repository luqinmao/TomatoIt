package com.lqm.tomatoit.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.adapter.ArticleListAdapter;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.presenter.TypePresenter;
import com.lqm.tomatoit.ui.view.TypeView;
import com.lqm.tomatoit.widget.AutoLinefeedLayout;
import com.zhy.autolayout.AutoLinearLayout;

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
        mAdapter.setOnLoadMoreListener(this);

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
    public RecyclerView getRecyclerView() {
        return rvContent;
    }

    @Override
    public ArticleListAdapter getAdapter() {
        return mAdapter;
    }
}
