package com.hostmentor.allshoply;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;



import com.hostmentor.allshoply.Interface.WebAppInterface;

public class WebActivity extends AppCompatActivity {

    WebView myWebView;
    String url;
    Activity activity;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_web);

        myWebView = (WebView) findViewById(R.id.webview);
        //progressBar=(ProgressBar)findViewById(R.id.progressBar);


        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.setWebChromeClient(new MyWebViewClient());
        myWebView.setInitialScale(1);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebView.setScrollbarFadingEnabled(false);
        myWebView.loadUrl("https://www.allshoply.com");
    }
   /* private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();

            view.loadUrl(url);
//            progressBar.setVisibility(View.VISIBLE);
//            if(progressDialog==null){
//                progressDialog=new ProgressDialog(WebActivity.this);
//                progressDialog.setMessage("Loading...");
//                progressDialog.show();
//            }
           return true;
        }
        //Show loader on url load
//        @Override
//        public void onLoadResource (WebView view, String url) {
//           if(progressDialog == null){
//               progressDialog=new ProgressDialog(WebActivity.this);
//               progressDialog.setMessage("Loading...");
//               progressDialog.show();
//           }
//        }
        @Override
        public void onPageFinished(WebView view,String url){
//            if(progressDialog != null){
//                progressDialog=new ProgressDialog(WebActivity.this);
//               // progressDialog.setMessage("Loading...");
//                progressDialog.dismiss();
//            }
            super.onPageFinished(view,url);
            //progressBar.setVisibility(View.GONE);
        }
    }*/

   private  class MyWebViewClient extends WebChromeClient{
       public void onProgressChanged(WebView view,int newProgress){
           super.onProgressChanged(view,newProgress);

              if(myWebView != null){
                  url=myWebView.getUrl();
              }

       }
   }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed(){
        if(myWebView.canGoBack()){
            myWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
    }

