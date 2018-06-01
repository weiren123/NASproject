package com.example.lyw.nasproject.presenter;

import com.example.lyw.nasproject.base.BaseView;

/**
 * Created by Administrator on 2018/5/31.
 */

public interface BasePresenter<T extends BaseView> {
    void  attchView(T view);

    void  detchView();
}
