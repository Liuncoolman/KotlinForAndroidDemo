package com.liun.example.project.view

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.liun.example.R
import com.liun.example.adapter.ProjectViewPagerAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.project.contract.ProjectContract
import com.liun.example.project.model.ProjectBean
import com.liun.example.project.presenter.ProjectChaterPresenter
import kotlinx.android.synthetic.main.fragment_project.view.*

/**
 * Description:项目
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class ProjectFragment : BaseFragment(), ProjectContract.ProjectChaterView {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ProjectViewPagerAdapter
    private lateinit var mProjectChaterPresenter: ProjectContract.ProjectChaterPresenter

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

        mViewPagerAdapter = ProjectViewPagerAdapter(childFragmentManager, ProjectViewPagerAdapter.PROJECT)
        mViewPager.adapter = mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        ProjectChaterPresenter(this)

        mProjectChaterPresenter.start()
    }

    override fun getProjectChater(data: ArrayList<ProjectBean.Data>) {
        if (data.isNotEmpty()) {
            for (i in data.indices) {
                val tab = mTabLayout.newTab()
                tab.text = data[i].name
                if (i == 0) {
                    mTabLayout.addTab(tab, i, true)
                } else {
                    mTabLayout.addTab(tab, i, false)
                }

            }
            mViewPagerAdapter.setList(data)
        }
    }

    override fun setPresenter(presenter: ProjectContract.ProjectChaterPresenter) {
        this.mProjectChaterPresenter = presenter
    }
}