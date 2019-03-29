package com.liun.example.me.presenter

import com.liun.example.http.retrofit.BaseObserver
import com.liun.example.http.retrofit.RetrofitFactory
import com.liun.example.me.contract.MeContract
import com.liun.example.me.model.LoginOut
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Author：Liun
 * Date:2019/01/26 15:28
 *
 */
class MePresenter(private val view: MeContract.MeView) : MeContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun loadLoginOut() {
        /*HttpModel.HttpLoginout(object : BaseProgressCallBack<String>(BaseIProgressDialog(view.aty)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.loginOut(result)
                }
            }
        })*/

        RetrofitFactory.instance.getApiService().logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :BaseObserver<LoginOut>(view.aty){
                override fun onSuccess(t: LoginOut?) {
                    view.loginOut("")
                }
            })
    }

    override fun start() {
    }
}