package com.lqm.tomatoit.ui.presenter;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojoVO.ArticleListVO;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.CollectView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：我的收藏
 */

public class CollectPresenter extends BasePresenter<CollectView> {

    private int mCurrentPage;

    //刷新获取数据
    public void getRefreshData() {
        mCurrentPage = 0;
        WanService.getCollectData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<ArticleListVO> responseData) {
                        getView().onRefreshSuccess(responseData.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onRefreshFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().loadComplete();
                    }
                });

    }

    //获取更多数据
    public void getMoreData() {
        mCurrentPage += 1;
        WanService.getCollectData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<ArticleListVO> responseData) {
                        getView().onLoadMoreSuccess(responseData.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onLoadMoreFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
