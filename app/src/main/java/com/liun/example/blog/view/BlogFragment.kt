package com.liun.example.blog.view

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liun.example.Constants
import com.liun.example.R
import com.liun.example.WebViewActivity
import com.liun.example.adapter.BlogAdapter
import com.liun.example.base.BaseFragment
import com.liun.example.blog.contract.BlogContract
import com.liun.example.blog.model.BannerBean
import com.liun.example.blog.model.Blog
import com.liun.example.blog.presenter.BlogPresenter
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.blog_banner.view.*
import kotlinx.android.synthetic.main.fragment_blog.view.*


/**
 * Description:博文
 * Author：Liun
 * Date:2019/01/05 14:57
 *
 */
class BlogFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
    SwipeRefreshLayout.OnRefreshListener, BlogContract.View {
    override val aty: FragmentActivity
        get() = activity!!

    private var index = 1
    private lateinit var mAdapter: BlogAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mBanner: Banner
    private lateinit var mBlogPresenter: BlogContract.Presenter

    companion object {
        fun newInstance(): BlogFragment {
            val fragment = BlogFragment()

            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_blog
    }

    override fun initView(view: View) {
        mRecyclerView = view.recyclerView
        mSwipeRefreshLayout = view.swipeRefreshLayout
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = BlogAdapter()
        view.recyclerView.adapter = mAdapter
        // banner
        initBanner(view)
        // blog
        initBlogView(view)

        BlogPresenter(this)
        mBlogPresenter.start()
    }

    private fun initBanner(v: View) {
        val view = LayoutInflater.from(activity).inflate(R.layout.blog_banner, null)
        mBanner = view.banner
        mAdapter.addHeaderView(view)
    }

    private fun initBlogView(v: View) {
        mAdapter.setNewData(null)
        mAdapter.setEmptyView(R.layout.layout_net_loading, v.recyclerView)

        mAdapter.setOnLoadMoreListener(this, v.recyclerView)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val blog = adapter.getItem(position) as Blog
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(Constants.KEY_LINK, blog.link)
            intent.putExtra(Constants.KEY_TITLE, "博文")
            startActivity(intent)
        }

        v.swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun blogList(blogs: List<Blog>) {
        if (blogs.isNotEmpty()) {

            if (index == 1) {
                mAdapter.setNewData(blogs)
            } else {
                mAdapter.addData(blogs)
            }

            if (blogs.size < 15) mAdapter.loadMoreEnd() else mAdapter.loadMoreComplete()
        } else {
            mAdapter.setNewData(null)
            mAdapter.setEmptyView(R.layout.layout_net_nodata, mRecyclerView)
        }

        mAdapter.setEnableLoadMore(true)
        if (mSwipeRefreshLayout.isRefreshing) mSwipeRefreshLayout.isRefreshing = false
    }

    override fun bannerList(banners: List<BannerBean>) {
        if (banners.isNotEmpty()) {
            val titleList = arrayListOf<String>()
            val imageList = arrayListOf<String>()
            for (banner in banners) {
                titleList.add(banner.title)
                imageList.add(banner.imagePath)
            }
            //设置banner样式
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
            //设置图片加载器
            mBanner.setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                    Glide.with(context).load(path).into(imageView)
                }
            })
            //设置图片集合
            mBanner.setImages(imageList)
            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.DepthPage)
            //设置标题集合（当banner样式有显示title时）
            mBanner.setBannerTitles(titleList)
            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true)
            //设置轮播时间
            mBanner.setDelayTime(3000)
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(BannerConfig.CENTER)
            //banner设置方法全部调用完毕时最后调用
            mBanner.start()
        }
    }

    override fun setPresenter(presenter: BlogContract.Presenter) {
        this.mBlogPresenter = presenter
    }

    override fun onLoadMoreRequested() {
        mBlogPresenter.getBlogList(index++)
    }

    override fun onRefresh() {
        mAdapter.setNewData(null)
        mAdapter.setEmptyView(R.layout.layout_net_loading, mRecyclerView)
        index = 1
        mBlogPresenter.getBlogList(index)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBanner.stopAutoPlay()
    }
}