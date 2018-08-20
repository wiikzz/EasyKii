package com.wiikzz.library.core.storage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wiikzz.library.core.storage.SPManager;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by wiikii on 2018/8/14.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class SPManagerTest {

    @Test
    public void testSPString() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_string_a";
        SPManager.removeKey(appContext, spKey);

        String a = SPManager.getValue(appContext, spKey, null);
        assertEquals(a, null);

        a = SPManager.getValue(appContext, spKey, "");
        assertEquals(a, "");

        a = SPManager.getValue(appContext, spKey, "SP$%#@Default)(*&^%$#@!~`Value");
        assertEquals(a, "SP$%#@Default)(*&^%$#@!~`Value");

        String setValue = "ABCDEFGHIJKLNMOPQRSTUVWXYZ0123456789";
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, null);
        assertEquals(a, setValue);

        setValue = "";
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, null);
        assertEquals(a, setValue);

        setValue = "Love";
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, null);
        assertEquals(a, setValue);

        SPManager.setValue(appContext, spKey, null);
        a = SPManager.getValue(appContext, spKey, null);
        assertEquals(a, setValue);

        setValue = "";
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, null);
        assertEquals(a, setValue);
    }

    @Test
    public void testSPInteger() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_integer_a";
        SPManager.removeKey(appContext, spKey);

        int a = SPManager.getValue(appContext, spKey, -1);
        assertEquals(-1, a);

        int setValue = Integer.MAX_VALUE;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1);
        assertEquals(setValue, a);

        setValue = Integer.MIN_VALUE;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1);
        assertEquals(setValue, a);

        setValue = 1267853;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1);
        assertEquals(setValue, a);

        setValue = 0;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1);
        assertEquals(setValue, a);
    }


    @Test
    public void testSPLong() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_long_a";
        SPManager.removeKey(appContext, spKey);

        long a = SPManager.getValue(appContext, spKey, -1L);
        assertEquals(-1L, a);

        long setValue = Long.MAX_VALUE;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1L);
        assertEquals(setValue, a);

        setValue = Long.MIN_VALUE;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1L);
        assertEquals(setValue, a);

        setValue = 1267853L;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1L);
        assertEquals(setValue, a);

        setValue = 0L;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1L);
        assertEquals(setValue, a);
    }


    @Test
    public void testSPFloat() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_float_a";
        SPManager.removeKey(appContext, spKey);

        float a = SPManager.getValue(appContext, spKey, -1f);
        assertEquals(-1f, a, 0f);

        float setValue = Float.MAX_VALUE;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1f);
        assertEquals(setValue, a, 0f);

        setValue = Float.MIN_VALUE;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1f);
        assertEquals(setValue, a, 0f);

        setValue = 1267853.9876543f;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1f);
        assertEquals(setValue, a, 0f);

        setValue = 0f;
        SPManager.setValue(appContext, spKey, setValue);
        a = SPManager.getValue(appContext, spKey, -1f);
        assertEquals(setValue, a, 0f);
    }


    @Test
    public void testSPBoolean() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_boolean_a";
        SPManager.removeKey(appContext, spKey);

        boolean a = SPManager.getValue(appContext, spKey, false);
        assertEquals(false, a);

        a = SPManager.getValue(appContext, spKey, true);
        assertEquals(true, a);

        SPManager.setValue(appContext, spKey, true);
        a = SPManager.getValue(appContext, spKey, false);
        assertEquals(true, a);

        a = SPManager.getValue(appContext, spKey, true);
        assertEquals(true, a);

        SPManager.setValue(appContext, spKey, false);
        a = SPManager.getValue(appContext, spKey, false);
        assertEquals(false, a);

        a = SPManager.getValue(appContext, spKey, true);
        assertEquals(false, a);
    }

    @Test
    public void testSPStringArray() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_string_array_a";
        SPManager.removeKey(appContext, spKey);

        String [] a = SPManager.getStringArray(appContext, spKey);
        assertArrayEquals(null, a);

        String [] setValue = new String[] {"Hello", "", "Picker", "6666"};
        SPManager.setStringArray(appContext, spKey, setValue);
        a = SPManager.getStringArray(appContext, spKey);
        assertArrayEquals(setValue, a);

        SPManager.setStringArray(appContext, spKey, null);
        a = SPManager.getStringArray(appContext, spKey);
        assertArrayEquals(setValue, a);

        SPManager.setStringArray(appContext, spKey, new String[] {});
        a = SPManager.getStringArray(appContext, spKey);
        assertArrayEquals(setValue, a);

        setValue = new String[] {"Hello", "", "Picker9", "6666", "Hello1", "", "Picker", "6666", "Hello", "",
                "Picker", "6666", "Hello", "", "Picker7", "6666", "Hello2", "", "Picker", "6666", "Hello", "",
                "Picker", "6666", "Hello", "", "Picker5", "6666", "Hello3", "", "Picker", "6666", "Hello", "",
                "Picker", "6666", "Hello", "", "Picker1", "6666", "Hello4", "", "Picker", "6666", "Hello", "",
                "Picker", "6666"};
        SPManager.setStringArray(appContext, spKey, setValue);
        a = SPManager.getStringArray(appContext, spKey);
        assertArrayEquals(setValue, a);
    }

    @Test
    public void testSPStringList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_string_list_a";
        SPManager.removeKey(appContext, spKey);

        List<String> a = SPManager.getStringList(appContext, spKey);
        assertEquals(null, a);

        List<String> setValue = new ArrayList<>();
        setValue.add("");
        setValue.add("Hello");
        setValue.add("987654321");
        SPManager.setStringList(appContext, spKey, setValue);
        a = SPManager.getStringList(appContext, spKey);
        assertEquals(setValue, a);

        setValue = new ArrayList<>();
        setValue.add(null);
        setValue.add(null);
        setValue.add(null);
        SPManager.setStringList(appContext, spKey, setValue);
        a = SPManager.getStringList(appContext, spKey);
        assertEquals(setValue, a);

        setValue = new ArrayList<>();
        setValue.add("");
        setValue.add(null);
        setValue.add("---");
        SPManager.setStringList(appContext, spKey, setValue);
        a = SPManager.getStringList(appContext, spKey);
        assertEquals(setValue, a);
    }

    @Test
    public void testSPIntegerArray() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_integer_array_a";
        SPManager.removeKey(appContext, spKey);

        int [] a = SPManager.getIntegerArray(appContext, spKey);
        assertArrayEquals(null, a);

        int [] setValue = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, -1, 0, 1};
        SPManager.setIntegerArray(appContext, spKey, setValue);
        a = SPManager.getIntegerArray(appContext, spKey);
        assertArrayEquals(setValue, a);

        Integer b = 100;
        setValue = new int[] {b, Integer.MAX_VALUE, -1, 0, 1};
        SPManager.setIntegerArray(appContext, spKey, setValue);
        a = SPManager.getIntegerArray(appContext, spKey);
        assertArrayEquals(setValue, a);
    }

    @Test
    public void testSPIntegerList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String spKey = "key_integer_list_a";
        SPManager.removeKey(appContext, spKey);

        List<Integer> a = SPManager.getIntegerList(appContext, spKey);
        assertEquals(null, a);

        List<Integer> setValue = new ArrayList<>();
        setValue.add(2);
        setValue.add(7);
        setValue.add(0);
        SPManager.setIntegerList(appContext, spKey, setValue);
        a = SPManager.getIntegerList(appContext, spKey);
        assertEquals(setValue, a);

        setValue = new ArrayList<>();
        setValue.add(-1);
        setValue.add(Integer.MAX_VALUE);
        setValue.add(Integer.MIN_VALUE);
        SPManager.setIntegerList(appContext, spKey, setValue);
        a = SPManager.getIntegerList(appContext, spKey);
        assertEquals(setValue, a);

        setValue = new ArrayList<>();
        setValue.add(-128372382);
        setValue.add(98766);
        setValue.add(20);
        SPManager.setIntegerList(appContext, spKey, setValue);
        a = SPManager.getIntegerList(appContext, spKey);
        assertEquals(setValue, a);
    }
}
