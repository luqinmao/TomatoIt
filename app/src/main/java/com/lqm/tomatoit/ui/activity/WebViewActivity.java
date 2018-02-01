package com.lqm.tomatoit.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.presenter.WebViewPresenter;
import com.lqm.tomatoit.ui.view.CommonWebView;
import com.lqm.tomatoit.util.UIUtils;
import com.lqm.tomatoit.widget.CustomPopWindow;
import com.lqm.tomatoit.widget.IconFontTextView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：WebView界面，加载文章详情等
 */

public class WebViewActivity extends BaseActivity<CommonWebView, WebViewPresenter>
        implements CommonWebView {

    public static final String WEB_URL = "web_url";

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.tv_return)
    IconFontTextView tvReturn;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_other)
    IconFontTextView tvOther;

    private String mUrl;
    private CustomPopWindow mMorePopWindow;

    public static void runActivity(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEB_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected WebViewPresenter createPresenter() {
        return new WebViewPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void init() {
        mUrl = getIntent().getStringExtra(WEB_URL);
    }

    @Override
    public void initView() {
        tvOther.setVisibility(View.VISIBLE);
        tvOther.setText(UIUtils.getString(R.string.ic_more));

        mPresenter.setWebView(mUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                finish();//退出程序
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.tv_return, R.id.tv_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                   finish();
                break;
            case R.id.tv_other:
                //更多按钮
                View popView = View.inflate(WebViewActivity.this,R.layout.popup_webview_more,null);
                mMorePopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(popView)
                        .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                        .create()
                        .showAsDropDown(tvOther,50,-10);

                break;
        }
    }
}
