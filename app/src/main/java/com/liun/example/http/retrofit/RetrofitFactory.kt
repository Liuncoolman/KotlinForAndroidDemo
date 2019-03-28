package com.liun.example.http.retrofit

import com.liun.example.http.AppBaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Description:
 * Author：Liun
 * Date:2019/03/27 17:00
 *
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = RetrofitFactory()

    }

    fun getApiService(): RetrofitService {

        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder
            .connectTimeout(5000, TimeUnit.SECONDS)
            .readTimeout(5000, TimeUnit.SECONDS)

            /** 对所有请求添加请求头 */
//            .addInterceptor(
//                object : Interceptor {
//                    @Throws(IOException::class)
//                    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//                        val request = chain.request()
//                        val originalResponse = chain.proceed(request)
//                        return originalResponse.newBuilder().header("key1", "value1").addHeader("key2", "value2")
//                            .build()
//                    }
//                })

            /** 加HttpLoggingInterceptor拦截器方便调试接口 */
            .addInterceptor(HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
                override fun log(message: String?) {

                }

            }).setLevel(HttpLoggingInterceptor.Level.BASIC))

        return Retrofit.Builder()
            .client(httpClientBuilder.build())
            .baseUrl(AppBaseUrl.BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)

    }
}