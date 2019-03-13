package com.liun.example.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.liun.example.fragment.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:54
 *
 */
class MyViewAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 -> return BlogFragment.newInstance()
            1 -> return ProjectFragment.newInstance()
            2 -> return WxarticleFragment.newInstance()
            3 -> return ArticleFragment.newInstance()
            4 -> return MeFragment.newInstance()
            else -> return BlogFragment.newInstance()
        }

    }

    override fun getCount(): Int {
        return 5
    }
}