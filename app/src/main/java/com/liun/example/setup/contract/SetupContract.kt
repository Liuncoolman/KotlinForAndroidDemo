package com.liun.example.setup.contract

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
interface SetupContract {

    interface SetupChaterView : BaseView<SetupChaterPresenter> {
        fun getSetupChater(data: ArrayList<ProjectBean.Data>)
    }

    interface SetupChaterPresenter : BasePresenter {
        fun loadSetupChater()
    }

    interface SetupListView : BaseView<SetupListPresenter> {
        fun getSetupList(datas: ArrayList<ProjectListBean.DataBeanList.DataBean>)
    }

    interface SetupListPresenter : BasePresenter {
        fun loadSetupList(index: Int)
    }
}