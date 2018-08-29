package com.wiikzz.library.core.utils;

import android.content.res.Resources;
import android.view.View;

import com.wiikzz.library.core.app.SystemInformation;

/**
 * Created by wiikii on 2018/8/29.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class UIUtils {
    private static final String STATUS_BAR_HEIGHT_RESOURCE_NAME = "status_bar_height";
    private static final String NAVIGATION_BAR_HEIGHT_RESOURCE_NAME = "navigation_bar_height";

    public int getScreenWidth() {
        return SystemInformation.getScreenWidth();
    }

    public int getScreenHeight() {
        return SystemInformation.getScreenHeight();
    }

    public float getScreenDensity() {
        return SystemInformation.getScreenDensity();
    }

    public float getScaleDensity() {
        return Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public int dimensionToPixel(float dimension) {
        return (int) Math.ceil(getScreenDensity() * dimension);
    }

    public int pixelToDimension(int pixel) {
        return (int) Math.ceil(pixel / getScreenDensity());
    }

    public int spToPixel(float sp) {
        return (int) Math.ceil(getScaleDensity() * sp);
    }

    public static int getViewWidth(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                (1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                (1 << 30) - 1, View.MeasureSpec.AT_MOST);
        view.measure(widthMeasureSpec,heightMeasureSpec);

        return view.getMeasuredWidth();
    }

    public static int getViewHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                (1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                (1 << 30) - 1, View.MeasureSpec.AT_MOST);
        view.measure(widthMeasureSpec,heightMeasureSpec);

        return view.getMeasuredHeight();
    }

    public static int getStatusBarHeight() {
        int resourceId = Resources.getSystem().getIdentifier(
                STATUS_BAR_HEIGHT_RESOURCE_NAME, "dimen","android");
        return Resources.getSystem().getDimensionPixelSize(resourceId);
    }

    public static int getNavigationBarHeight() {
        int resourceId = Resources.getSystem().getIdentifier(
                NAVIGATION_BAR_HEIGHT_RESOURCE_NAME, "dimen","android");
        return Resources.getSystem().getDimensionPixelSize(resourceId);
    }
}
