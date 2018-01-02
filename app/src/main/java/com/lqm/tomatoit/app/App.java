package com.lqm.tomatoit.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

import java.util.LinkedList;
import java.util.List;

/**
 * user：lqm
 * desc：
 */

public class App extends Application {

    public static List<Activity> activities = new LinkedList<>();
    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this.getApplicationContext();

        initOkGo();

    }

    /**
     * 初始化okgo
     */
    private void initOkGo() {
        OkGo.getInstance()
                .init(this)
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);
    }

    /**
     * 退出程序
     */
    public static void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

}