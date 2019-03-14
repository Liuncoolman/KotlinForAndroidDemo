package com.liun.example.fragment

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.liun.example.R
import com.liun.example.adapter.ProjectViewPagerAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.project.ProjectBean
import com.liun.example.presenter.WxarticlePresenter
import kotlinx.android.synthetic.main.fragment_wxarticle.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class WxarticleFragment:BaseFragment(), OnRequestCallBackListener.WxarticleChaptersListener {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ProjectViewPagerAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_wxarticle
    }

    companion object {
        fun newInstance():WxarticleFragment{
            val fragment = WxarticleFragment()

            return fragment
        }
    }

    override fun initView(view: View) {
        setPaddingTop(view)
        mTabLayout = view.tabLayout
        mViewPager = view.viewPager

        mViewPagerAdapter = ProjectViewPagerAdapter(childFragmentManager,ProjectViewPagerAdapter.WXARTICLE)
        mViewPager.adapter = mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        var presenter = WxarticlePresenter()
        presenter.getWxarticleChapters(this)
    }

    override fun onSuccess(list:ArrayList<ProjectBean.Data>) {
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