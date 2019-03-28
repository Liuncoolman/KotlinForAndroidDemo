package com.liun.example.project.presenter

import com.liun.example.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.project.contract.ProjectContract
import com.liun.example.project.model.ProjectBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/12 16:00
 *
 */
class ProjectChaterPresenter(private val view:ProjectContract.ProjectChaterView) :
    ProjectContract.ProjectChaterPresenter {

    init {
        view.setPresenter(this)
    }

    override fun loadProjectChater() {
        HttpModel.HttpGetProjectTree(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.getProjectChater(bean.data)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        })
    }

    override fun start() {
        loadProjectChater()
    }


}