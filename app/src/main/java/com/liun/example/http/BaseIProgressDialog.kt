package com.liun.example.http

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog

import com.zhouyou.http.subsciber.IProgressDialog

/**
 * 圆形进度框
 */
class BaseIProgressDialog : IProgressDialog {

    var activity: Activity? = null
        private set
    private var tips: String? = null

    constructor(activity: Activity) {
        this.activity = activity
        tips = "加载中,请稍后..."
    }

    constructor(activity: Activity, tips: String) {
        this.activity = activity
        this.tips = tips
    }

    override fun getDialog(): Dialog? {
        if (activity == null || activity!!.isFinishing) {
            return null
        }
        val dialog = ProgressDialog(activity)
        dialog.setMessage(tips)
        dialog.setCancelable(true)
        return dialog
    }

}
