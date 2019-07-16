package com.app.nb.loggy.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getEmailSessionPreference(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static String getPasswordSessionPreference(SharedPreferences preferences) {
        return preferences.getString("password", "");
    }

    public static void removeSharedPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }
}
