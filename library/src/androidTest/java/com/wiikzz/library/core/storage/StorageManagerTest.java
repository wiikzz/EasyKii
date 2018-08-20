package com.wiikzz.library.core.storage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wiikii on 2018/8/20.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */

@RunWith(AndroidJUnit4.class)
public class StorageManagerTest {

    @Test
    public void testStorageManager() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        File dir = StorageManager.getDiskCacheDirectory(appContext, "testCache");
        assertNotNull(dir);
        assertFalse(dir.exists() && dir.isDirectory());

        dir = StorageManager.getUpdateStorageDirectory();
        assertNotNull(dir);
        assertFalse(dir.exists() && dir.isDirectory());

        dir = StorageManager.getDownloadStorageDirectory();
        assertNotNull(dir);
        assertFalse(dir.exists() && dir.isDirectory());

        dir = StorageManager.getAppCacheDirectory(appContext);
        assertNotNull(dir);
        assertFalse(dir.exists() && dir.isDirectory());
    }
}
