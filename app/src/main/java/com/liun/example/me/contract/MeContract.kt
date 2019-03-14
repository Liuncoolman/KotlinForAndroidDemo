package com.liun.example.me.contract

import android.app.Activity
import com.liun.example.base.BasePresenter
import com.liun.example.base.BaseView

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 17:35
 *
 */
interface MeContract {

    interface MeView : BaseView<Presenter> {
        fun loginOut(result: String)
        val aty: Activity
    }

    interface Presenter : BasePresenter {
        fun loadLoginOut()
    }
}