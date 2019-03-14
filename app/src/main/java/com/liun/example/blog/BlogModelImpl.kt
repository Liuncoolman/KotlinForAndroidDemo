package com.liun.example.blog

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 09:44
 *
 */
class BlogModelImpl(val listenter: BlogView) : BlogModel {

    override fun getBannerList() {
        HttpModel.HttpGetBannerList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BannerBean::class.java)
                listenter.bannerList(bean!!)
            }
        })
    }

    override fun getBlogList(index: Int) {
        HttpModel.HttpGetBlogList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BlogBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    listenter.blogList(bean)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }

            }
        }, index)
    }
}