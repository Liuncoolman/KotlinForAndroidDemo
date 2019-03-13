package com.liun.example.http

/**
 * Description:
 * Author：Liun
 * Date:2019/01/02 16:56
 *
 */
object HttpModel {

    /**
     *  [username] 用户名
     *  [password] 密码
     */
    fun HttpPostLogin(callBack: BaseProgressCallBack<String>, username: String, password: String) {
        HttpManager.post(AppBaseUrl.loginUrl)
            .params("username", username)
            .params("password", password)
            .execute(callBack)
    }

    /**
     * [index] 请求页数
     */
    fun HttpGetBlogList(callBack: BaseCallBack<String>, index: Int) {
        HttpManager.get(AppBaseUrl.BaseUrl + "/article/list/$index/json").execute(callBack)
    }

    /** 首页banner */
    fun HttpGetBannerList(callBack: BaseCallBack<String>){
        HttpManager.get(AppBaseUrl.bannerList).execute(callBack)
    }

    /** 项目分类 */
    fun HttpGetProjectTree(callBack: BaseCallBack<String>){
        HttpManager.get(AppBaseUrl.projectTree).execute(callBack)
    }

    /** 项目列表 */
    fun HttpGetProjectList(callBack: BaseCallBack<String>,index:Int,cid:Int){
        HttpManager.get(AppBaseUrl.projectList +"/$index/json?cid=$cid").execute(callBack)
    }

    /** 公众号分类 */
    fun HttpGetWxarticleChapters(callBack: BaseCallBack<String>){
        HttpManager.get(AppBaseUrl.wxarticleChapters).execute(callBack)
    }

    /** 公众号列表 */
    fun HttpGetWxarticleList(callBack: BaseCallBack<String>,id:Int,index:Int){
        HttpManager.get(AppBaseUrl.wxarticleList +"/$id/$index/json?k=java").execute(callBack)
    }

    /** 体系分类 */
    fun HttpGetArticleTree(callBack: BaseCallBack<String>){
        HttpManager.get(AppBaseUrl.treeList).execute(callBack)
    }

    /** 体系列表 */
    fun HttpGetArticleList(callBack: BaseCallBack<String>,id:Int,index:Int){
        HttpManager.get(AppBaseUrl.articleList +"/$index/json?cid=$id").execute(callBack)
    }

    /** 退出 */
    fun HttpLoginout(callBack: BaseProgressCallBack<String>){
        HttpManager.get(AppBaseUrl.logout).execute(callBack)
    }
}