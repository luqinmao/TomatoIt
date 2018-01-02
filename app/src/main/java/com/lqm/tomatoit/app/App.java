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

    private static Context mContext;//上下文

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();


        initOkGo();
        initAutoLayout();

    }

    /**
     * 配置AutoLayout
     */
    private void initAutoLayout() {
        //默认使用的高度是设备的可用高度，也就是不包括状态栏和底部的操作栏的，以下配置可以拿到设备的物理高度进行百分比
//        AutoLayoutConifg.getInstance().useDeviceSize();
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

    public static Context getmContext() {
        return mContext;
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