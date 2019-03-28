package com.liun.example.login.presenter

import com.liun.example.http.retrofit.BaseObserver
import com.liun.example.http.retrofit.RetrofitFactory
import com.liun.example.login.contract.LoginContract
import com.liun.example.login.model.LoginBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Author：Liun
 * Date:2019/01/03 16:50
 *
 */
class LoginPresenter() : LoginContract.Presenter {
    private lateinit var view: LoginContract.View

    constructor(view: LoginContract.View) : this() {
        this.view = view
        view.setPresenter(this)
    }

    override fun login() {
        /*HttpModel.HttpPostLogin(object : BaseProgressCallBack<String>(BaseIProgressDialog(view.aty)) {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = com.liun.example.utils.GsonUtils.toBean(result, BaseBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.onSuccess(result)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        }, view.userName, view.userPwd)*/

        val observable =
            RetrofitFactory.instance.getApiService().login(view.userName, view.userPwd)
        observable
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<LoginBean>(view.aty) {
                override fun onSuccess(t: LoginBean?) {
                    view.onSuccess("")
                }
            })
    }

    override fun start() {
        login()
    }
}
