package com.liun.example.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.activity.ArticleDetailActivity
import com.liun.example.adapter.ArticleListAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.model.ProjectBean
import kotlinx.android.synthetic.main.fragment_article_list.view.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/21 14:30
 *
 */
class ArticleListFragment : BaseFragment() {
    private lateinit var mAdapter: ArticleListAdapter
    private lateinit var mRecyclerView: RecyclerView

    companion object {
        fun newInstance(data: ProjectBean.Data): ArticleListFragment {
            val fragment = ArticleListFragment()
            val bundle = Bundle()
            bundle.putSerializable("data", data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_list
    }

    override fun initView(view: View) {
        mRecyclerView = view.recyclerView
        val data = arguments?.getSerializable("data") as ProjectBean.Data

        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = ArticleListAdapter()
        view.recyclerView.adapter = mAdapter
        mAdapter.setNewData(data.children)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item = mAdapter.data[position]
            val bundle = Bundle()
            bundle.putSerializable(Constants.KEY_ARTICEL_ITEM, item)
            val intent = Intent(activity, ArticleDetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

}