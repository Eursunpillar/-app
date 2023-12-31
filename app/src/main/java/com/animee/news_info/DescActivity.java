package com.animee.news_info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DescActivity extends AppCompatActivity {
    WebView descWeb;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        descWeb = findViewById(R.id.desc_webview);
        url = getIntent().getStringExtra("url");
//        创建WebView的设置类，对于属性进行设置
        WebSettings webSettings = descWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);  //设置页面支持js交互
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); //缩放至屏幕的大小
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置webview的缓存方式
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持js打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8"); //设置编码格式

//        设置要加载的网址
        descWeb.loadUrl(url);
//        默认通过手机浏览器打开网址，为了能够直接通过webview打开网址，就必须设置以下操作
        descWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                使用webview要加载的url
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && descWeb.canGoBack()) {
            descWeb.goBack();   //返回上一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
