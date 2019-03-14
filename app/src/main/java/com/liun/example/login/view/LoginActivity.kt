package com.liun.example.login.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.MainActivity
import com.liun.example.R
import com.liun.example.base.BaseActivity
import com.liun.example.login.contract.LoginContract
import com.liun.example.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Description:登录
 * Author：Liun
 * Date:2019/01/02 17:36
 *
 */
class LoginActivity : BaseActivity(),LoginContract.View{

    private lateinit var mLoginPresenter:LoginContract.Presenter

    override fun setLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleText(getString(R.string.login))
        setRightActionGone()
        val aBoolean = SPUtils.getInstance().getBoolean(Constants.KEY_IS_LOGIN, false)
        if (aBoolean) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        LoginPresenter(this)

    }

    override val userName: String
        get() = et_account.text.toString()
    override val userPwd: String
        get() = et_psw.text.toString()
    override val aty: Activity
        get() = this

    override fun setPresenter(presenter: LoginContract.Presenter) {
       this.mLoginPresenter = presenter
    }

    override fun onSuccess(result: String) {
        // 登录成功
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        ToastUtils.showShort(getString(R.string.tip_login_success))
        SPUtils.getInstance().put(Constants.KEY_IS_LOGIN, true)
    }

    fun doLogin(view: View){
        mLoginPresenter.start()
    }
}