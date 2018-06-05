package com.example.lyw.nasproject.di.component;

import android.app.Activity;

import com.example.lyw.nasproject.di.module.ActivityModule;
import com.example.lyw.nasproject.di.spoce.ActivityScope;
import com.example.lyw.nasproject.ui.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/1.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);
}

