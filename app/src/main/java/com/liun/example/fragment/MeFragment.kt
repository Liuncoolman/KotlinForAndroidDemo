package com.liun.example.fragment

import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.login.LoginActivity
import com.liun.example.base.BaseFragment
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.presenter.MePresenter
import kotlinx.android.synthetic.main.fragment_me.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class MeFragment : BaseFragment(), OnRequestCallBackListener.LoginoutListener {

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
        view.layoutExit.setOnClickListener {
            MePresenter().loginOut(this@MeFragment.activity!!, this)
        }

        setTitleGone(view, false)
        setActionLeftGone(view)
        setActionRightGone(view)
        setTitleText(view, getString(R.string.tab_05))
    }

    override fun onSuccess(result: String) {
        SPUtils.getInstance().put(Constants.KEY_IS_LOGIN, false)
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


}