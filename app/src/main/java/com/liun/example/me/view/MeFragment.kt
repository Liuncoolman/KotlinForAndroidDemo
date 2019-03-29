package com.liun.example.me.view

import android.app.Activity
import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.base.BaseFragment
import com.liun.example.login.view.LoginActivity
import com.liun.example.me.contract.MeContract
import com.liun.example.me.presenter.MePresenter
import kotlinx.android.synthetic.main.fragment_me.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class MeFragment : BaseFragment(), MeContract.MeView {

    private lateinit var mPresenter: MeContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    companion object {
        fun newInstance(): MeFragment {
            val fragment = MeFragment()

            return fragment
        }
    }

    override fun initView(view: View) {
        setPaddingTop(view)
        MePresenter(this)
        view.layoutExit.setOnClickListener {
            mPresenter.loadLoginOut()
        }

        setTitleGone(view, false)
        setActionLeftGone(view)
        setActionRightGone(view)
        setTitleText(view, getString(R.string.tab_05))
    }

    override val aty: Activity
        get() = activity!!

    override fun loginOut(result: String) {
        SPUtils.getInstance().put(Constants.KEY_IS_LOGIN, false)
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        ToastUtils.showShort("登出成功")
        activity?.finish()
    }

    override fun setPresenter(presenter: MeContract.Presenter) {
        mPresenter = presenter
    }
}