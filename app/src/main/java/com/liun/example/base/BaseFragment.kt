package com.liun.example.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liun.example.R
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.layout_title.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/12 16:47
 *
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        val contentView = inflater.inflate(getLayoutId(), container, false)
        view.container.addView(contentView)
        initView(view)
        return view
    }

    protected open fun setPaddingTop(view: View) {
        view.setPadding(0, getStatusBarHeight(view.context), 0, 0)
    }

    protected open fun setTitleGone(view: View, aBoolean: Boolean) {
        if (aBoolean) {
            view.layoutTitle.visibility = View.GONE
        } else {
            view.layoutTitle.visibility = View.VISIBLE
            view.layoutTitle.actionLeft.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    protected open fun setTitleText(view: View, title: String) {
        view.layoutTitle.tvTitle.text = title
    }

    protected open fun setActionLeftGone(view: View){
        view.layoutTitle.actionLeft.visibility = View.GONE
    }

    protected open fun setActionRightGone(view: View){
        view.layoutTitle.actionRight.visibility = View.GONE
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private fun getStatusBarHeight(context: Context): Int {
        // 获得状态栏高度
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(view: View)
}