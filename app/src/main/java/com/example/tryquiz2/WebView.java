package com.example.tryquiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {

    private android.webkit.WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView=findViewById(R.id.webView);
        Intent intent=getIntent();
        String website=intent.getStringExtra("links");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(website);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
