package com.lqm.tomatoit.ui.presenter;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.HotKeyBean;
import com.lqm.tomatoit.model.pojoVO.ArticleListVO;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.SearchView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：搜索
 */

public class SearchPresenter extends BasePresenter<SearchView>{


    private int mCurrentPage;

    //热门搜索
    public void getHotKeyData() {
        WanService.getHotKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<List<HotKeyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<List<HotKeyBean>> responseData) {
                        getView().getHotKeySuccess(responseData.getData());

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().getHotKeyFail(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //搜索
    public void searchData(String key) {
        mCurrentPage = 0;
        WanService.searchArticle(mCurrentPage,key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseData<ArticleListVO>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ResponseData<ArticleListVO> responseData) {
                    if (responseData.getData() == null){
                        getView().searchDataSuccess(null);
                    }else{
                        getView().searchDataSuccess(responseData.getData().getDatas());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    getView().searchDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
    }

    //加载更多
    public void getMoreData(String key){
        mCurrentPage += 1;
        WanService.searchArticle(mCurrentPage,key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<ArticleListVO> responseData) {
                        getView().loadMoreDataSuccess(responseData.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().loadMoreDataFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
