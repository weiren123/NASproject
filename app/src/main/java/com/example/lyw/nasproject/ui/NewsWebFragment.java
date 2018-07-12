package com.example.lyw.nasproject.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
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
import com.orhanobut.logger.Logger;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/7.
 */

public class NewsWebFragment extends BaseFragment<NewsPresenter> implements NewsWebContract.View {
    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;
    private final static int MSG_PAGE_TIMEOUT = 1;

    private boolean flag =false;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MSG_PAGE_TIMEOUT :
                    //这里对已经显示出页面且加载超时的情况不做处理
                    Logger.e("ss"+MSG_PAGE_TIMEOUT);
                    webview.reload();
//                    if(webview != null && webview.getProgress() < 100)
//                    {ToastUtil.shortShow("网络异常"); }
                    break ;
            }
        }
    };
    private ScheduledThreadPoolExecutor scheduled;

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
//                mActivity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
                        Message m = new Message();
                        m.what = MSG_PAGE_TIMEOUT ;
                        mHandler.sendEmptyMessageDelayed(MSG_PAGE_TIMEOUT,3000);
//                    }
//                });
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
//            scheduled = new ScheduledThreadPoolExecutor(2);
//            scheduled.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    Message m = new Message();
//                    m.what = MSG_PAGE_TIMEOUT ;
//                    mHandler.sendMessage(m);
//                }
//            }, 15000, 15000, TimeUnit.MILLISECONDS);//0表示首次执行任务的延迟时间，15表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Logger.e("onPageFinished==url:=="+url);
            super.onPageFinished(view, url);
//            scheduled.shutdown();
            mHandler.removeMessages(MSG_PAGE_TIMEOUT);
        }
    }
}
