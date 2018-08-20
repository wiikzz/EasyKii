package com.wiikzz.library.core.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wiikii on 16/4/5.
 * Copyright (C) 2018 wiikii. All rights reserved.
 */
public class SPManager {
    private static final String SPLIT_SYMBOL = "#`,`#";
    private static final String NULL_VALUE = "#`null`#";
    private static final String EMPTY_STRING = "";

    public static final int SP_TYPE_DEFAULT    = 0;
    public static final int SP_TYPE_EXTRA      = 1;

    @SuppressLint("UseSparseArrays")
    private static final HashMap<Integer, SharedPreferenceBase> sSharedPreferences = new HashMap<>();

    private static class SharedPreferencesDefault extends SharedPreferenceBase {
        @Override
        protected SharedPreferences getSharedPreferences(Context context) {
            if (context == null) {
                return null;
            }

            return PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    private static class SharedPreferencesExtra extends SharedPreferenceBase {
        private static final String SHARED_PREFERENCES_NAME = "shared_preferences_extra";

        @Override
        protected SharedPreferences getSharedPreferences(Context context) {
            if (context == null) {
                return null;
            }

            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
    }

    private static abstract class SharedPreferenceBase {
        private SharedPreferences mSharedPreferences;
        protected abstract SharedPreferences getSharedPreferences(Context context);

        private SharedPreferences getSPInternal(Context context) {
            if (context == null) {
                return null;
            }

            if (mSharedPreferences == null) {
                synchronized (SPManager.class) {
                    if (mSharedPreferences == null) {
                        mSharedPreferences = getSharedPreferences(context);
                    }
                }
            }

            return mSharedPreferences;
        }

        private SharedPreferences.Editor getEditor(Context context) {
            SharedPreferences sharedPreferences = getSPInternal(context);
            if (sharedPreferences != null) {
                return sharedPreferences.edit();
            } else {
                return null;
            }
        }

        private void setValueBase(Context context, String key, Object value) {
            if(context == null || TextUtils.isEmpty(key) || value == null) {
                return;
            }

            try {
                SharedPreferences.Editor editor = getEditor(context);
                if (editor == null) {
                    return;
                }

                if (value instanceof String) {
                    editor.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                } else if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                }  else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                }
                editor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Object getValueBase(Context context, String key, Object defaultValue) {
            if(context == null || TextUtils.isEmpty(key)) {
                return null;
            }

            try {
                SharedPreferences sharedPreferences = getSPInternal(context);
                if (sharedPreferences == null) {
                    return null;
                }

                if (defaultValue == null) {
                    return sharedPreferences.getString(key, null);
                }

                if (defaultValue instanceof String) {
                    return sharedPreferences.getString(key, (String) defaultValue);
                } else if (defaultValue instanceof Integer) {
                    return sharedPreferences.getInt(key, (Integer) defaultValue);
                } else if (defaultValue instanceof Boolean) {
                    return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
                } else if (defaultValue instanceof Float) {
                    return sharedPreferences.getFloat(key, (Float) defaultValue);
                } else if (defaultValue instanceof Long) {
                    return sharedPreferences.getLong(key, (Long) defaultValue);
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        // set value
        public void setValue(Context context, String key, String value) {
            setValueBase(context, key, value);
        }
        public void setValue(Context context, String key, int value) {
            setValueBase(context, key, value);
        }
        public void setValue(Context context, String key, boolean value) {
            setValueBase(context, key, value);
        }
        public void setValue(Context context, String key, float value) {
            setValueBase(context, key, value);
        }
        public void setValue(Context context, String key, long value) {
            setValueBase(context, key, value);
        }
        public void setStringArray(Context context, String key, String [] value) {
            if (value != null && value.length > 0) {
                StringBuilder sb = new StringBuilder();
                boolean firstTime = true;
                for (String token: value) {
                    if (firstTime) {
                        firstTime = false;
                    } else {
                        sb.append(SPLIT_SYMBOL);
                    }

                    if (token == null) {
                        sb.append(NULL_VALUE);
                    } else {
                        sb.append(token);
                    }
                }
                setValueBase(context, key, sb.toString());
            }
        }
        public void setStringList(Context context, String key, List<String> value) {
            if (value != null && value.size() > 0) {
                setValueBase(context, key, join(SPLIT_SYMBOL, value));
            }
        }
        public void setIntegerArray(Context context, String key, int [] value) {
            if (value != null && value.length > 0) {
                StringBuilder sb = new StringBuilder();
                boolean firstTime = true;
                for (int token: value) {
                    if (firstTime) {
                        firstTime = false;
                    } else {
                        sb.append(SPLIT_SYMBOL);
                    }
                    sb.append(token);
                }
                setValueBase(context, key, sb.toString());
            }
        }
        public void setIntegerList(Context context, String key, List<Integer> value) {
            if (value != null && value.size() > 0) {
                setValueBase(context, key, join(SPLIT_SYMBOL, value));
            }
        }

        // getvalue
        public String getValue(Context context, String key, String defaultValue) {
            Object object = getValueBase(context, key, defaultValue);
            if(object != null) {
                return (String) object;
            }
            return defaultValue;
        }
        public int getValue(Context context, String key, int defaultValue) {
            Object object = getValueBase(context, key, defaultValue);
            if(object != null) {
                return (int) object;
            }
            return defaultValue;
        }
        public boolean getValue(Context context, String key, boolean defaultValue) {
            Object object = getValueBase(context, key, defaultValue);
            if(object != null) {
                return (boolean) object;
            }
            return defaultValue;
        }
        public float getValue(Context context, String key, float defaultValue) {
            Object object = getValueBase(context, key, defaultValue);
            if(object != null) {
                return (float) object;
            }
            return defaultValue;
        }
        public long getValue(Context context, String key, long defaultValue) {
            Object object = getValueBase(context, key, defaultValue);
            if(object != null) {
                return (long) object;
            }
            return defaultValue;
        }

        public String [] getStringArray(Context context, String key) {
            if (TextUtils.isEmpty(key)) {
                return null;
            }

            String value = getValue(context, key, EMPTY_STRING);
            if (!TextUtils.isEmpty(value)) {
                String [] result = TextUtils.split(value, SPLIT_SYMBOL);
                for (int i = 0; i < result.length; i++) {
                    if (TextUtils.equals(result[i], NULL_VALUE)) {
                        result[i] = null;
                    }
                }
                return result;
            }

            return null;
        }

        public ArrayList<String> getStringList(Context context, String key) {
            if (TextUtils.isEmpty(key)) {
                return null;
            }

            String value = getValue(context, key, EMPTY_STRING);
            if (!TextUtils.isEmpty(value)) {
                String [] result = TextUtils.split(value, SPLIT_SYMBOL);
                for (int i = 0; i < result.length; i++) {
                    if (TextUtils.equals(result[i], NULL_VALUE)) {
                        result[i] = null;
                    }
                }
                return new ArrayList<>(Arrays.asList(result));
            }

            return null;
        }

        public int [] getIntegerArray(Context context, String key) {
            String value = getValue(context, key, EMPTY_STRING);
            if (TextUtils.isEmpty(value)) {
                return null;
            }

            String [] valueStringArray = TextUtils.split(value, SPLIT_SYMBOL);
            int [] result = new int[valueStringArray.length];
            for (int i = 0; i < valueStringArray.length; i++) {
                try {
                    // 不可能为空值
                    if (TextUtils.equals(valueStringArray[i], NULL_VALUE)) {
                        result[i] = 0;
                    } else {
                        result[i] = Integer.parseInt(valueStringArray[i]);
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            return result;
        }

        public List<Integer> getIntegerList(Context context, String key) {
            String value = getValue(context, key, EMPTY_STRING);
            if (TextUtils.isEmpty(value)) {
                return null;
            }

            String [] valueStringArray = TextUtils.split(value, SPLIT_SYMBOL);
            List<Integer> result = new ArrayList<>();
            for (String token : valueStringArray) {
                try {
                    if (TextUtils.equals(token, NULL_VALUE)) {
                        result.add(null);
                    } else {
                        result.add(Integer.parseInt(token));
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            return result;
        }

        // contains key
        public boolean containsKey(Context context, String key) {
            SharedPreferences sp = getSPInternal(context);
            return sp != null && sp.contains(key);
        }

        // remove key
        public boolean removeKey(Context context, String key) {
            SharedPreferences.Editor editor = getEditor(context);
            if (editor != null && !TextUtils.isEmpty(key)) {
                editor.remove(key);
                editor.commit();
                return true;
            }

            return false;
        }
    }

    public static SharedPreferenceBase sharedPref(int spType) {
        SharedPreferenceBase sp = sSharedPreferences.get(spType);
        if (sp == null) {
            synchronized (SPManager.class) {
                sp = sSharedPreferences.get(spType);
                if (sp == null) {
                    switch (spType) {
                        case SP_TYPE_EXTRA:
                            sp = new SharedPreferencesExtra();
                            break;
                        case SP_TYPE_DEFAULT:
                        default:
                            sp = new SharedPreferencesDefault();
                            break;
                    }

                    sSharedPreferences.put(spType, sp);
                }
            }
        }

        return sp;
    }

    // setvalue  使用默认SharedPreferences
    public static void setValue(Context context, String key, String value) {
        sharedPref(SP_TYPE_DEFAULT).setValue(context, key, value);
    }
    public static void setValue(Context context, String key, int value) {
        sharedPref(SP_TYPE_DEFAULT).setValue(context, key, value);
    }
    public static void setValue(Context context, String key, boolean value) {
        sharedPref(SP_TYPE_DEFAULT).setValue(context, key, value);
    }
    public static void setValue(Context context, String key, float value) {
        sharedPref(SP_TYPE_DEFAULT).setValue(context, key, value);
    }
    public static void setValue(Context context, String key, long value) {
        sharedPref(SP_TYPE_DEFAULT).setValue(context, key, value);
    }
    public static void setStringArray(Context context, String key, String [] value) {
        sharedPref(SP_TYPE_DEFAULT).setStringArray(context, key, value);
    }
    public static void setStringList(Context context, String key, List<String> value) {
        sharedPref(SP_TYPE_DEFAULT).setStringList(context, key, value);
    }
    public static void setIntegerArray(Context context, String key, int [] value) {
        sharedPref(SP_TYPE_DEFAULT).setIntegerArray(context, key, value);
    }
    public static void setIntegerList(Context context, String key, List<Integer> value) {
        sharedPref(SP_TYPE_DEFAULT).setIntegerList(context, key, value);
    }

    // getvalue  使用默认SharedPreferences
    public static String getValue(Context context, String key, String defaultValue) {
        return sharedPref(SP_TYPE_DEFAULT).getValue(context, key, defaultValue);
    }
    public static int getValue(Context context, String key, int defaultValue) {
        return sharedPref(SP_TYPE_DEFAULT).getValue(context, key, defaultValue);
    }
    public static boolean getValue(Context context, String key, boolean defaultValue) {
        return sharedPref(SP_TYPE_DEFAULT).getValue(context, key, defaultValue);
    }
    public static float getValue(Context context, String key, float defaultValue) {
        return sharedPref(SP_TYPE_DEFAULT).getValue(context, key, defaultValue);
    }
    public static long getValue(Context context, String key, long defaultValue) {
        return sharedPref(SP_TYPE_DEFAULT).getValue(context, key, defaultValue);
    }

    public static String [] getStringArray(Context context, String key) {
        return sharedPref(SP_TYPE_DEFAULT).getStringArray(context, key);
    }

    public static ArrayList<String> getStringList(Context context, String key) {
        return sharedPref(SP_TYPE_DEFAULT).getStringList(context, key);
    }

    public static int [] getIntegerArray(Context context, String key) {
        return sharedPref(SP_TYPE_DEFAULT).getIntegerArray(context, key);
    }

    public static List<Integer> getIntegerList(Context context, String key) {
        return sharedPref(SP_TYPE_DEFAULT).getIntegerList(context, key);
    }

    // contains key
    public static boolean containsKey(Context context, String key) {
        return sharedPref(SP_TYPE_DEFAULT).containsKey(context, key);
    }

    // remove key
    public static boolean removeKey(Context context, String key) {
        return sharedPref(SP_TYPE_DEFAULT).removeKey(context, key);
    }


    // 内部用函数
    private static <T> String join(CharSequence delimiter, Iterable<T> tokens) {
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = tokens.iterator();
        if (it.hasNext()) {
            T value = it.next();
            if (value == null) {
                sb.append(NULL_VALUE);
            } else {
                sb.append(value);
            }

            while (it.hasNext()) {
                sb.append(delimiter);
                value = it.next();
                if (value == null) {
                    sb.append(NULL_VALUE);
                } else {
                    sb.append(value);
                }
            }
        }
        return sb.toString();
    }
}
