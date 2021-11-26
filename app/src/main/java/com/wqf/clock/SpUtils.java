package com.wqf.clock;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
    private static final SpUtils instance = new SpUtils();
    private static SharedPreferences mSp;

    private SpUtils() {
    }

    //保证单例对象唯一
    public static SpUtils getInstance(String fileName) {
        if (mSp == null) {
            mSp = ContextApplication.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return instance;
    }

    //保存
    //分别判断对应的参数类型 保存对应的格式
    public void save(String key, Object value) {
        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Long) {
            mSp.edit().putLong(key, (Long) value).commit();
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();
        }
    }

    //取
    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    public int getInteger(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSp.getLong(key, defValue);
    }

}
