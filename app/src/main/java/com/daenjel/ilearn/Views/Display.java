package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daenjel.ilearn.R;

public class Display extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        webView = findViewById(R.id.contentDisplay);

       try {
           webView.loadUrl("file:///android_asset/www/index.html");
           WebSettings settings = webView.getSettings();
           settings.setJavaScriptEnabled(true);
           settings.setDisplayZoomControls(true);
           settings.setBuiltInZoomControls(true);
           webView.setWebViewClient(new WebViewClient());
           webView.setNetworkAvailable(true);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
