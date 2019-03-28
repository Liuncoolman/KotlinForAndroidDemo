package com.liun.example.blog.presenter

import com.liun.example.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.blog.contract.BlogContract
import com.liun.example.blog.model.BannerBean
import com.liun.example.blog.model.BlogBean
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 09:35
 *
 */
class BlogPresenter() : BlogContract.Presenter {

    private lateinit var view: BlogContract.View

    constructor(view: BlogContract.View) : this() {
        this.view = view
        view.setPresenter(this)
    }

    override fun getBlogList(index: Int) {
        HttpModel.HttpGetBlogList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BlogBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.blogList(bean)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }

            }
        }, index)
    }

    override fun getBannerList() {
        HttpModel.HttpGetBannerList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BannerBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.bannerList(bean)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        })
    }

    override fun start() {
        getBlogList(1)
        getBannerList()
    }

}