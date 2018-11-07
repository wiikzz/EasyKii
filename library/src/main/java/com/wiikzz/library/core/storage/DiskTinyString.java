package com.wiikzz.library.core.storage;

import android.content.Context;
import android.text.TextUtils;

import com.jakewharton.disklrucache.DiskLruCache;
import com.wiikzz.library.core.app.SystemInformation;
import com.wiikzz.library.core.utils.SystemUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DiskTinyString {

    private static final int BUFFER_SIZE = 8 * 1024;
    private static final long CACHE_MAX_SIZE = 8 * 1024 * 1024;
    private static final String CACHE_DIR_NAME = "tinyString";
    private DiskLruCache mDiskLruCache;

    private static DiskTinyString _INSTANCE;

    private DiskTinyString(Context context) {
        try {
            mDiskLruCache = DiskLruCache.open(StorageManager.getDiskCacheDirectory(context, CACHE_DIR_NAME),
                    SystemInformation.getVersionCode(context), 1, CACHE_MAX_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  A secondary method used to convert a string to a stream
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SystemUtils.closeStream(reader);
            SystemUtils.closeStream(is);
        }

        return sb.toString();
    }

    private String getString(String key) {
        if (TextUtils.isEmpty(key) || mDiskLruCache == null) {
            return null;
        }

        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = mDiskLruCache.get(key);
            if (snapshot == null) {
                return null;
            }

            InputStream inputStream = snapshot.getInputStream(0);
            if (inputStream != null) {
                return convertStreamToString(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SystemUtils.closeStream(snapshot);
        }

        return null;
    }

    private boolean writeStringToFile(String string, DiskLruCache.Editor editor) {
        if (TextUtils.isEmpty(string) || editor == null) {
            return false;
        }

        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(editor.newOutputStream(0), BUFFER_SIZE);
            bufferedOutputStream.write(string.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            SystemUtils.closeStream(bufferedOutputStream);
        }
    }

    private void putString(String key, String value) {
        if (mDiskLruCache == null) {
            return;
        }

        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return;
        }
        DiskLruCache.Editor editor = null;
        try {
            editor = mDiskLruCache.edit(key);
            if (writeStringToFile(value, editor)) {
                editor.commit();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean invalid() {
        return mDiskLruCache == null;
    }

    private static DiskTinyString getInstance(Context context) {
        if (_INSTANCE == null || _INSTANCE.invalid()) {
            synchronized (DiskTinyString.class) {
                if (_INSTANCE == null || _INSTANCE.invalid()) {
                    _INSTANCE = new DiskTinyString(context);
                }
            }
        }

        return _INSTANCE;
    }

    public static String getString(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }

        try {
            return getInstance(context).getString(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static void putString(Context context, String key, String value) {
        if (context == null) {
            return;
        }

        try {
            getInstance(context).putString(key, value);
        } catch (Exception e) {
            // do nothing
        }
    }
}
