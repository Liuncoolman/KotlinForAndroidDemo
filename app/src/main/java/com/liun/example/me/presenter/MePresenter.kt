package com.liun.example.me.presenter

import com.liun.example.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.base.BaseBean
import com.liun.example.http.BaseIProgressDialog
import com.liun.example.http.BaseProgressCallBack
import com.liun.example.http.HttpModel
import com.liun.example.me.contract.MeContract

/**
 * Description:
 * Author：Liun
 * Date:2019/01/26 15:28
 *
 */
class MePresenter(private val view: MeContract.MeView) : MeContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun loadLoginOut() {
        HttpModel.HttpLoginout(object : BaseProgressCallBack<String>(BaseIProgressDialog(view.aty)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.loginOut(result)
                }
            }
        })
    }

    override fun start() {
    }
}