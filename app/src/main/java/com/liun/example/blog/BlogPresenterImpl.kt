package com.liun.example.blog

/**
 * Description:视图层
 * Author：Liun
 * Date:2019/01/05 16:12
 *
 */
class BlogPresenterImpl(blogView: BlogView) : BlogPresenter {

    private var mBlogModel: BlogModel = BlogModelImpl(blogView)

    override fun getBanner() {
        mBlogModel.getBannerList()
        getBlog(1)
    }

    override fun getBlog(index: Int) {
        mBlogModel.getBlogList(index)
    }
}