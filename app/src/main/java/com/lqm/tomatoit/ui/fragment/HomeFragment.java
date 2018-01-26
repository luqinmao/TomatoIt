package com.lqm.tomatoit.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.manager.ImageLoaderManager;
import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.ui.adapter.HomeAdapter;
import com.lqm.tomatoit.ui.base.BaseFragment;
import com.lqm.tomatoit.ui.presenter.HomePresenter;
import com.lqm.tomatoit.ui.view.HomeView;

import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;

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
    private BGABanner mBannerView;

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
    public void setBannerData(List<BannerBean> bannerData) {
        //设置轮播图
        mBannerView.setData(R.layout.item_banner, bannerData, null);
//        if(bannerData.size() >1){
//            mBannerView.setAutoPlayAble(true);
//        }else{
//            mBannerView.setAutoPlayAble(false);
//        }
        mBannerView.setAdapter(new BGABanner.Adapter<View, BannerBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, BannerBean model, int position) {
                ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
                ImageLoaderManager.LoadImage(getContext(), model.getImagePath(), imageView);
            }
        });
        mBannerView.setDelegate(new BGABanner.Delegate<View, BannerBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, BannerBean model, int position) {

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

        //添加头部轮播图布局
        View headView = View.inflate(getActivity(), R.layout.layout_banner, null);
        mBannerView = (BGABanner)headView.findViewById(R.id.banner);
        mAdapter.addHeaderView(headView);

        onRefresh();
    }

    @Override
    public void initData() {
        mPresenter.getBannerData();
    }
}
