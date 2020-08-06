package com.dy.vam.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.dy.vam.R;

public class ExcelWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_web_view);

        String url = getIntent().getStringExtra("url");

        Log.e("111111111",url);
        WebView webView = (WebView) findViewById(R.id.web);
//        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);
    }
}
