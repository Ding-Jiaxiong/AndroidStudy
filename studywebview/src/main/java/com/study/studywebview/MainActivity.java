package com.study.studywebview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        // 基础设置
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        // 允许JS开窗口（配合WebChromeClient）
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);

        // 注入JS交互接口
        webView.addJavascriptInterface(new AndroidJsBridge(this, webView), "AndroidBridge");

        // WebViewClient：处理URL加载
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.startsWith("javascript:")) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("javascript:")) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 页面加载完成后执行JS alert
                view.loadUrl("javascript:showToast()");
                // 可选：测试传参方法
                view.loadUrl("javascript:showMessage('来自Android的测试消息')");
            }
        });

        // 核心修复：添加WebChromeClient处理JS弹窗
        webView.setWebChromeClient(new WebChromeClient() {
            // 处理JS alert弹窗
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                // 执行系统默认的alert弹窗（也可以自定义Dialog替换）
                return super.onJsAlert(view, url, message, result);
            }
        });

        // 加载本地HTML
        webView.loadUrl("file:///android_asset/index.html");
    }

    // 注意：添加@JavascriptInterface注解（必须）
    public class AndroidJsBridge {
        private Context context;
        private WebView webView;

        public AndroidJsBridge(Context context, WebView webView) {
            this.context = context;
            this.webView = webView;
        }

        // JS调用的方法（无参）
        @JavascriptInterface
        public void toast() {
            Toast.makeText(context, "JS调用了Android的toast方法", Toast.LENGTH_SHORT).show();
        }

        // JS调用的方法（有参）
        @JavascriptInterface
        public void setTitle(String title) {
            Toast.makeText(context, "JS调用了Android的toast方法，参数" + title, Toast.LENGTH_SHORT).show();
//            ((Activity) context).runOnUiThread(() -> {
//                // 主线程更新UI
//                ((Activity) context).setTitle(title);
//            });
        }
    }

    // JS交互桥接类
    public class JsBridge {
        @JavascriptInterface
        public void showAndroidToast(String msg) {
            runOnUiThread(() -> Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show());
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}