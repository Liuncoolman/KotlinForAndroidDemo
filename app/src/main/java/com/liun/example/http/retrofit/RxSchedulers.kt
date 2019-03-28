package com.liun.example.http.retrofit

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Author：Liun
 * Date:2019/03/28 16:44
 */
object RxSchedulers {

    fun <T> compose(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    // if (!Utils.isNetworkConnected()) {
                    //     Toast.makeText(context, R.string.toast_network_error, Toast.LENGTH_SHORT).show();
                    // }
                }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}