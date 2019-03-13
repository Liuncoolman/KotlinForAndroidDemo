package com.liun.example.login

import android.app.Activity
import com.liun.example.http.BaseIProgressDialog
import com.liun.example.http.BaseProgressCallBack
import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.BaseBean
import com.liun.example.Constants
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener

/**
 * Description:
 * Author：Liun
 * Date:2019/01/03 16:50
 *
 */
class LoginPresenter {

    /**
     *  [account]   账号
     *  [password]  密码
     */
    fun HttpPostLogin(
        activity: Activity,
        account: String,
        password: String,
        listener: OnRequestCallBackListener.LoginListener
    ) {
        HttpModel.HttpPostLogin(object : BaseProgressCallBack<String>(BaseIProgressDialog(activity)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    listener.onSuccess(result)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        }, account, password)
    }
}