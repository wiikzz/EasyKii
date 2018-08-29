package com.wiikzz.library.core.app;

import android.content.res.Resources;

/**
 * Created by wiikii on 2018/8/28.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class SystemInformation {
    // 整理系统相关参数及数据

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }
}
