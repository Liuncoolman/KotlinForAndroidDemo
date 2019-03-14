package com.liun.example.project.contract

import com.liun.example.base.BasePresenter
import com.liun.example.base.BaseView
import com.liun.example.project.model.ProjectBean
import com.liun.example.project.model.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 16:37
 *
 */
interface ProjectContract {

    interface ProjectChaterView : BaseView<ProjectChaterPresenter> {
        fun getProjectChater(data: ArrayList<ProjectBean.Data>)
    }

    interface ProjectChaterPresenter : BasePresenter {
        fun loadProjectChater()
    }

    interface ProjectListView : BaseView<ProjectListPresenter> {
        fun getProjectList(datas: ArrayList<ProjectListBean.DataBeanList.DataBean>)
    }

    interface ProjectListPresenter : BasePresenter {
        fun loadProjectList(index: Int)
    }
}