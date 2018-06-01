package com.example.lyw.nasproject.di.component;

import com.example.lyw.nasproject.app.App;
import com.example.lyw.nasproject.di.module.AppModule;
import com.example.lyw.nasproject.di.module.HttpModule;
import com.example.lyw.nasproject.model.DataManager;
import com.example.lyw.nasproject.model.http.RetrofitHelper;
import com.example.lyw.nasproject.model.prefs.ImplPreferencehelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/31.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
//
    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    ImplPreferencehelper preferencesHelper();//提供sp的帮助类

}
