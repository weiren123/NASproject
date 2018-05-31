package com.example.lyw.nasproject.di;

import com.example.lyw.nasproject.app.App;

import javax.inject.Inject;

import dagger.Module;

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
}
