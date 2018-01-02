package com.lqm.tomatoit.util;

import android.content.Context;
import android.content.res.Resources;

import com.lqm.tomatoit.app.App;

/**
 * @创建者 CSDN_LQR
 * @描述 和ui相关的工具类
 */
public class UIUtils {

    /**
     * 得到上下文
     *
     * @return
     */
    public static Context getContext() {
        return App.getmContext();
    }

    /**
     * 得到resources对象
     *
     * @return
     */
    public static Resources getResource() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的字符串
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    /**
     * 得到string.xml中的字符串，带点位符
     *
     * @return
     */
    public static String getString(int id, Object... formatArgs) {
        return getResource().getString(id, formatArgs);
    }

    /**
     * 得到string.xml中和字符串数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArr(int resId) {
        return getResource().getStringArray(resId);
    }

    /**
     * 得到colors.xml中的颜色
     * @param colorId
     * @return
     */
    public static int getColor(int colorId) {
        return getResource().getColor(colorId);
    }

    /**
     * dip-->px
     */
    public static int dp2px(int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density = getResource().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2dp(int px) {

        float density = getResource().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

}
