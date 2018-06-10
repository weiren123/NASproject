package com.example.lyw.nasproject.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.base.BaseFragment;
import com.example.lyw.nasproject.base.contract.NewsWebContract;
import com.example.lyw.nasproject.presenter.NewsPresenter;
import com.example.lyw.nasproject.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/7.
 */

public class NewsWebFragment extends BaseFragment<NewsPresenter> implements NewsWebContract.View {
    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;
    private boolean flag =false;

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
        // 修改ua使得web端正确判断
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        String ua = webview.getSettings().getUserAgentString();
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setUserAgentString(ua+" BSJApp");
        webview.setWebViewClient(new MyWebVewClient());
        webview.loadUrl("http://m.bishijie.com/kuaixun");
    }
    @TargetApi(21)
    class MyWebVewClient extends WebViewClient{

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            Logger.e("shouldInterceptRequest:=="+request.getUrl());
            if(request.getUrl().toString().contains("loading.gif")) {
                flag = true;
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.shortShow("功能尚未开发！");
                        webview.reload();//在这里中断连接
                    }
                });
            }
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Logger.e("url:=="+request.getUrl());
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Logger.e("onPageStarted==url:=="+url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Logger.e("onPageFinished==url:=="+url);
            super.onPageFinished(view, url);
        }
    }
}
