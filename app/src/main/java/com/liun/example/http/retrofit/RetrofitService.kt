package com.liun.example.http.retrofit

import com.liun.example.blog.model.BannerBean
import com.liun.example.blog.model.Blog
import com.liun.example.blog.model.BlogBean
import com.liun.example.login.model.LoginBean
import com.liun.example.me.model.LoginOut
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Description:
 * Author：Liun
 * Date:2019/03/27 10:45
 */
interface RetrofitService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<BaseEntity<LoginBean>>

    @GET("user/logout/json")
    fun logout(): Observable<BaseEntity<LoginOut>>

    @GET("banner/json")
    fun getBannerList(): Observable<BaseEntity<List<BannerBean>>>

    @GET("article/list/{index}/json")
    fun getBlogList(@Path("index") index: String):Observable<BaseEntity<BlogBean>>
}
