package com.lqm.tomatoit.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.model.pojo.HomeBean;
import com.lqm.tomatoit.util.UIUtils;

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
        holder.setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_author, bean.getAuthor())
                .setText(R.id.tv_type, bean.getChapterName());

        TextView tvCollect = (TextView) holder.getView(R.id.tv_collect);
        tvCollect.setTextColor(bean.isCollect() ? UIUtils.getColor(R.color.main) : UIUtils.getColor(R.color.text3));

        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
