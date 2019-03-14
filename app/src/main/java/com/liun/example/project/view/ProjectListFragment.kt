package com.liun.example.project.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.WebViewActivity
import com.liun.example.adapter.ProjectListAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.project.contract.ProjectContract
import com.liun.example.project.model.ProjectListBean
import com.liun.example.project.presenter.ProjectListPresenter
import kotlinx.android.synthetic.main.fragment_project_list.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/14 10:13
 *
 */
class ProjectListFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
    SwipeRefreshLayout.OnRefreshListener, ProjectContract.ProjectListView {

    private var index = 1
    private lateinit var mAdapter: ProjectListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mProjectListPresenter: ProjectContract.ProjectListPresenter

    companion object {
        fun newInstance(cid: Int): ProjectListFragment {
            val fragment = ProjectListFragment()
            val bundle = Bundle()
            bundle.putInt("cid", cid)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project_list
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

            ProjectListPresenter(cid, this)
            mProjectListPresenter.loadProjectList(index)
        }

        mAdapter.setOnLoadMoreListener(this, view.recyclerView)
        view.swipeRefreshLayout.setOnRefreshListener(this)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.getItem(position) as ProjectListBean.DataBeanList.DataBean
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(Constants.KEY_LINK, item.link)
            intent.putExtra(Constants.KEY_TITLE, "项目")
            startActivity(intent)

        }
    }

    override fun getProjectList(datas: ArrayList<ProjectListBean.DataBeanList.DataBean>) {
        if (datas.isNotEmpty()) {
            if (index == 1) {
                mAdapter.setNewData(datas)
            } else {
                mAdapter.addData(datas)
            }

            if (datas.size < 15) mAdapter.loadMoreEnd() else mAdapter.loadMoreComplete()
        } else {
            mAdapter.setNewData(null)
            mAdapter.setEmptyView(R.layout.layout_net_nodata, mRecyclerView)
        }

        mAdapter.setEnableLoadMore(true)
        if (mSwipeRefreshLayout.isRefreshing) mSwipeRefreshLayout.isRefreshing = false
    }

    override fun setPresenter(presenter: ProjectContract.ProjectListPresenter) {
        this.mProjectListPresenter = presenter
    }

    override fun onRefresh() {
        mAdapter.setNewData(null)
        mAdapter.setEmptyView(R.layout.layout_net_loading, mRecyclerView)
        index = 1
        mProjectListPresenter.loadProjectList(index)
    }

    override fun onLoadMoreRequested() {
        index++
        mProjectListPresenter.loadProjectList(index)
    }

}