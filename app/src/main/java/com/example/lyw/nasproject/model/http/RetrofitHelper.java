package com.example.lyw.nasproject.model.http;

import com.example.lyw.nasproject.model.Apis;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/1.
 */

public class RetrofitHelper implements HttpHelper {
    private Apis apis;

    @Inject
    public RetrofitHelper(Apis apis){
        this.apis = apis;
    }
}
