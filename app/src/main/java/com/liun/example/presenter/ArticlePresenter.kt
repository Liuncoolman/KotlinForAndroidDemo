package com.liun.example.presenter

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.project.ProjectBean
import com.liun.example.project.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/21 11:16
 *
 */
class ArticlePresenter {

    fun getTreeList(listener:OnRequestCallBackListener.ArticleListener){
        HttpModel.HttpGetArticleTree(object: BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    listener.onSuccess(bean.data)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        })

    }

    private lateinit var listener: OnRequestCallBackListener.ArticleListListener

    private var id: Int = 0

    fun setListener(listener: OnRequestCallBackListener.ArticleListListener){
        this.listener = listener
    }


    fun setId(id: Int){
        this.id = id
    }

    fun getArticleList(index:Int){
        HttpModel.HttpGetArticleList(object :BaseCallBack<String>(){
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectListBean::class.java)
                if(bean?.errorCode == Constants.CODE_SUCCESS){
                    if(::listener.isInitialized){
                        listener.onSuccess(bean.data.datas)
                    }
                }
            }
        },id,index)
    }
}