package com.kbear.noknok.utils.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by allen on 2/18/15.
 */
public final class SharedPreferencesHelper {
    private static final SharedPreferencesHelper INSTANCE = new SharedPreferencesHelper();

    private static SharedPreferences preferences = null;
    private static SharedPreferences.Editor editor = null;

    public static synchronized SharedPreferencesHelper getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    // SETTERS
    public void setPreference(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void setPreference(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
    }

    public void setPreference(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setPreference(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void setPreference(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void setPreference(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void removeAll() {
        editor.clear();
        editor.commit();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public Set<String> getStringSet(String key) {
        return preferences.getStringSet(key, null);
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    public float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
