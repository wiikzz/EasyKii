package com.wiikzz.library.core.app;

import com.wiikzz.library.BuildConfig;

/**
 * Created by wiikii on 2018/8/27.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class KiiConfiguration {

    /* 项目集成后，改成项目的BuildConfig.DEBUG值 */
    public static boolean isDebugMode() {
        return BuildConfig.DEBUG;
    }

    /* 根据各集成项目自己的情况来自己定义 */
    public static String getAppBaseTag() {
        return "EasyKii";
    }
}
