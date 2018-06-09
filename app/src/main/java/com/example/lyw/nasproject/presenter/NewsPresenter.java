package com.example.lyw.nasproject.presenter;

import com.example.lyw.nasproject.base.RxPresenter;
import com.example.lyw.nasproject.base.contract.NewsWebContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/7.
 */

public class NewsPresenter extends RxPresenter<NewsWebContract.View> implements NewsWebContract.Presenter {
    @Inject
    public NewsPresenter(){}

    @Override
    public void getNewsData() {
        mView.loadNews();
    }
}
