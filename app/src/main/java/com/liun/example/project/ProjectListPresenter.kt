package com.liun.example.project

import com.android.kotlinapp.wzp.utils.GsonUtils
import com.liun.example.Constants
import com.liun.example.http.BaseCallBack
import com.liun.example.http.HttpModel
import com.liun.example.impl.OnRequestCallBackListener

/**
 * Description:
 * Author：Liun
 * Date:2019/01/14 14:18
 *
 */
class ProjectListPresenter(val cid: Int, val listener: OnRequestCallBackListener.ProjectListListener) {

    fun getProjectList(index: Int) {
        HttpModel.HttpGetProjectList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, ProjectListBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS){
                    val datas = bean.data.datas
                    listener.onSuccess(datas)
                }
            }
        }, index, cid)
    }
}