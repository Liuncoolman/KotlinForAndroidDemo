package com.liun.example.blog.contract

import com.liun.example.base.BasePresenter
import com.liun.example.base.BaseView
import com.liun.example.blog.model.BannerBean
import com.liun.example.blog.model.BlogBean

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 15:05
 *
 */
interface BlogContract : BasePresenter {

    interface View : BaseView<Presenter> {
        fun blogList(blogBean: BlogBean)
        fun bannerList(bannerBean: BannerBean)
    }

    interface Presenter : BasePresenter {
        fun getBlogList(index:Int)
        fun getBannerList()
    }
}