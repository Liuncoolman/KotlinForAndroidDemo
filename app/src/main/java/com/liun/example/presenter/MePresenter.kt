package com.liun.example.presenter

import android.app.Activity
import com.android.kotlinapp.wzp.utils.GsonUtils
import com.liun.example.BaseBean
import com.liun.example.Constants
import com.liun.example.http.BaseIProgressDialog
import com.liun.example.http.BaseProgressCallBack
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener

/**
 * Description:
 * Author：Liun
 * Date:2019/01/26 15:28
 *
 */
class MePresenter {

    fun loginOut(activity: Activity,listener:OnRequestCallBackListener.LoginoutListener) {
        HttpModel.HttpLoginout(object : BaseProgressCallBack<String>(BaseIProgressDialog(activity)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    listener.onSuccess(result)
                }
            }
        })
    }
}