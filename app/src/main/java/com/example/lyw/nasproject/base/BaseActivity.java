package com.example.lyw.nasproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.lyw.nasproject.app.App;
import com.example.lyw.nasproject.di.component.ActivityComponent;
import com.example.lyw.nasproject.di.component.DaggerActivityComponent;
import com.example.lyw.nasproject.di.module.ActivityModule;
import com.example.lyw.nasproject.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/31.
 */

public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity implements BaseView{
    @Inject
    protected T mPresenter;
    private ActivityComponent activityComponent;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        App.getInstance().addActivity(this);
        mBind =ButterKnife.bind(this);
        initView();
        initEventAndData();
    }

    protected abstract void initEventAndData();

    public ActivityComponent getActivityComponten(){
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent.builder()
                    .appComponent(App.getAppComponent())
                    .activityModule(getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract int getLayoutView();

    protected void initView(){
        initInject();
        if(mPresenter!=null){
            mPresenter.attchView(this);
        }
    };
    protected abstract void initInject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detchView();
        }
        App.getInstance().removeActivity(this);
        mBind.unbind();
    }
}
