package com.lqm.tomatoit.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.ui.adapter.CollectArticleAdapter;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.presenter.CollectPresenter;
import com.lqm.tomatoit.ui.view.CollectView;
import com.lqm.tomatoit.widget.IconFontTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：我的收藏界面
 */

public class CollectActivity extends BaseActivity<CollectView, CollectPresenter> implements CollectView,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @Bind(R.id.tv_return)
    IconFontTextView tvReturn;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_no_collect)
    TextView tvNoCollect;
    @Bind(R.id.tv_other)
    IconFontTextView tvOther;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private CollectArticleAdapter mAdapter;

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_collect;
    }

    @Override
    public void initView() {
        tvTitle.setText("我的收藏");

        rvContent.setLayoutManager(new LinearLayoutManager(CollectActivity.this));
        mAdapter = new CollectArticleAdapter(CollectActivity.this, null);
        rvContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this,rvContent);

        onRefresh();

    }


    @Override
    public void onRefresh() {
        mPresenter.getRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }


    @OnClick(R.id.tv_return)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefreshSuccess(List<ArticleBean> data) {
        mAdapter.setNewData(data);
        tvNoCollect.setVisibility(data.size() ==0? View.VISIBLE:View.GONE);
    }

    @Override
    public void onRefreshFail(String errorString) {
        showSwipeRefresh(false);
        Snackbar.make(rvContent, errorString, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(List<ArticleBean> data) {
        if (data.size() == 0) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onLoadMoreFail(String errorString) {
        showSwipeRefresh(false);
        mAdapter.loadMoreComplete();
        Snackbar.make(rvContent, errorString, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadComplete() {
        showSwipeRefresh(false);
    }

    private void showSwipeRefresh(boolean isRefresh) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(isRefresh);
            }
        });
    }
}
