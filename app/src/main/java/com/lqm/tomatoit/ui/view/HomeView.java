package com.lqm.tomatoit.ui.view;

import android.support.v7.widget.RecyclerView;

import com.lqm.tomatoit.ui.adapter.HomeAdapter;

/**
 * user：lqm
 * desc：首页
 */

public interface HomeView {

    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    HomeAdapter getAdapter();

}
