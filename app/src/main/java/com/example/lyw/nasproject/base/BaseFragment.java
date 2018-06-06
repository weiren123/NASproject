package com.example.lyw.nasproject.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lyw.nasproject.app.App;
import com.example.lyw.nasproject.di.component.DaggerFragmentComponent;
import com.example.lyw.nasproject.di.component.FragmentComponent;
import com.example.lyw.nasproject.di.module.FragmentModule;
import com.example.lyw.nasproject.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/5.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{
    @Inject
    protected T mPresenter;

    private Unbinder unbinder;
    protected Activity mActivity;
    protected Context mContext;

    protected FragmentComponent getFragmentComponet(){
        return DaggerFragmentComponent.builder().appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }
    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }
    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initInject();
        mPresenter.attchView(this);
        initEventOrData();
    }

    protected abstract void initInject();

    protected abstract void initEventOrData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null){
            mPresenter.detchView();
        }
    }
}
