package com.liun.example.http

import android.app.AlertDialog
import com.liun.example.utils.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.base.BaseBean
import com.liun.example.MyApplication
import com.zhouyou.http.callback.CallBack
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.utils.Utils
import retrofit2.HttpException

/**
 * 不带进度条的回调
 * Created by 77202 on 2018/5/25.
 */

abstract class BaseCallBack<T> : CallBack<T>() {

    override fun onStart() {

    }

    override fun onCompleted() {

    }


    override fun onError(e: ApiException) {
        val isNetwork = Utils.isNetworkAvailable(MyApplication.get())
        if (!isNetwork) {
            //网络异常检查网络
            ToastUtils.showShort("请检查网络是否连接")
        } else {
            //网络请求错误
            doErrorBack(e)
        }
    }

    private fun doErrorBack(e: ApiException) {
        if (e.cause is HttpException) {
            val httpException = e.cause as HttpException
            try {
                val result = httpException.response().errorBody()!!.string()
                val baseBean = GsonUtils.toBean(result, BaseBean::class.java)
                when (baseBean?.errorCode) {
                    -1001 -> showDialog(baseBean.errorMsg)
                }
            } catch (e1: Exception) {
                onToastError("服务异常")
            }

        } else {
            onToastError("服务异常")
        }
    }

    //展示错误文本，不需要展示时重写这个方法
    private fun onToastError(errorToast: String) {
        ToastUtils.showShort(errorToast)
    }

    override fun onSuccess(result: T) {
    }

    companion object {
        private lateinit var alertDialog: AlertDialog
        fun showDialog(responseMsg: String) {
            if (null != alertDialog && alertDialog.isShowing) {
                return
            }
            val builder = AlertDialog.Builder(MyApplication.get())
            alertDialog = builder.create()
            alertDialog.setCanceledOnTouchOutside(false)
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }
}
