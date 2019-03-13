package com.liun.example.http

import com.zhouyou.http.callback.CallBack
import com.zhouyou.http.callback.CallBackProxy
import com.zhouyou.http.callback.CallClazzProxy
import com.zhouyou.http.model.ApiResult
import com.zhouyou.http.request.GetRequest
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.lang.reflect.Type

/**
 * Description:
 * Author：Liun
 * Date:2019/01/02 17:11
 *
 */
class CustomGetRequest(url: String?) : GetRequest(url) {

    override fun <T : Any?> execute(clazz: Class<T>?): Observable<T> {
        return super.execute(clazz)
    }

    override fun <T : Any?> execute(callBack: CallBack<T>?): Disposable {
        return super.execute(callBack)
    }

    override fun <T : Any?> execute(proxy: CallBackProxy<out ApiResult<T>, T>?): Disposable {
        return super.execute(proxy)
    }

    override fun <T : Any?> execute(proxy: CallClazzProxy<out ApiResult<T>, T>?): Observable<T> {
        return super.execute(proxy)
    }

    override fun <T : Any?> execute(type: Type?): Observable<T> {
        return super.execute(type)
    }
}