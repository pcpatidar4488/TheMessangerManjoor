package com.example.manjooralam.themessanger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.manjooralam.themessanger.R;

public class LoginSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvLogin,tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        init();
        tvLogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);

    }

    private void init() {
        tvLogin= (TextView) findViewById(R.id.loginPage);
        tvSignup= (TextView) findViewById(R.id.signupPage);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupPage:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.loginPage:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
