package com.liun.example.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.adapter.ProjectListAdapter
import com.liun.example.base.BaseActivity
import com.liun.example.impl.OnRequestCallBackListener
import com.liun.example.model.ProjectBean
import com.liun.example.model.ProjectListBean
import com.liun.example.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/26 14:18
 *
 */
class ArticleDetailActivity : BaseActivity(), OnRequestCallBackListener.ArticleListListener,
    BaseQuickAdapter.RequestLoadMoreListener {

    private var index = 0
    private lateinit var mAdapter: ProjectListAdapter
    private lateinit var mPresenter: ArticlePresenter

    override fun setLayoutId(): Int {
        return R.layout.activity_article_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getSerializableExtra(Constants.KEY_ARTICEL_ITEM) as ProjectBean.Data
        setTitleText(data.name)
        setRightActionGone()

        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ProjectListAdapter()
        recyclerView.adapter = mAdapter
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val dataBean = mAdapter.data[position]
            val link = dataBean.link
            val intent = Intent(this,WebViewActivity::class.java)
            intent.putExtra(Constants.KEY_LINK,link)
            startActivity(intent)
        }

        mPresenter = ArticlePresenter()
        mPresenter.setId(data.id)
        mPresenter.setListener(this)
        mPresenter.getArticleList(index)
    }

    override fun onSuccess(list: ArrayList<ProjectListBean.DataBeanList.DataBean>) {
        if (list.isNotEmpty()) {
            if (index == 0) {
                mAdapter.setNewData(list)
            } else {
                mAdapter.addData(list)
            }

            if (list.size < 20) mAdapter.loadMoreEnd() else mAdapter.loadMoreComplete()
        } else {
            mAdapter.setNewData(null)
            mAdapter.setEmptyView(R.layout.layout_net_nodata, recyclerView)
        }

        mAdapter.setEnableLoadMore(true)

    }

    override fun onLoadMoreRequested() {
        index++
        mPresenter.getArticleList(index)
    }
}