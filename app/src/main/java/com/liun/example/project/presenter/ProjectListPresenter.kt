package com.liun.example.project.presenter

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.project.contract.ProjectContract
import com.liun.example.project.model.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 11:26
 *
 */
class ProjectListPresenter(private val cid:Int,private val view:ProjectContract.ProjectListView) :
    ProjectContract.ProjectListPresenter {

    init {
        view.setPresenter(this)
    }

    override fun loadProjectList(index: Int) {
        HttpModel.HttpGetProjectList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectListBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    val datas = bean.data.datas
                    view.getProjectList(datas)
                }
            }
        }, index, cid)
    }

    override fun start() {

    }


}