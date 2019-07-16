package com.app.nb.loggy.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.app.nb.loggy.Activities.LoginActivity;
import com.app.nb.loggy.Activities.MenuActivity;
import com.app.nb.loggy.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("Session", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMenu = new Intent(this, MenuActivity.class);

        if (!TextUtils.isEmpty(Util.getEmailSessionPreference(preferences))
                && !TextUtils.isEmpty(Util.getPasswordSessionPreference(preferences))) {
            startActivity(intentMenu);
        } else {
            startActivity(intentLogin);
        }
        finish();
    }
}
