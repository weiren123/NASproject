package com.example.lyw.nasproject.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lyw.nasproject.app.App;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/1.
 */

public class ImplPreferencehelper implements Preferenceshelper {
    private static final String SHAREDPREFERENCES_NAME = "my_sp";
    private final SharedPreferences mSPrefs;
    @Inject
    public ImplPreferencehelper(){
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
