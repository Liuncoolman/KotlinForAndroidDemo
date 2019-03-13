package com.liun.example.http

import com.zhouyou.http.request.GetRequest
import com.zhouyou.http.request.PostRequest

/**
 * Description:
 * Author：Liun
 * Date:2019/01/02 17:01
 *
 */
class HttpManager {

    companion object {
        fun get(url: String): GetRequest {

            return CustomGetRequest(url)
        }

        fun post(url: String): PostRequest {

            return CustomPostRequest(url)
        }
    }
}