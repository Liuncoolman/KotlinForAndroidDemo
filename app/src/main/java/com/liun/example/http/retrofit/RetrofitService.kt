package com.liun.example.http.retrofit

import com.liun.example.login.model.LoginBean
import com.liun.example.me.model.LoginOut
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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
    fun logout() : Observable<BaseEntity<LoginOut>>
}
