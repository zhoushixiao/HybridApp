package com.suning.hybrid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.suning.hybrid.R;
import com.suning.hybrid.js.JsInteration;
import com.suning.hybrid.tools.JSONTools;
import com.suning.hybrid.tools.KLog;
import com.suning.hybrid.views.ProgressWebView;

import java.util.Date;

public class WebActivity extends AppCompatActivity {

    private  ProgressWebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webview= (ProgressWebView) findViewById(R.id.webview);
        webview.settings();
        //加载本地文件
        String url = "file:///android_asset/Web/index.html";
        webview.loadUrl(url);

        //js调用本地方法window.hybrid.+方法名
        //本地调用js方法
        webview.addJavascriptInterface(new JsInteration(this,webview), "hybrid");

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);        // 点击网页链接 ,在webview中跳转,而不是另外开浏览器
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }
}
