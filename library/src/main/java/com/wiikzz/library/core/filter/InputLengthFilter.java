package com.wiikzz.library.core.filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wiikii on 2018/8/28.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */

public class InputLengthFilter implements InputFilter {
    // 此类是为了保证EditText控件输入中英文都可以限制在指定个字符长度内(英文字符占一个字符，中文占2个字符)

    private int maxLength;

    public InputLengthFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (source == null || dest == null) {
            return "";
        }

        int keep = end;
        int destLength = getCountString(dest) - getCountString(dest.subSequence(dstart, dend));
        while ((destLength + getCountString(source.subSequence(start, keep))) > maxLength) {
            keep--;
            if (keep == start) {
                return "";
            }
        }

        if (keep == start) {
            return "";
        }

        if (keep >= end - start) {
            return null;
        }

        if (Character.isHighSurrogate(source.charAt(keep - 1))) {
            --keep;
            if (keep == start) {
                return "";
            }
        }

        return source.subSequence(start, keep);
    }

    private int getCountString(CharSequence string) {
        if (TextUtils.isEmpty(string)) {
            return 0;
        }

        int length = string.length();
        int chineseCount = countChinese(string);
        return chineseCount * 2 + (length - chineseCount);
    }

    // 计算字符串中中文字符数量.
    private static int countChinese(CharSequence str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }

        int count = 0;
        Pattern p = Pattern.compile("[^\\x00-\\xff]"); // 全角字符匹配
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }
}