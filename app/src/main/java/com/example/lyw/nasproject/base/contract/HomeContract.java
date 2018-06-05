package com.example.lyw.nasproject.base.contract;

import com.example.lyw.nasproject.base.BaseView;
import com.example.lyw.nasproject.presenter.BasePresenter;

/**
 * Created by Administrator on 2018/6/5.
 */

public interface HomeContract {
    interface View extends BaseView{
        void showContent();
    }
    interface Presenter extends BasePresenter<View>{
        void getHomeData();
    }
}
