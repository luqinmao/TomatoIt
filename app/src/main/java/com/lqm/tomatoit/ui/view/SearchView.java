package com.lqm.tomatoit.ui.view;

import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.model.pojo.HotKeyBean;

import java.util.List;

/**
 * user：lqm
 * desc：搜索
 */

public interface SearchView {
    void getHotKeySuccess(List<HotKeyBean> data);

    void getHotKeyFail(String message);

    void searchDataSuccess(List<ArticleBean> data);

    void searchDataFail(String message);

    void loadMoreDataSuccess(List<ArticleBean> data);

    void loadMoreDataFail(String message);
}
