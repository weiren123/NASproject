package com.example.lyw.nasproject.model;

import com.example.lyw.nasproject.model.http.HttpHelper;
import com.example.lyw.nasproject.model.prefs.Preferenceshelper;

/**
 * Created by Administrator on 2018/6/1.
 */

public class DataManager implements HttpHelper,Preferenceshelper {
    private Preferenceshelper preferenceshelper;
    private HttpHelper httpHelper;

    public DataManager(HttpHelper httpHelper, Preferenceshelper preferenceshelper){
        this.httpHelper = httpHelper;
        this.preferenceshelper = preferenceshelper;
    }
}
