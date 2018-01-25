package com.lqm.tomatoit.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.model.pojo.HomeBean;

import java.util.List;

/**
 * user：lqm
 * desc：首页列表适配器
 */

public class HomeAdapter extends BaseQuickAdapter<HomeBean> {

    private Context mContext;

    public HomeAdapter(Context context, @Nullable List<HomeBean> data) {
        super(R.layout.item_home, data);
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final HomeBean bean) {

    }
}
