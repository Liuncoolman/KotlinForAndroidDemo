package com.liun.example.setup.view

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.liun.example.R
import com.liun.example.adapter.ProjectViewPagerAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.project.model.ProjectBean
import com.liun.example.setup.contract.SetupContract
import com.liun.example.setup.presenter.SetupChaterPresenter
import kotlinx.android.synthetic.main.fragment_article.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class SetupFragment : BaseFragment(), SetupContract.SetupChaterView {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ProjectViewPagerAdapter
    private lateinit var mSetupChaterPresenter: SetupContract.SetupChaterPresenter

    companion object {
        fun newInstance(): SetupFragment {
            val fragment = SetupFragment()
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

        mViewPagerAdapter = ProjectViewPagerAdapter(childFragmentManager, ProjectViewPagerAdapter.ARTICLE)
        mViewPager.adapter = mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        SetupChaterPresenter(this)
        mSetupChaterPresenter.start()
    }

    override fun getSetupChater(list: ArrayList<ProjectBean.Data>) {
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

    override fun setPresenter(presenter: SetupContract.SetupChaterPresenter) {
        this.mSetupChaterPresenter = presenter
    }
}