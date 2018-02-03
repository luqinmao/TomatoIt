package com.lqm.tomatoit.ui.presenter;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.model.pojoVO.ArticleListVO;
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

    private int mCurrentPage;

    //刷新
    public void getRefreshData() {
        mCurrentPage = 0;
        WanService.getHomeData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().showRefreshView(true);
                    }

                    @Override
                    public void onNext(ResponseData<ArticleListVO> responseData) {
                        getView().getRefreshDataSuccess(responseData.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().getDataError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().showRefreshView(false);
                    }
                });

    }

    //加载更多
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        WanService.getHomeData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<ArticleListVO> responseData) {
                            getView().getMoreDataSuccess(responseData.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().getDataError(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //获取轮播图数据
    public void getBannerData() {
        WanService.getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<List<BannerBean>> responseData) {
                        getView().getBannerDataSuccess(responseData.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().getDataError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}