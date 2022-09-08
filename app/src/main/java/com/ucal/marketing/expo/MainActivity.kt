package com.ucal.marketing.expo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainWeb = findViewById<WebView>(R.id.mainWebView)

        mainWeb.webViewClient = WebViewClient()

        mainWeb.apply {
            loadUrl("file:///android_asset/index.html")
            settings.javaScriptEnabled = true
        }
    }

}