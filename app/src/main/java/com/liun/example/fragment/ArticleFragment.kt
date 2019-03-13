package com.liun.example.fragment

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.liun.example.R
import com.liun.example.adapter.ProjectViewPagerAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.model.ProjectBean
import com.liun.example.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.fragment_article.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class ArticleFragment:BaseFragment(), OnRequestCallBackListener.ArticleListener {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ProjectViewPagerAdapter

    companion object {
        fun newInstance():ArticleFragment{
            val fragment = ArticleFragment()
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article
    }

    override fun initView(view: View) {
        setPaddingTop(view)
        mTabLayout = view.tabLayout
        mViewPager = view.viewPager

        mViewPagerAdapter = ProjectViewPagerAdapter(childFragmentManager,ProjectViewPagerAdapter.ARTICLE)
        mViewPager.adapter = mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        var presenter = ArticlePresenter()
        presenter.getTreeList(this)
    }

    override fun onSuccess(list: ArrayList<ProjectBean.Data>) {
        if (list.isNotEmpty()) {
            for (i in list.indices) {
                val tab = mTabLayout.newTab()
                tab.text = list[i].name
                if (i == 0) {
                    mTabLayout.addTab(tab, i, true)
                } else {
                    mTabLayout.addTab(tab, i, false)
                }

            }
            mViewPagerAdapter.setList(list)
        }
    }
}