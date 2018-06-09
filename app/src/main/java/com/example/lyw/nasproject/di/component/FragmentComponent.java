package com.example.lyw.nasproject.di.component;

import android.app.Activity;

import com.example.lyw.nasproject.di.module.FragmentModule;
import com.example.lyw.nasproject.di.spoce.FragmentScope;
import com.example.lyw.nasproject.ui.HomeFragment;
import com.example.lyw.nasproject.ui.MyModelFragment;
import com.example.lyw.nasproject.ui.NewsWebFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/5.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(HomeFragment homeFragment);

    void inject(NewsWebFragment newsWebFragment);

    void inject(MyModelFragment myModelFragment);
}
