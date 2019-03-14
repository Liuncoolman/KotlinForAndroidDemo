package com.liun.example.login.presenter

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.base.BaseBean
import com.liun.example.Constants
import com.liun.example.http.BaseIProgressDialog
import com.liun.example.http.BaseProgressCallBack
import com.liun.example.http.HttpModel
import com.liun.example.login.contract.LoginContract

/**
 * Description:
 * Author：Liun
 * Date:2019/01/03 16:50
 *
 */
class LoginPresenter() : LoginContract.Presenter {
    private lateinit var view: LoginContract.View

    constructor(view: LoginContract.View) : this() {
        this.view = view
        view.setPresenter(this)
    }

    override fun login() {
        HttpModel.HttpPostLogin(object : BaseProgressCallBack<String>(BaseIProgressDialog(view.aty)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.onSuccess(result)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        }, view.userName, view.userPwd)
    }

    override fun start() {
        login()
    }
}