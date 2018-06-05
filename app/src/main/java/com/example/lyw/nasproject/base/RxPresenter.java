package com.example.lyw.nasproject.base;

import com.example.lyw.nasproject.presenter.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by codeest on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

//    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
//        if (mCompositeDisposable == null) {
//            mCompositeDisposable = new CompositeDisposable();
//        }
//        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
//    }

    @Override
    public void attchView(T view) {
        this.mView = view;
    }

    @Override
    public void detchView() {
        this.mView = null;
        unSubscribe();
    }
}
