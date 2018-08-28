package com.wiikzz.library.core.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by wiikii on 2018/8/28.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class SystemUtils {

    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
