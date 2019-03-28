package com.liun.example.wxartcile.presenter

import com.liun.example.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.project.model.ProjectBean
import com.liun.example.wxartcile.contract.WxArticleContract

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 11:57
 *
 */
class WxArticleChaterPresenter(private val view: WxArticleContract.WxArticChaterView) :
    WxArticleContract.WxArticChaterPresenter {

    init {
        view.setPresenter(this)
    }

    override fun loadChaterList() {
        HttpModel.HttpGetWxarticleChapters(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.getChaterList(bean)
                }
            }
        })
    }

    override fun start() {
        loadChaterList()
    }
}