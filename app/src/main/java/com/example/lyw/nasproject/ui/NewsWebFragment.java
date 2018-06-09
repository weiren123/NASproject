package com.example.lyw.nasproject.ui;

import android.webkit.WebView;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.base.BaseFragment;
import com.example.lyw.nasproject.base.contract.NewsWebContract;
import com.example.lyw.nasproject.presenter.NewsPresenter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/7.
 */

public class NewsWebFragment extends BaseFragment<NewsPresenter> implements NewsWebContract.View {
    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_web;
    }

    @Override
    protected void initInject() {
        getFragmentComponet().inject(this);
    }

    @Override
    protected void initEventOrData() {
        mPresenter.getNewsData();
    }

    @Override
    public void loadNews() {
        webview.loadUrl("http://m.bishijie.com/kuaixun");
    }

}
