package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daenjel.ilearn.R;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void loginPage(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }
}
