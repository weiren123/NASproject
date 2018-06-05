package com.example.lyw.nasproject.presenter;

import com.example.lyw.nasproject.base.RxPresenter;
import com.example.lyw.nasproject.base.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/5.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    @Inject
    public HomePresenter(){}
    @Override
    public void getHomeData() {
        mView.showContent();
    }
}
