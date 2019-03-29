package com.liun.example.blog.presenter

import com.liun.example.blog.contract.BlogContract
import com.liun.example.blog.model.BannerBean
import com.liun.example.blog.model.Blog
import com.liun.example.blog.model.BlogBean
import com.liun.example.http.retrofit.BaseObserver
import com.liun.example.http.retrofit.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 09:35
 *
 */
class BlogPresenter() : BlogContract.Presenter {

    private lateinit var view: BlogContract.View

    constructor(view: BlogContract.View) : this() {
        this.view = view
        view.setPresenter(this)
    }

    override fun getBlogList(index: Int) {
//        HttpModel.HttpGetBlogList(object : BaseCallBack<String>() {
//            override fun onSuccess(result: String) {
//                super.onSuccess(result)
//                val bean = GsonUtils.toBean(result, BlogBean::class.java)
//                if (bean?.errorCode == Constants.CODE_SUCCESS) {
//                    view.blogList(bean)
//                } else {
//                    ToastUtils.showShort(bean?.errorMsg)
//                }
//
//            }
//        }, index)

        RetrofitFactory.instance.getApiService().getBlogList("$index")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :BaseObserver<BlogBean>(view.aty){
                override fun onSuccess(t: BlogBean?) {
                    view.blogList(t!!.datas)
                }
            })
    }

    override fun getBannerList() {
        /*HttpModel.HttpGetBannerList(object : BaseCallBack<String>() {
            override fun onSuccess(result: String) {
                super.onSuccess(result)
                val bean = GsonUtils.toBean(result, BannerBean::class.java)
                if (bean?.errorCode == Constants.CODE_SUCCESS) {
                    view.bannerList(bean)
                } else {
                    ToastUtils.showShort(bean?.errorMsg)
                }
            }
        })*/

        RetrofitFactory.instance.getApiService().getBannerList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :BaseObserver<List<BannerBean>>(null){
                override fun onSuccess(t: List<BannerBean>?) {
                    view.bannerList(t!!)
                }
            })
    }

    override fun start() {
        getBlogList(1)
        getBannerList()
    }

}