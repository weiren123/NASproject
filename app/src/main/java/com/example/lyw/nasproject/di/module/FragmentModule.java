package com.example.lyw.nasproject.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.lyw.nasproject.di.spoce.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/5.
 */
@Module
public class FragmentModule {
    private Fragment frament;

    public FragmentModule(Fragment fragment){
        this.frament = fragment;
    }
    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return frament.getActivity();
    }
}
