package com.example.lyw.nasproject.ui;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.base.BaseFragment;
import com.example.lyw.nasproject.base.contract.MyModelContract;
import com.example.lyw.nasproject.presenter.MyModelPersenter;

/**
 * Created by Administrator on 2018/6/8.
 */

public class MyModelFragment extends BaseFragment<MyModelPersenter> implements MyModelContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_model;
    }

    @Override
    protected void initInject() {
        getFragmentComponet().inject(this);
    }

    @Override
    protected void initEventOrData() {
    }
}
