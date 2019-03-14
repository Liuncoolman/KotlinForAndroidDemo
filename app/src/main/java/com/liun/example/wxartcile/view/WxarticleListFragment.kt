package com.liun.example.wxartcile.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.WebViewActivity
import com.liun.example.adapter.ProjectListAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.project.model.ProjectListBean
import com.liun.example.wxartcile.contract.WxArticleContract
import com.liun.example.wxartcile.presenter.WxArticleListPresenter
import kotlinx.android.synthetic.main.fragment_wxarticle_list.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/14 10:13
 *
 */
class WxarticleListFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
    SwipeRefreshLayout.OnRefreshListener,WxArticleContract.WxArticListView {


    private var index = 1
    private lateinit var mAdapter: ProjectListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mWxArticleListPresenter: WxArticleContract.WxArticListPresenter

    companion object {
        fun newInstance(cid: Int): WxarticleListFragment {
            val fragment = WxarticleListFragment()
            val bundle = Bundle()
            bundle.putInt("cid", cid)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_wxarticle_list
    }

    override fun initView(view: View) {
        mRecyclerView = view.recyclerView
        mSwipeRefreshLayout = view.swipeRefreshLayout
        val cid = arguments?.getInt("cid")

        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = ProjectListAdapter()
        view.recyclerView.adapter = mAdapter

        if (cid != null) {
            mAdapter.setNewData(null)
            mAdapter.setEmptyView(R.layout.layout_net_loading, view.recyclerView)

            WxArticleListPresenter(cid,this)
            mWxArticleListPresenter.loadWxArticList(index)
        }

        mAdapter.setOnLoadMoreListener(this, view.recyclerView)
        view.swipeRefreshLayout.setOnRefreshListener(this)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.getItem(position) as ProjectListBean.DataBeanList.DataBean
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(Constants.KEY_LINK, item.link)
            intent.putExtra(Constants.KEY_TITLE, "公众号")
            startActivity(intent)

        }
    }

    override fun getChaterListByCid(data: ProjectListBean) {
        if (data.data.datas.isNotEmpty()) {
            if (index == 1) {
                mAdapter.setNewData(data.data.datas)
            } else {
                mAdapter.addData(data.data.datas)
            }

            if (data.data.datas.size < 15) mAdapter.loadMoreEnd() else mAdapter.loadMoreComplete()
        } else {
            mAdapter.setNewData(null)
            mAdapter.setEmptyView(R.layout.layout_net_nodata, mRecyclerView)
        }

        mAdapter.setEnableLoadMore(true)
        if (mSwipeRefreshLayout.isRefreshing) mSwipeRefreshLayout.isRefreshing = false
    }

    override fun setPresenter(presenter: WxArticleContract.WxArticListPresenter) {
        mWxArticleListPresenter = presenter
    }

    override fun onRefresh() {
        if (this::mWxArticleListPresenter.isLateinit) {
            mAdapter.setNewData(null)
            mAdapter.setEmptyView(R.layout.layout_net_loading, mRecyclerView)
            index = 1
            mWxArticleListPresenter.loadWxArticList(index)
        } else {
            mSwipeRefreshLayout.isRefreshing = false
            ToastUtils.showShort("id is null")
        }
    }

    override fun onLoadMoreRequested() {
        index++
        mWxArticleListPresenter.loadWxArticList(index)
    }

}