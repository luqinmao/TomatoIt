package com.lqm.tomatoit.ui.view;

import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * user：lqm
 * desc： WebView
 */

public interface CommonWebView {

    ProgressBar getProgressBar();
    WebView getWebView();
    void setTitle(String title);

}
