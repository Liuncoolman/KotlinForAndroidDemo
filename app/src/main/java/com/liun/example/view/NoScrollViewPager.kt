package com.liun.example.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Description: 禁止/允许 左右滑动
 * Author：Liun
 * Date:2019/01/12 17:33
 *
 */
class NoScrollViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private var isScroll: Boolean = false



    fun setScroll(aBoolean:Boolean){
        this.isScroll = aBoolean
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isScroll
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isScroll
    }
}