package com.lqm.tomatoit.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.app.AppConst;
import com.lqm.tomatoit.helper.rxjavahelper.RxObserver;
import com.lqm.tomatoit.helper.rxjavahelper.RxResultHelper;
import com.lqm.tomatoit.helper.rxjavahelper.RxSchedulersHelper;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.ui.activity.WebViewActivity;
import com.lqm.tomatoit.util.PrefUtils;
import com.lqm.tomatoit.util.T;
import com.lqm.tomatoit.util.UIUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：文章列表适配器
 */

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    private Context mContext;

    public ArticleListAdapter(Context context, @Nullable List<ArticleBean> data) {
        super(R.layout.item_article, data);
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final ArticleBean bean) {
        holder.setText(R.id.tv_title, Html.fromHtml(bean.getTitle()))
                .setText(R.id.tv_author, bean.getAuthor())
                .setText(R.id.tv_time, bean.getNiceDate())
                .setText(R.id.tv_type, bean.getChapterName());

        TextView tvCollect = (TextView) holder.getView(R.id.tv_collect);
        if (bean.isCollect()) {
            tvCollect.setText(UIUtils.getString(R.string.ic_collect_sel));
            tvCollect.setTextColor(UIUtils.getColor(R.color.main));
        } else {
            tvCollect.setText(UIUtils.getString(R.string.ic_collect_nor));
            tvCollect.setTextColor(UIUtils.getColor(R.color.text3));
        }

        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectArticle(tvCollect, bean);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.runActivity(mContext, bean.getLink());
            }
        });

    }

    //收藏文章
    private void collectArticle(TextView tvCollect, ArticleBean bean) {
        if (PrefUtils.getBoolean(mContext, AppConst.IS_LOGIN_KEY, false) == false) {
            T.showShort(mContext, "请先登录");
            return;
        }
        if (bean.isCollect()) {
            //已经收藏，点击取消收藏
            unCollectArticler(bean, tvCollect);
        } else {
            collectArticler(bean, tvCollect);
        }
    }

    private void collectArticler(ArticleBean bean, TextView tvCollect) {
        WanService.collectArticle(bean.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<String> responseData) {
                        if (responseData.getErrorCode() == 0) {
                            T.showShort(mContext, "收藏成功");
                            bean.setCollect(true);
                            tvCollect.setText(UIUtils.getString(R.string.ic_collect_sel));
                            tvCollect.setTextColor(UIUtils.getColor(R.color.main));
                        } else {
                            T.showShort(mContext, responseData.getErrorMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        T.showShort(mContext, "收藏失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void unCollectArticler(ArticleBean bean, TextView tvCollect) {
        WanService.unCollectArticle(bean.getId(), bean.getOriginId(), false)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void _onNext(String s) {
                        T.showShort(mContext, "取消收藏");
                        bean.setCollect(false);
                        tvCollect.setText(UIUtils.getString(R.string.ic_collect_nor));
                        tvCollect.setTextColor(UIUtils.getColor(R.color.text3));
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        T.showShort(mContext, "取消收藏失败");
                    }
                });
    }

}
