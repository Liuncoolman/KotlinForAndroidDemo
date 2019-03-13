package com.liun.example.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import com.liun.example.R
import com.liun.example.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_title.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/03 17:18
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        setContentLayout(setLayoutId())
        setStatusBar()
        setActionLeftClickListener()
    }

    private fun setContentLayout(layoutId: Int) {
        val contentView = layoutInflater.inflate(layoutId, null)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(contentView, params)
    }

    protected fun setTitleGone() {
        layoutTitle.visibility = View.GONE

    }

    protected fun setRightActionGone(){
        layoutTitle.actionRight.visibility = View.GONE
    }

    protected fun setTitleText(title: String) {
        layoutTitle.tvTitle.text = title
    }

    protected open fun setStatusBar(){
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary),0)
    }

    private fun setActionLeftClickListener(){
        layoutTitle.actionLeft.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
    }

    abstract fun setLayoutId(): Int
}