package com.liun.example.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

class X5WebView : WebView {

    private lateinit var mProgressBar:ProgressBar

    private val client = object : WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    constructor(arg0: Context, arg1: AttributeSet) : super(arg0, arg1) {
        this.webViewClient = client
        addProgressBar(arg0)
        initWebViewSettings()
        this.view.isClickable = true
    }

    private fun addProgressBar(context: Context){
        mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10)
        mProgressBar.layoutParams = params
        mProgressBar.progress = 0
        addView(mProgressBar)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        val webSetting = this.settings
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.useWideViewPort = true
        webSetting.loadWithOverviewMode = true
        // webSetting.setAppCacheEnabled(true);
        webSetting.domStorageEnabled = true
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        /*页面内跳转*/
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                p0?.loadUrl(p1)
                return super.shouldOverrideUrlLoading(p0, p1)
            }
        }

        webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)
                if (p1 == 100) {
                    mProgressBar.visibility = GONE
                } else {
                    if (mProgressBar.visibility == GONE)
                        mProgressBar.visibility = VISIBLE
                    mProgressBar.progress = p1
                }
            }
        }

    }
}
