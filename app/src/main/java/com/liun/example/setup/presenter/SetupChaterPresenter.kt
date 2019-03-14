package com.liun.example.setup.presenter

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.project.model.ProjectBean
import com.liun.example.setup.contract.SetupContract

/**
 * Description:
 * Author：Liun
 * Date:2019/01/21 11:16
 *
 */
class SetupChaterPresenter(private val view: SetupContract.SetupChaterView) : SetupContract.SetupChaterPresenter {

    init {
        view.setPresenter(this)
    }

    override fun loadSetupChater() {
        HttpModel.HttpGetArticleTree(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.getSetupChater(bean.data)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        })
    }

    override fun start() {
        loadSetupChater()
    }
}