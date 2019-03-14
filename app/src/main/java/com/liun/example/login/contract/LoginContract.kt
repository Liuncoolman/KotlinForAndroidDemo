package com.liun.example.login.contract

import android.app.Activity
import com.liun.example.base.BasePresenter
import com.liun.example.base.BaseView

/**
 * Description:
 * Author：Liun
 * Date:2019/03/13 17:37
 *
 */
interface LoginContract {

    interface View : BaseView<Presenter> {
        fun onSuccess(result: String)
        val userName: String
        val userPwd: String
        val aty: Activity
    }

    interface Presenter : BasePresenter {
        fun login()
    }

}