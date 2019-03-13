package com.liun.example.presenter

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.model.ProjectBean
import com.liun.example.model.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/16 15:05
 *
 */
class WxarticlePresenter {
    private var id = 0
    private var listener: OnRequestCallBackListener.WxarticleListListener? = null

    fun getWxarticleChapters(listener: OnRequestCallBackListener.WxarticleChaptersListener) {
        HttpModel.HttpGetWxarticleChapters(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result,ProjectBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS){
                    listener.onSuccess(bean.data)
                }
            }
        })
    }

    fun setId(id: Int, listener: OnRequestCallBackListener.WxarticleListListener) {
        this.id = id
        this.listener = listener
    }

    fun getWxarticleList(index: Int) {
        HttpModel.HttpGetWxarticleList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectListBean::class.java)
                if(bean?.errorCode == Constants.CODE_SUCCESS){
                    listener?.onSuccess(bean.data.datas)
                }
            }
        }, id, index)
    }
}