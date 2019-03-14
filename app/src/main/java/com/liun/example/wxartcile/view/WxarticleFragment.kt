package com.liun.example.wxartcile.view

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.liun.example.R
import com.liun.example.adapter.ProjectViewPagerAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.project.model.ProjectBean
import com.liun.example.wxartcile.contract.WxArticleContract
import com.liun.example.wxartcile.presenter.WxArticleChaterPresenter
import kotlinx.android.synthetic.main.fragment_wxarticle.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class WxarticleFragment:BaseFragment(), WxArticleContract.WxArticChaterView {


    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ProjectViewPagerAdapter
    private lateinit var mWxArticleChaterPresenter:WxArticleContract.WxArticChaterPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_wxarticle
    }

    companion object {
        fun newInstance(): WxarticleFragment {
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

        WxArticleChaterPresenter(this)
        mWxArticleChaterPresenter.start()
    }

    override fun getChaterList(projectBean: ProjectBean) {
        if (projectBean.data.isNotEmpty()) {
            for (i in projectBean.data.indices) {
                val tab = mTabLayout.newTab()
                tab.text = projectBean.data[i].name
                if (i == 0) {
                    mTabLayout.addTab(tab, i, true)
                } else {
                    mTabLayout.addTab(tab, i, false)
                }

            }
            mViewPagerAdapter.setList(projectBean.data)
        }
    }

    override fun setPresenter(presenter: WxArticleContract.WxArticChaterPresenter) {
        this.mWxArticleChaterPresenter = presenter
    }
}