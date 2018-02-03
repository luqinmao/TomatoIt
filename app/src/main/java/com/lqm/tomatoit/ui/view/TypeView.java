package com.lqm.tomatoit.ui.view;

import android.support.design.widget.TabLayout;

import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.ui.adapter.ArticleListAdapter;
import com.lqm.tomatoit.widget.AutoLinefeedLayout;

import java.util.List;

/**
 * user：lqm
 * desc：分类View
 */

public interface TypeView {

    TabLayout getTabLayout();
    AutoLinefeedLayout getTagLayout();
    ArticleListAdapter getAdapter();

    void getDataError(String message);
    void getRefreshDataSuccess(List<ArticleBean> data);
    void getMoreDataSuccess(List<ArticleBean> data);

}
