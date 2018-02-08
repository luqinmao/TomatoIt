package com.lqm.tomatoit.ui.presenter;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.helper.rxjavahelper.RxObserver;
import com.lqm.tomatoit.helper.rxjavahelper.RxResultHelper;
import com.lqm.tomatoit.helper.rxjavahelper.RxSchedulersHelper;
import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.model.pojoVO.ArticleListVO;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.HomeView;

import java.util.List;

import io.reactivex.disposables.Disposable;

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
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onSubscribe(Disposable d) {
                        getView().showRefreshView(true);
                    }

                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().getRefreshDataSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String message) {
                        getView().getDataError(message);
                    }

                    @Override
                    public void _onComplete() {
                        getView().showRefreshView(false);
                    }
                });
    }

    //加载更多
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        WanService.getHomeData(mCurrentPage)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().getMoreDataSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }
                });
    }

    //获取轮播图数据
    public void getBannerData() {

        WanService.getBannerData()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<List<BannerBean>>() {
                    @Override
                    public void _onNext(List<BannerBean> bannerBeans) {
                        getView().getBannerDataSuccess(bannerBeans);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }
                });
    }

}