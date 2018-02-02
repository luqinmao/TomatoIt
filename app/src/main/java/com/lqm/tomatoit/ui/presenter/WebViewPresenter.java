package com.lqm.tomatoit.ui.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.CommonWebView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：WebView Presenter
 */

public class WebViewPresenter extends BasePresenter<CommonWebView> {

    private Activity activity;

    public WebViewPresenter(Activity activity) {
        this.activity = activity;
    }

    public void setWebView(String url) {

        CommonWebView urlView = getView();
        ProgressBar progressBar = urlView.getProgressBar();
        WebView webView = urlView.getWebView();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 支持JS
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击放大缩小

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                System.out.println("网页开始加载");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束");
                progressBar.setVisibility(View.GONE);
            }

            /**
             * 所有跳转的链接都在此方法中回调
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                urlView.setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });

        webView.loadUrl(url);
    }


    //收藏
    public void collectArticle(int id) {
        WanService.collectArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<String> responseData) {
                        if (responseData.getErrorCode() == 0) {
                            getView().collectSuccsee();
                        } else {
                            getView().collectFrail(responseData.getErrorMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().collectFrail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    //取消收藏
    public void unCollectArticle(int id) {
        WanService.unCollectArticle2(id,-1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<String> responseData) {
                        if (responseData.getErrorCode() == 0) {
                            getView().unCollectSuccsee();
                        } else {
                            getView().unCollectFail(responseData.getErrorMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().unCollectFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}