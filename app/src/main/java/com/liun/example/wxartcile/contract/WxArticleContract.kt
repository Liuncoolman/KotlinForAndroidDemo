package com.liun.example.wxartcile.contract

import com.liun.example.base.BasePresenter
import com.liun.example.base.BaseView
import com.liun.example.project.model.ProjectBean
import com.liun.example.project.model.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 15:39
 *
 */
interface WxArticleContract {

    interface WxArticChaterView:BaseView<WxArticChaterPresenter>{
        fun getChaterList(projectBean: ProjectBean)
    }

    interface WxArticChaterPresenter:BasePresenter{
        fun loadChaterList()
    }

    interface WxArticListView:BaseView<WxArticListPresenter>{
        fun getChaterListByCid(data: ProjectListBean)
    }

    interface WxArticListPresenter:BasePresenter{
        fun loadWxArticList(index: Int)
    }
}