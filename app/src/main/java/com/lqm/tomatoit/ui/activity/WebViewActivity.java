package com.lqm.tomatoit.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.presenter.WebViewPresenter;
import com.lqm.tomatoit.ui.view.CommonWebView;
import com.lqm.tomatoit.util.ActivityUtils;
import com.lqm.tomatoit.util.SharesUtils;
import com.lqm.tomatoit.util.UIUtils;
import com.lqm.tomatoit.widget.CustomPopWindow;
import com.lqm.tomatoit.widget.IconFontTextView;
import com.lqm.tomatoit.widget.WebViewFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：WebView界面，加载文章详情等
 * (tip:上滑隐藏topbar,因为webview焦点问题，这里在布局中用NestedScrollView，再动态添加webview)
 */

public class WebViewActivity extends BaseActivity<CommonWebView, WebViewPresenter>
        implements CommonWebView {

    public static final String WEB_URL = "web_url";

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.tv_return)
    IconFontTextView tvReturn;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_other)
    IconFontTextView tvOther;
    @Bind(R.id.rl_topbar_layout)
    RelativeLayout rlTopbarLayout;
    @Bind(R.id.webview_container)
    NestedScrollView webViewContainer;


    private String mUrl;
    private CustomPopWindow mMorePopWindow;
    private WebViewFragment mWebViewFragment;
    private WebView webView;

    public static void runActivity(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEB_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected WebViewPresenter createPresenter() {
        return new WebViewPresenter();
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
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void init() {
        mUrl = getIntent().getStringExtra(WEB_URL);
    }

    @Override
    public void initView() {
        tvOther.setText(UIUtils.getString(R.string.ic_more));

        mWebViewFragment = new WebViewFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mWebViewFragment, R.id.webview_container);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //获取webview
        webView = mWebViewFragment.getWebView();
        mPresenter.setWebView(webView, mUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
                View popView = View.inflate(WebViewActivity.this, R.layout.popup_webview_more, null);
                mMorePopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(popView)
                        .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                        .create()
                        .showAsDropDown(tvOther, -430, -10);

                //分享
                popView.findViewById(R.id.tv_shape).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharesUtils.share(WebViewActivity.this, webView.getUrl());
                        mMorePopWindow.dissmiss();
                    }
                });
                //复制链接
                popView.findViewById(R.id.tv_copy_link).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager cmd = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        cmd.setPrimaryClip(ClipData.newPlainText(getString(R.string.copy_link), webView.getUrl()));
                        Snackbar.make(getWindow().getDecorView(), R.string.copy_link_success, Snackbar.LENGTH_SHORT).show();
                        mMorePopWindow.dissmiss();
                    }
                });
                //使用系统浏览器打开
                popView.findViewById(R.id.tv_open_out).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
                        startActivity(intent);
                        mMorePopWindow.dissmiss();
                    }
                });
                break;
        }
    }
}
