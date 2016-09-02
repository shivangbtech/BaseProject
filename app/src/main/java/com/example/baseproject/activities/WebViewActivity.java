package com.example.baseproject.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.baseproject.R;
import com.example.baseproject.utilities.ToastUtils;
import com.example.baseproject.variables.GlobalParams;

/**
 * Activity use to show web page in webview
 * You need to send StringURL using intent
 */
public class WebViewActivity extends Activity {

    private WebView webView;
    private ProgressDialog progressDialog;
    private Context mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mContext = this;
        webView = (WebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        if (progressDialog == null) {
            // in standard case YourActivity.this
            progressDialog = new ProgressDialog(WebViewActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        String url, header;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString(GlobalParams.PARAM_URL);
            header = extras.getString(GlobalParams.PARAM_HEADER);
        } else {
            url = "";
            header = "";
        }

        if(url != null && url.length() > 0){
            startWebView(url);
        }else {
            ToastUtils.getInstance().showToastLong(mContext, R.string.validation_setting_web_view_url);
        }
    }

    private void startWebView(String url) {
        
        //Create new webview Client to show progress dialog
        //When opening a url or click on link
         
        webView.setWebViewClient(new WebViewClient() {
          
            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {              
                view.loadUrl(url);
                return true;
            }
        
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {

            }
            public void onPageFinished(WebView view, String url) {
                try{
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }); 
          
         // Javascript inabled on webview  
        webView.getSettings().setJavaScriptEnabled(true); 
         
        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */


        webView.loadUrl(url);
          
          
    }
     
    // Open previous opened link from history on webview when back button pressed
     
    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }


}