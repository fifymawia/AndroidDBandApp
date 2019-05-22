package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.daenjel.ilearn.R;

public class Display extends AppCompatActivity {

    WebView webView;
    ToggleButton zoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        webView = findViewById(R.id.contentDisplay);
        zoom = findViewById(R.id.zoomer);

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

       zoom.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (zoom.isChecked()){
                   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
               }else if (!zoom.isChecked()){
                   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
               }else {
                   Toast.makeText(getApplicationContext(), "Orientation", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
