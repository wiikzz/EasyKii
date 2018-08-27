package com.wiikzz.library.core.log;

import android.util.Log;

import com.wiikzz.library.core.app.KiiConfiguration;

/**
 * Created by wiikii on 2018/8/21.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class Logger {
    private static final String KII_BASE_TAG = KiiConfiguration.getAppBaseTag();
    public static final boolean DEBUG = KiiConfiguration.isDebugMode();

    public static void v(String tag, String message) {
        if (DEBUG) {
            Log.v(buildTag(tag), buildMessage(message));
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(buildTag(tag), buildMessage(message));
        }
    }

    public static void d(String tag, String message, Throwable throwable) {
        if (DEBUG) {
            Log.d(buildTag(tag), buildMessage(message), throwable);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(buildTag(tag), buildMessage(message));
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.w(buildTag(tag), buildMessage(message));
        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(buildTag(tag), buildMessage(message));
        }
    }

    public static void e(String tag, String message, Throwable tr) {
        if (DEBUG) {
            Log.e(buildTag(tag), buildMessage(message), tr);
        }
    }

    public static void wtf(String tag, String message) {
        if (DEBUG) {
            Log.wtf(buildTag(tag), buildMessage(message));
        }
    }

    public static void wtf(String tag, String message, Throwable tr) {
        if (DEBUG) {
            Log.wtf(buildTag(tag), buildMessage(message), tr);
        }
    }

    private static String buildTag(String tag) {
        return KII_BASE_TAG + "[" + tag + "]";
    }

    private static String buildMessage(String message) {
        Thread currentThread = Thread.currentThread();
        return "[" + currentThread.getName() + "(" + currentThread.getId() + ")] -->" + message;
    }
}
