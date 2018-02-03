package com.lqm.tomatoit.ui.view;

import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.model.pojo.BannerBean;

import java.util.List;

/**
 * user：lqm
 * desc：首页
 */

public interface HomeView {

    void showRefreshView(Boolean refresh);
    void getBannerDataSuccess(List<BannerBean> data);
    void getDataError(String message);
    void getMoreDataSuccess(List<ArticleBean> data);
    void getRefreshDataSuccess(List<ArticleBean> data);

}
