package com.example.lyw.nasproject.base.contract;

import com.example.lyw.nasproject.base.BaseView;
import com.example.lyw.nasproject.presenter.BasePresenter;

/**
 * Created by Administrator on 2018/6/8.
 */

public interface MyModelContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
