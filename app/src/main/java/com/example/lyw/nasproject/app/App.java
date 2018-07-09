package com.example.lyw.nasproject.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.lyw.nasproject.di.component.AppComponent;
import com.example.lyw.nasproject.di.component.DaggerAppComponent;
import com.example.lyw.nasproject.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/31.
 */

public class App extends Application {
    private static App instance;
    private static AppComponent appComponent;
    private Set<Activity> allActivities;

    public static synchronized App getInstance(){return instance;}
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化极光
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        String registrationID = JPushInterface.getRegistrationID(getApplicationContext());
//        Logger.e("registrationID:"+registrationID);
    }

    /**
     * 内存管理
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }


    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if ( allActivities!= null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if(appComponent==null){
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
}
