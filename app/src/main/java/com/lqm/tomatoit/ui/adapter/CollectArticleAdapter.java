package com.lqm.tomatoit.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.helper.rxjavahelper.RxObserver;
import com.lqm.tomatoit.helper.rxjavahelper.RxResultHelper;
import com.lqm.tomatoit.helper.rxjavahelper.RxSchedulersHelper;
import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.ui.activity.WebViewActivity;
import com.lqm.tomatoit.util.T;
import com.lqm.tomatoit.util.UIUtils;

import java.util.List;

/**
 * user：lqm
 * desc：我的收藏文章列表适配器
 *      （这个适配器跟ArticleListAdapter一样的，但是接口收藏列表接口返回的数据collect字段有问题，所以这里分开）
 */

public class CollectArticleAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    private Context mContext;

    public CollectArticleAdapter(Context context, @Nullable List<ArticleBean> data) {
        super(R.layout.item_article, data);
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final ArticleBean bean) {
        holder.setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_author, bean.getAuthor())
                .setText(R.id.tv_time, bean.getNiceDate())
                .setText(R.id.tv_type, bean.getChapterName());

        TextView tvCollect = (TextView) holder.getView(R.id.tv_collect);
        tvCollect.setText(UIUtils.getString(R.string.ic_collect_sel));
        tvCollect.setTextColor(UIUtils.getColor(R.color.main));

        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unCollectArticler(holder.getLayoutPosition(), bean);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.runActivity(mContext, bean.getLink());
            }
        });

    }

    private void unCollectArticler(int position, ArticleBean bean) {
        WanService.unCollectArticle(bean.getId(),bean.getOriginId(),true)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void _onNext(String s) {
                        T.showShort(mContext, "取消收藏");
                        getData().remove(position);
                        if (position != 0){
                            notifyItemRemoved(position);
                        }else{
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        T.showShort(mContext, "取消收藏失败");
                    }
                });
    }
}
