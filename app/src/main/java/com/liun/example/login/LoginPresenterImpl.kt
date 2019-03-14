package com.liun.example.login

import android.app.Activity

/**
 * Description:展示层
 * Author：Liun
 * Date:2019/03/13 17:41
 *
 */
class LoginPresenterImpl : LoginPresenter {

    private var mLoginModel: LoginModelImpl

    constructor(activity: Activity, loginView: LoginView) {
        this.mLoginModel = LoginModelImpl(loginView, activity)
    }


    override fun validateCredentials(username: String, password: String) {
        mLoginModel.login(username, password)
    }
}