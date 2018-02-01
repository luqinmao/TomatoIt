package com.lqm.tomatoit.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.tomatoit.R;
import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.model.pojo.HotKeyBean;
import com.lqm.tomatoit.ui.adapter.ArticleListAdapter;
import com.lqm.tomatoit.ui.base.BaseActivity;
import com.lqm.tomatoit.ui.presenter.SearchPresenter;
import com.lqm.tomatoit.ui.view.SearchView;
import com.lqm.tomatoit.util.T;
import com.lqm.tomatoit.widget.AutoLinefeedLayout;
import com.lqm.tomatoit.widget.IconFontTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：搜索界面
 */

public class SearchActivity extends BaseActivity<SearchView, SearchPresenter>
        implements SearchView, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.rl_search)
    RelativeLayout rlSearch;
    @Bind(R.id.tv_return)
    TextView tvReturn;
    @Bind(R.id.tv_clean_input)
    IconFontTextView tvCleanInput;
    @Bind(R.id.layout_hot_key)
    AutoLinefeedLayout hotKeyLayout;
    @Bind(R.id.ll_hot_key)
    LinearLayout llHotKey;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    private ArticleListAdapter mArticleAdapter;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {

        rvContent.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mArticleAdapter = new ArticleListAdapter(SearchActivity.this, null);
        rvContent.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnLoadMoreListener(this, rvContent);

        mPresenter.getHotKeyData();

    }

    @Override
    public void initListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.searchData(etSearch.getText().toString());
            }
        });

    }

    @OnClick({R.id.tv_clean_input, R.id.tv_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_clean_input:
                etSearch.setText("");
                break;
            case R.id.tv_return:
                finish();
                break;
        }

    }

    @Override
    public void getHotKeySuccess(List<HotKeyBean> data) {
        hotKeyLayout.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View view = LinearLayout.inflate(SearchActivity.this, R.layout.item_hot_key, null);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(data.get(i).getName());
            hotKeyLayout.addView(view);
            int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etSearch.setText(data.get(finalI).getName());

                    // 将光标移至字符串尾部
                    CharSequence charSequence = etSearch.getText();
                    if (charSequence instanceof Spannable) {
                        Spannable spanText = (Spannable) charSequence;
                        Selection.setSelection(spanText, charSequence.length());
                    }
                }
            });
        }
    }

    @Override
    public void getHotKeyFail(String message) {
        T.showShort(SearchActivity.this, message);
    }

    @Override
    public void searchDataSuccess(List<ArticleBean> data) {
        if (data == null || data.size() == 0) {
            llHotKey.setVisibility(View.VISIBLE);
            rvContent.setVisibility(View.GONE);
        } else {
            llHotKey.setVisibility(View.GONE);
            rvContent.setVisibility(View.VISIBLE);
        }
        mArticleAdapter.setNewData(data);
    }

    @Override
    public void searchDataFail(String message) {
        T.showShort(SearchActivity.this, message);
    }

    @Override
    public void loadMoreDataSuccess(List<ArticleBean> data) {
        if (data.size() == 0) {
            mArticleAdapter.loadMoreEnd();
        } else {
            mArticleAdapter.addData(data);
            mArticleAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadMoreDataFail(String message) {
        T.showShort(SearchActivity.this, message);
    }

    @Override
    public void onLoadMoreRequested() {
        String keyWord = etSearch.getText().toString();
        if (!TextUtils.isEmpty(keyWord)) {
            mPresenter.getMoreData(etSearch.getText().toString());
        }
    }
}
