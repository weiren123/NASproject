package com.example.lyw.nasproject.di.module;

import com.example.lyw.nasproject.app.App;
import com.example.lyw.nasproject.model.DataManager;
import com.example.lyw.nasproject.model.http.HttpHelper;
import com.example.lyw.nasproject.model.http.RetrofitHelper;
import com.example.lyw.nasproject.model.prefs.ImplPreferencehelper;
import com.example.lyw.nasproject.model.prefs.Preferenceshelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/31.
 */
@Module
public class AppModule {
    private App appliction;
    @Inject
    public AppModule(App instance) {
        this.appliction = instance;
    }

    @Provides
    @Singleton
    public App providesApplicationContext(){
        return appliction;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    Preferenceshelper providePreferencesHelper(ImplPreferencehelper implPreferencesHelper) {
        return implPreferencesHelper;
    }
    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, Preferenceshelper preferenceshelper) {
        return new DataManager(httpHelper,preferenceshelper);
    }

}
