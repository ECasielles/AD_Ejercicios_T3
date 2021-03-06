package com.example.usuario.ad_ejercicios_t3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.usuario.ad_ejercicios_t3.R;

public class WebActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        //CAUTION
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getExtras().getString("url"));
    }

}
