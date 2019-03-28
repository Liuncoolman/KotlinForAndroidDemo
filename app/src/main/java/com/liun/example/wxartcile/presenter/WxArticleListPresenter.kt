package com.liun.example.wxartcile.presenter

import com.liun.example.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.project.model.ProjectListBean
import com.liun.example.wxartcile.contract.WxArticleContract

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 11:57
 *
 */
class WxArticleListPresenter(private val cid:Int,private val view: WxArticleContract.WxArticListView) :
    WxArticleContract.WxArticListPresenter {

    init {
        view.setPresenter(this)
    }

    override fun loadWxArticList(index: Int) {
        HttpModel.HttpGetWxarticleList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectListBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.getChaterListByCid(bean)
                }
            }
        }, cid, index)
    }

    override fun start() {
    }
}