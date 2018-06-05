package com.example.lyw.nasproject.presenter;

import com.example.lyw.nasproject.base.RxPresenter;
import com.example.lyw.nasproject.base.contract.MainContract;
import com.example.lyw.nasproject.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/5.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getMainData() {
        mView.showFragment();
    }
}
