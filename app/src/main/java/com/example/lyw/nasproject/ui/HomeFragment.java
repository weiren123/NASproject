package com.example.lyw.nasproject.ui;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.base.BaseFragment;
import com.example.lyw.nasproject.base.contract.HomeContract;
import com.example.lyw.nasproject.presenter.HomePresenter;

/**
 * Created by Administrator on 2018/6/5.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @Override
    public void showContent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getFragmentComponet().inject(this);
    }

    @Override
    protected void initEventOrData() {
        mPresenter.getHomeData();
    }
}
