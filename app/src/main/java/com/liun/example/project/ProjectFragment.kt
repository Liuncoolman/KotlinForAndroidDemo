package com.liun.example.project

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.liun.example.R
import com.liun.example.adapter.ProjectViewPagerAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.impl.OnRequestCallBackListener
import kotlinx.android.synthetic.main.fragment_project.view.*

/**
 * Description:项目
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class ProjectFragment : BaseFragment(), OnRequestCallBackListener.ProjectTreeListener {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ProjectViewPagerAdapter

    companion object {
        fun newInstance(): ProjectFragment {
            val fragment = ProjectFragment()

            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView(view: View) {
        setPaddingTop(view)
        mTabLayout = view.tabLayout
        mViewPager = view.viewPager

        mViewPagerAdapter = ProjectViewPagerAdapter(childFragmentManager, ProjectViewPagerAdapter.WXARTICLE)
        mViewPager.adapter = mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        val mPresenter = ProjectPresenter(activity!!)

        mPresenter.requestProjectData(this)
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