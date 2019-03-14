package com.liun.example.login

import android.app.Activity
import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.BaseBean
import com.liun.example.Constants
import com.liun.example.http.BaseIProgressDialog
import com.liun.example.http.BaseProgressCallBack
import com.liun.example.http.HttpModel

/**
 * Description:数据层
 * Author：Liun
 * Date:2019/03/13 17:48
 *
 */
class LoginModelImpl : LoginModel {
    private var mLoginView: LoginView

    private var mActivity: Activity

    constructor(loginView: LoginView, activity: Activity){
        this.mLoginView = loginView
        this.mActivity = activity
    }

    override fun login(name: String, passWord: String) {
        HttpModel.HttpPostLogin(object : BaseProgressCallBack<String>(BaseIProgressDialog(mActivity)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    mLoginView.onSuccess(result)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        }, name, passWord)
    }
}