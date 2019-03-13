package com.liun.example.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.liun.example.fragment.ArticleFragment
import com.liun.example.fragment.ArticleListFragment
import com.liun.example.fragment.ProjectListFragment
import com.liun.example.fragment.WxarticleListFragment
import com.liun.example.model.ProjectBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/14 10:11
 *
 */
class ProjectViewPagerAdapter(fm: FragmentManager, var tab: Int) : FragmentStatePagerAdapter(fm) {

    private val mList: ArrayList<ProjectBean.Data> = arrayListOf()

    override fun getItem(p0: Int): Fragment? {
        val data = mList[p0]
        when (tab) {
            PROJECT -> return ProjectListFragment.newInstance(data.id)
            WXARTICLE -> return WxarticleListFragment.newInstance(data.id)
            ARTICLE -> return ArticleListFragment.newInstance(data)
        }

        return null
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position].name
    }


    fun setList(list: ArrayList<ProjectBean.Data>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }

    companion object {
        const val PROJECT = 0
        const val WXARTICLE = 1
        const val ARTICLE = 2
    }
}