package com.example.kshitij.wikisearch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kshitij.wikisearch.Constants;
import com.example.kshitij.wikisearch.R;

public class DetailWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_web_view);

        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        WebView view = findViewById(R.id.wikiWebView);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl(Constants.BASE_WIKI_URL + getIntent().getStringExtra("title"));
    }
}
