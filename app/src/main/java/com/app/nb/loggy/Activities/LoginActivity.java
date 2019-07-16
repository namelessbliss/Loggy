package com.app.nb.loggy.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.app.nb.loggy.R;
import com.app.nb.loggy.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private EditText etEmail;
    private EditText etPass;
    private Switch swRecordar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("Session", Context.MODE_PRIVATE);

        setTitle("Login");

        bindUI();

        setCredentialsIfExist();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();
                if (login(email, password)) {
                    goToMenu();
                    saveOnPrefereces(email, password);
                }
            }
        });
    }

    private void setCredentialsIfExist() {
        String email = Util.getEmailSessionPreference(preferences);
        String password = Util.getPasswordSessionPreference(preferences);

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            etEmail.setText(email);
            etPass.setText(password);
            swRecordar.setChecked(true);
        }
    }

    private void bindUI() {
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        swRecordar = findViewById(R.id.swRecordar);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email no valido", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "Contraseña no valida", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    private void saveOnPrefereces(String email, String password) {
        if (swRecordar.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }

    private void goToMenu() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
