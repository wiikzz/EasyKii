package com.wiikzz.library.core.storage;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by wiikii on 2018/8/20.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class StorageManager {
    private static final String STORAGE_APP_DIRECTORY_NAME = "EasyKii";

    private static final String STORAGE_APP_UPDATE_DIR_NAME = "update";
    private static final String STORAGE_APP_DOWNLOAD_DIR_NAME = "download";

    // 获取SD卡的路径
    public static String getExternalStoragePath() {
        File externalStorage = Environment.getExternalStorageDirectory();
        return externalStorage.getAbsolutePath();
    }

    /**
     * 获取缓存目录，并根据cacheCategory的值在缓存目录中创建该文件夹（如不存在）；
     * 首先SD卡缓存目录，其次应用目录。
     * @param context 上下文环境
     * @param cacheCategory 缓存分类，也是创建缓存文件夹的名称
     * @return 该分类的缓存目录。
     */
    public static File getDiskCacheDirectory(Context context, String cacheCategory) {
        if (context == null || TextUtils.isEmpty(cacheCategory)) {
            return null;
        }

        try {
            File cacheDirectory = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    || !Environment.isExternalStorageRemovable()) {
                cacheDirectory = context.getExternalCacheDir();
            }

            if (cacheDirectory == null || !cacheDirectory.exists()) {
                cacheDirectory = context.getCacheDir();
            }

            if (cacheDirectory == null || !cacheDirectory.exists()) {
                return null;
            }

            File categoryCacheDirectory = new File(cacheDirectory, cacheCategory);
            if (!categoryCacheDirectory.exists()) {
                categoryCacheDirectory.mkdirs();
            }

            return categoryCacheDirectory;
        } catch (Throwable throwable) {
            return null;
        }
    }

    // 获取应用缓存目录
    public static File getAppCacheDirectory(Context context) {
        if (context == null) {
            return null;
        }

        return context.getCacheDir();
    }

    public static File getAppStorageDirectory() {
        try {
            File externalStorage = Environment.getExternalStorageDirectory();
            File appStorageDirectory = new File(externalStorage, STORAGE_APP_DIRECTORY_NAME);
            if (!appStorageDirectory.exists()) {
                appStorageDirectory.mkdirs();
            }

            return appStorageDirectory;
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static File getUpdateStorageDirectory() {
        File appStorageDirectory = getAppStorageDirectory();
        if (appStorageDirectory != null) {
            File directory = new File(appStorageDirectory, STORAGE_APP_UPDATE_DIR_NAME);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            return directory;
        } else {
            return null;
        }
    }

    public static File getDownloadStorageDirectory() {
        File appStorageDirectory = getAppStorageDirectory();
        if (appStorageDirectory != null) {
            File directory = new File(appStorageDirectory, STORAGE_APP_DOWNLOAD_DIR_NAME);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            return directory;
        } else {
            return null;
        }
    }
}
