package com.lqm.tomatoit.ui.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.model.pojoVO.HomeVO;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.HomeView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：首页
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private FragmentActivity mActivity;
    private HomeView mHomeView;
    private int mCurrentPage;

    public HomePresenter(FragmentActivity activity) {
        this.mActivity = activity;
    }

    //刷新
    public void getRefreshData() {
        mCurrentPage = 1;
        mHomeView = getView();
        WanService.getHomeData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<HomeVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mHomeView.setDataRefresh(true);
                    }

                    @Override
                    public void onNext(ResponseData<HomeVO> responseData) {
                        if (responseData.getData().getSize() == 0){
//                            mRvContent.setVisibility(View.GONE);
//                            mLankLayout.setVisibility(View.VISIBLE);
                        }else{
//                            mLankLayout.setVisibility(View.GONE);
//                            mRvContent.setVisibility(View.VISIBLE);
                            mHomeView.getAdapter().setNewData(responseData.getData().getDatas());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mHomeView.setDataRefresh(false);
                        Snackbar.make(mHomeView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        //可能需要移除之前添加的布局
                        mHomeView.getAdapter().removeAllFooterView();
                        //最后调用结束刷新的方法
                        mHomeView.setDataRefresh(false);
                    }
                });

    }

    //加载更多
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        mHomeView = getView();
        WanService.getHomeData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseData -> setMoreDataView(responseData),this::showError);
    }

    private void setMoreDataView(ResponseData<HomeVO> responseData){
        if (responseData.getData().getSize() != 0) {
            mHomeView.getAdapter().addData(responseData.getData().getDatas());
        } else {
            mHomeView.getAdapter().loadComplete();
            View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
            mHomeView.getAdapter().addFooterView(noDataView);
        }
    }

    private void showError(Throwable e){
        mHomeView.setDataRefresh(false);
        Snackbar.make(mHomeView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
    }

    //获取轮播图数据
    public void getBannerData() {
        mHomeView = getView();
        WanService.getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<List<BannerBean>> responseData) {
                        mHomeView.setBannerData(responseData.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}