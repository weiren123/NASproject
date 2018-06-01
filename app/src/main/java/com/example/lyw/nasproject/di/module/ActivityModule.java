package com.example.lyw.nasproject.di.module;

import android.app.Activity;

import com.example.lyw.nasproject.di.spoce.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/1.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }
    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return mActivity;
    }
}
