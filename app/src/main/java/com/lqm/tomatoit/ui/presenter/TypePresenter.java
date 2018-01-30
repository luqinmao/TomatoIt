package com.lqm.tomatoit.ui.presenter;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojoVO.TypeTagVO;
import com.lqm.tomatoit.model.pojoVO.TypeVO;
import com.lqm.tomatoit.ui.adapter.ArticleListAdapter;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.TypeView;
import com.lqm.tomatoit.util.UIUtils;
import com.lqm.tomatoit.widget.AutoLinefeedLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：分类
 */

public class TypePresenter extends BasePresenter<TypeView> {

    private FragmentActivity mActivity;
    private TypeView mTypeView;
    private int mCurrentPage ;
    private List<TypeTagVO> mTagDatas;
    private ArticleListAdapter mAdapter;
    private int mId;

    public TypePresenter(FragmentActivity activity) {
        this.mActivity = activity;
    }

    //分类标签
    public void getTagData() {
        mTypeView = getView();
        WanService.getTypeTagData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<List<TypeTagVO>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<List<TypeTagVO>> responseData) {
                        mTagDatas = responseData.getData();
                        setTabUI();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //一级Tab
    private void setTabUI() {
        TabLayout tabLayout = mTypeView.getTabLayout();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (TypeTagVO bean : mTagDatas) {
            tabLayout.addTab(tabLayout.newTab().setText(bean.getName()));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTagUI(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //二级Tag
    private void setTagUI(int position) {
        AutoLinefeedLayout llTag = mTypeView.getTagLayout();
        llTag.setVisibility(View.VISIBLE);
        llTag.removeAllViews();
        List<TextView> tvs = new ArrayList<>();
        for (int i = 0; i < mTagDatas.get(position).getChildren().size(); i++) {
            View view = LinearLayout.inflate(mActivity, R.layout.item_tag_layout, null);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            tvItem.setText(mTagDatas.get(position).getChildren().get(i).getName());
            llTag.addView(view);
            tvs.add(tvItem);
            int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (TextView textView: tvs) {
                        textView.setTextColor(UIUtils.getColor(R.color.text1));
                        textView.setBackgroundResource(R.drawable.shape_tag_nor);
                    }
                    tvItem.setTextColor(UIUtils.getColor(R.color.white));
                    tvItem.setBackgroundResource(R.drawable.shape_tag_sel);

                    mId = mTagDatas.get(position).getChildren().get(finalI).getId();
                    getServerData(mId);
                }
            });
        }
    }


    //根据点击的标签获取数据
    public void getServerData(int cid){
        mCurrentPage = 0; //从第0页开始
        mAdapter =  mTypeView.getAdapter();
        WanService.getTypeDataById(mCurrentPage,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<TypeVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<TypeVO> responseData) {
                        if (responseData.getData().getDatas() != null){
                            mAdapter.setNewData(responseData.getData().getDatas());
                            mTypeView.getTagLayout().setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {
                        mAdapter.removeAllFooterView();
                    }
                });

    }

    //加载下一页
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        WanService.getTypeDataById(mCurrentPage,mId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseData -> setMoreDataView(responseData),this::showError);

    }

    private void setMoreDataView(ResponseData<TypeVO> responseData){
        if (responseData.getData().getDatas().size() != 0) {
            mAdapter.addData(responseData.getData().getDatas());
        } else {
            mAdapter.loadComplete();
            View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
            mAdapter.addFooterView(noDataView);
        }
    }

    private void showError(Throwable e){
        Snackbar.make(mTypeView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
    }
}