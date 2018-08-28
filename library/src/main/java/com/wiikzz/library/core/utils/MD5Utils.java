package com.wiikzz.library.core.utils;

import android.os.Build;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by wiikii on 2018/8/27.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class MD5Utils {
    private static final String ALGORITHM = "MD5";

    private static final char [] HEX_CHAR = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String bytesToHexString(byte[] byteSource) {
        if (byteSource == null || byteSource.length <= 0) {
            return null;
        }

        StringBuilder stringBuffer = new StringBuilder(byteSource.length * 2);
        for(int index = 0; index < byteSource.length; ++index) {
            stringBuffer.append(HEX_CHAR[byteSource[index] >> 4 & 0xF]);
            stringBuffer.append(HEX_CHAR[byteSource[index] & 0xF]);
        }

        return stringBuffer.toString();
    }

    public static String getMd5ForString(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(value.getBytes());
            return bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMd5ForFile(String absoluteFilePath) {
        if (!TextUtils.isEmpty(absoluteFilePath)) {
            return getMd5ForFile(new File(absoluteFilePath));
        } else {
            return null;
        }
    }

    public static String getMd5ForFile(File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return getFileMd5AboveHoneycomb(file);
        } else {
            return  getFileMd5BelowHoneycomb(file);
        }
    }


    private static String getFileMd5BelowHoneycomb(File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        FileInputStream fileInputStream = null;
        byte [] fileBuffer = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            fileInputStream = new FileInputStream(file);
            int readLength;
            while ((readLength = fileInputStream.read(fileBuffer, 0, fileBuffer.length)) != -1) {
                messageDigest.update(fileBuffer, 0, readLength);
            }
            return bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            return null;
        } finally {
            SystemUtils.closeStream(fileInputStream);
        }
    }

    // 此方法在2.3上可能出现ANR现象.
    private static String getFileMd5AboveHoneycomb(File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            fileInputStream = new FileInputStream(file);
            fileChannel = fileInputStream.getChannel();
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messageDigest.update(byteBuffer);
            return bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            return null;
        }
    }
}
