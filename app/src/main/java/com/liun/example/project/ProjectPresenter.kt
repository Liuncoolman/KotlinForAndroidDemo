package com.liun.example.project

import android.app.Activity
import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener

/**
 * Description:
 * Author：Liun
 * Date:2019/01/12 16:00
 *
 */
class ProjectPresenter(val activity: Activity) {

    fun requestProjectData(listener:OnRequestCallBackListener.ProjectTreeListener) {
        HttpModel.HttpGetProjectTree(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    listener.onSuccess(bean.data)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        })
    }
}