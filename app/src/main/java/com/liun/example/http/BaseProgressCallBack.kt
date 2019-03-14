package com.liun.example.http


import com.android.kotlinapp.wzp.utils.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.base.BaseBean
import com.liun.example.MyApplication
import com.zhouyou.http.callback.ProgressDialogCallBack
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.subsciber.IProgressDialog
import com.zhouyou.http.utils.Utils

import retrofit2.HttpException

/**
 * 带进度条的网络回调
 * @param <T>
</T> */
open class BaseProgressCallBack<T> : ProgressDialogCallBack<T> {


    constructor(progressDialog: IProgressDialog?) : super(progressDialog) {
        if (progressDialog is BaseIProgressDialog) {
            if (progressDialog.activity == null || progressDialog.activity!!.isFinishing) {
                BaseProgressCallBack<T>(null)
            }
        }
    }

    /**
     *
     * @param progressDialog
     * @param isShowProgress 是否显示进度框
     * @param isCancel 对话框是否可以取消 true可以取消网络请求
     */
    constructor(progressDialog: IProgressDialog, isShowProgress: Boolean, isCancel: Boolean) : super(progressDialog, isShowProgress, isCancel) {
        if (progressDialog is BaseIProgressDialog) {
            if (progressDialog.activity == null || progressDialog.activity!!.isFinishing) {
                BaseProgressCallBack<T>(null)
            }
        }
    }

    override fun onSuccess(result: T) {

    }

    override fun onCompleted() {
        try {
            super.onCompleted()
        } catch (e: Exception) {

        }

    }

    override fun onError(e: ApiException?) {
        try {
            super.onError(e)
        } catch (e1: Exception) {

        }

        val isNetwork = Utils.isNetworkAvailable(MyApplication.get())
        if (!isNetwork) {
            //网络异常检查网络
            ToastUtils.showShort("请检查网络是否连接")
        } else {
            //网络请求错误
            doErrorBack(e!!)
        }
    }

    private fun doErrorBack(e: ApiException) {
        if (e.cause is HttpException) {
            val httpException = e.cause as HttpException
            try {
                val result = httpException.response().errorBody()!!.string()
                val baseBean = GsonUtils.toBean(result, BaseBean::class.java)
                when (baseBean?.errorCode) {
                    -1001 -> BaseCallBack.showDialog(baseBean.errorMsg)
                }
                LogUtils.d(result)
            } catch (e1: Exception) {
                onToastError("服务异常")
            }

        } else {
            onToastError("服务异常")
        }
    }

    //展示错误文本，不需要展示时重写这个方法
    fun onToastError(errorToast: String) {
        ToastUtils.showShort(errorToast)
    }
}
