package com.liun.example.impl

import com.liun.example.blog.BannerBean
import com.liun.example.blog.BlogBean
import com.liun.example.project.ProjectBean
import com.liun.example.project.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/03 16:53
 *
 */
interface OnRequestCallBackListener {

    interface LoginListener {
        fun onSuccess(result: String)
    }

    interface BlogListener {
        fun onSuccess(bean: BlogBean?)
    }

    interface BannerListener {
        fun onSuccess(bean: BannerBean?)
    }

    interface ProjectTreeListener {
        fun onSuccess(list: ArrayList<ProjectBean.Data>)
    }

    interface ProjectListListener {
        fun onSuccess(datas: ArrayList<ProjectListBean.DataBeanList.DataBean>?)
    }

    interface WxarticleChaptersListener {
        fun onSuccess(list: ArrayList<ProjectBean.Data>)
    }

    interface WxarticleListListener {
        fun onSuccess(list: ArrayList<ProjectListBean.DataBeanList.DataBean>)
    }

    interface ArticleListener{
        fun onSuccess(list: ArrayList<ProjectBean.Data>)
    }

    interface ArticleListListener {
        fun onSuccess(list: ArrayList<ProjectListBean.DataBeanList.DataBean>)
    }

    interface LoginoutListener{
        fun onSuccess(result:String)
    }
}