package com.liun.example

import android.annotation.SuppressLint
import android.app.Application
import com.liun.example.http.AppBaseUrl
import com.tencent.smtt.sdk.QbSdk
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.cookie.CookieManger

/**
 * Description:
 * Author：Liun
 * Date:2019/01/02 17:43
 *
 */
class MyApplication :Application(){

    /**
     * 单例
     * **/
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: MyApplication? = null
            get() {
                if (field == null) {
                    field = MyApplication()
                }
                return field
            }

        @Synchronized
        fun get(): MyApplication? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        EasyHttp.init(this)//默认初始化,必须调用
        EasyHttp.getInstance()
            .setBaseUrl(AppBaseUrl.BaseUrl)
//                .debug(TAG, BuildConfig.DEBUG)
            .debug("-----request-----", true)

            //默认60秒时可不设置一下三行
            .setReadTimeOut(60 * 1000)
            .setWriteTimeOut(60 * 1000)
            .setConnectTimeout(60 * 1000)
            .setCookieStore(CookieManger(this))

        // X5WebView
        QbSdk.initX5Environment(this,object :QbSdk.PreInitCallback{
            override fun onCoreInitFinished() {

            }

            override fun onViewInitFinished(p0: Boolean) {

            }

        })
    }
}