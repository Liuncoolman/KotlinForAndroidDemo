package com.liun.example.http.retrofit

import android.app.Activity
import android.app.Dialog
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.http.BaseIProgressDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Description:
 * Author：Liun
 * Date:2019/03/28 11:13
 */
abstract class BaseObserver<T>(private var activity: Activity?) : Observer<BaseEntity<T>> {

    private var dialog: Dialog? = null

    override fun onSubscribe(d: Disposable) {
        if (null != activity) {
            dialog = BaseIProgressDialog(activity!!).dialog!!
            dialog!!.show()
        }

    }

    override fun onNext(value: BaseEntity<T>) {
        if (value.isSuccess) {
            val t = value.data
            onSuccess(t)
        } else {
            onError(value.msg)
        }
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "error:$e")
        if (null != dialog) dialog!!.dismiss()
    }

    override fun onComplete() {
        Log.d(TAG, "onComplete")
        if (null != dialog) dialog!!.dismiss()
    }


    protected abstract fun onSuccess(t: T?)

    protected fun onError(msg: String?) {
        ToastUtils.showShort(msg)
    }

    companion object {

        private val TAG = "BaseObserver"
    }
}