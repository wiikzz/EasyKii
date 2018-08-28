package com.wiikzz.library.core.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

/**
 * Created by wiikii on 2018/8/28.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class SpannableBuilder {
    // 用于快速创建SpannableString，省去大量代码
    private SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder();

    // 添加普通字串
    public SpannableBuilder append(CharSequence text) {
        return append(text, -1, true, null, false);
    }

    // 添加带颜色字串
    public SpannableBuilder append(CharSequence text, String colorString) {
        return append(text, -1, true, colorString, false);
    }

    // 添加指定大小字串
    public SpannableBuilder append(CharSequence text, int textSize, boolean dip) {
        return append(text, textSize, dip, null, false);
    }

    public SpannableBuilder append(CharSequence text, int textSize, boolean dip, String colorString) {
        return append(text, textSize, dip, colorString, false);
    }

    public SpannableBuilder appendBold(CharSequence text) {
        return append(text, -1, true, null, true);
    }

    public SpannableBuilder appendBold(CharSequence text, String colorString) {
        return append(text, -1, true, colorString, true);
    }

    public SpannableBuilder appendBold(CharSequence text, int textSize, boolean dip) {
        return append(text, textSize, dip, null, true);
    }

    public SpannableBuilder appendBold(CharSequence text, int textSize, boolean dip, String colorString) {
        return append(text, textSize, dip, colorString, true);
    }

    private SpannableBuilder append(CharSequence text, int textSize, boolean dip, String colorString, boolean bold) {
        if (!TextUtils.isEmpty(text)) {
            int start = mSpannableStringBuilder.length();
            mSpannableStringBuilder.append(text);

            if (textSize > 0) {
                mSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(textSize, dip),
                        start, mSpannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (bold) {
                mSpannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD),
                        start, mSpannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (!TextUtils.isEmpty(colorString)) {
                try {
                    int color = Color.parseColor(colorString);
                    mSpannableStringBuilder.setSpan(new ForegroundColorSpan(color),
                            start, mSpannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return this;
    }

    public SpannableStringBuilder build() {
        return mSpannableStringBuilder;
    }
}
