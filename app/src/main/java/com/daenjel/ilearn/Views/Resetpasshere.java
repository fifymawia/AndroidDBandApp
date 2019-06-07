package com.daenjel.ilearn.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.daenjel.ilearn.R;

public class Resetpasshere extends AppCompatActivity {
    WebView mywebView;
    Button btnlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpasshere);
        btnlog = (Button) findViewById(R.id.gotologin);
        mywebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("http://fredamawia.mightechsdcl.com/ilearn/resetpass.php");
        mywebView.setWebViewClient(new WebViewClient());
    }

    public void backtologin (View view) {

        startActivity(new Intent(getApplicationContext(), Login.class));
    }
    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack()){
            mywebView.goBack();
        }else{
            super.onBackPressed();
        }

    }
}


