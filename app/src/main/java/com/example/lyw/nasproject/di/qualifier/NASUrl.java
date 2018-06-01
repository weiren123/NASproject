package com.example.lyw.nasproject.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2018/4/4.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface NASUrl {
}
