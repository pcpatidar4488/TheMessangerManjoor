package com.example.manjooralam.themessanger.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.manjooralam.themessanger.R;
import com.example.manjooralam.themessanger.utilities.AppSharedPreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(AppSharedPreferences.getBoolean(SplashActivity.this, AppSharedPreferences.PREF_KEY.ISLOGIN)){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this,LoginSignUpActivity.class));
                    finish();
                }
            }
        }, 3000);
    }
}
