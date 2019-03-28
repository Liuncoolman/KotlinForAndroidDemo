package com.liun.example.setup.presenter

import com.liun.example.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.project.model.ProjectListBean
import com.liun.example.setup.contract.SetupContract

/**
 * Description:
 * Author：Liun
 * Date:2019/01/21 11:16
 *
 */
class SetupListPresenter(private val id:Int,private val view: SetupContract.SetupListView) : SetupContract.SetupListPresenter {

    init {
        view.setPresenter(this)
    }

    override fun loadSetupList(index: Int) {
        HttpModel.HttpGetArticleList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectListBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.getSetupList(bean.data.datas)
                }
            }
        }, id, index)

    }

    override fun start() {
    }
}