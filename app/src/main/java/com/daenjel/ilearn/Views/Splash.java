package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.daenjel.ilearn.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        },3000);*/

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };
        timer.start();
    }
}
