package com.liun.example.activity

import android.graphics.PixelFormat
import android.os.Bundle
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/07 11:01
 *
 */
class WebViewActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleGone()
        window.setFormat(PixelFormat.TRANSLUCENT)
        val link = intent.getStringExtra(Constants.KEY_LINK)
        x5WebView.loadUrl(link)
        setRightActionGone()
    }

    override fun onBackPressed() {
        if (x5WebView.canGoBack()) x5WebView.goBack() else super.onBackPressed()

    }
}