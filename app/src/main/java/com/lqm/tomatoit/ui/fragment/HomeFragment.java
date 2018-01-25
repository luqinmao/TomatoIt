package com.lqm.tomatoit.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.adapter.HomeAdapter;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.presenter.HomePresenter;
import com.lqm.tomatoit.ui.view.HomeView;

import butterknife.Bind;

/**
 * user：lqm
 * desc：第一个模块，主页Fragment
 */

public class HomeFragment extends BaseFragment<HomeView, HomePresenter>
        implements HomeView, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private HomeAdapter mAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_home;
    }

    @Override
    public void onRefresh() {
        mPresenter.getRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }


    @Override
    public void setDataRefresh(final Boolean refresh) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(refresh);
            }
        });
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rvContent;
    }

    @Override
    public HomeAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void initView(View rootView) {
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HomeAdapter(getContext(),null);
        rvContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this);

        onRefresh();
    }
}
