package com.lqm.tomatoit.ui.view;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;

import com.lqm.tomatoit.ui.adapter.ArticleListAdapter;
import com.lqm.tomatoit.widget.AutoLinefeedLayout;

/**
 * user：lqm
 * desc：分类View
 */

public interface TypeView {

    TabLayout getTabLayout();

    AutoLinefeedLayout getTagLayout();

    RecyclerView getRecyclerView();

    ArticleListAdapter getAdapter();

}
