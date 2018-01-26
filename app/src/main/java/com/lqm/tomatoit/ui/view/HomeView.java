package com.lqm.tomatoit.ui.view;

import android.support.v7.widget.RecyclerView;

import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.ui.adapter.HomeAdapter;

import java.util.List;

/**
 * user：lqm
 * desc：首页
 */

public interface HomeView {

    void setDataRefresh(Boolean refresh);
    void setBannerData(List<BannerBean> data);
    RecyclerView getRecyclerView();
    HomeAdapter getAdapter();


}
