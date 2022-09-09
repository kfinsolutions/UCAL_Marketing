package com.ucal.marketing.expo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient


class HomeFragment : Fragment() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val mainWeb = view.findViewById<WebView>(R.id.mainWebView)
        mainWeb.webViewClient = WebViewClient()
        mainWeb.apply {
            loadUrl("file:///android_asset/index.html")
            settings.javaScriptEnabled = true
        }
        return view
    }

}