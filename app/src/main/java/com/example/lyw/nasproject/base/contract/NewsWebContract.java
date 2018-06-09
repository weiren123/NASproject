package com.example.lyw.nasproject.base.contract;

import com.example.lyw.nasproject.base.BaseView;
import com.example.lyw.nasproject.presenter.BasePresenter;

/**
 * Created by Administrator on 2018/6/7.
 */

public interface NewsWebContract {
    interface View extends BaseView{
        void loadNews();
    }
    interface Presenter extends BasePresenter<View>{
        void getNewsData();
    }
}
