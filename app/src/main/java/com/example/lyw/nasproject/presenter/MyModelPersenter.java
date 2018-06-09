package com.example.lyw.nasproject.presenter;

import com.example.lyw.nasproject.base.RxPresenter;
import com.example.lyw.nasproject.base.contract.MyModelContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/8.
 */

public class MyModelPersenter extends RxPresenter<MyModelContract.View> implements MyModelContract.Presenter {
    @Inject
    public MyModelPersenter(){}
}
