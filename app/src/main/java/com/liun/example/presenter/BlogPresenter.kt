package com.liun.example.presenter

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.model.BannerBean
import com.liun.example.model.BlogBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 16:12
 *
 */
class BlogPresenter(val listenter: OnRequestCallBackListener.BlogListener) {

    fun getBannerList(listenter: OnRequestCallBackListener.BannerListener){
        HttpModel.HttpGetBannerList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result,BannerBean::class.java)
                listenter.onSuccess(bean)
            }
        })
    }

    fun getBlogList(index: Int ) {
        HttpModel.HttpGetBlogList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BlogBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    listenter.onSuccess(bean)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }

            }
        }, index)
    }

}