package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daenjel.ilearn.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void signUpPage(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }
}
