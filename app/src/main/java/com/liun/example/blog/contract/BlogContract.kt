package com.liun.example.blog.contract

import android.support.v4.app.FragmentActivity
import com.liun.example.base.BasePresenter
import com.liun.example.base.BaseView
import com.liun.example.blog.model.BannerBean
import com.liun.example.blog.model.Blog

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 15:05
 *
 */
interface BlogContract : BasePresenter {

    interface View : BaseView<Presenter> {
        fun blogList(blogBean: List<Blog>)
        fun bannerList(banners: List<BannerBean>)
        val aty: FragmentActivity
    }

    interface Presenter : BasePresenter {
        fun getBlogList(index: Int)
        fun getBannerList()
    }
}