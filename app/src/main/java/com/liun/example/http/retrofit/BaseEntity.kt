package com.liun.example.http.retrofit

import com.google.gson.annotations.SerializedName

/**
 * Description:
 * Author：Liun
 * Date:2019/03/28 11:13
 */
class BaseEntity<T> {

    @SerializedName("errorCode")
    var code: Int = 0
    @SerializedName("errorMsg")
    var msg: String? = null
    @SerializedName("data")
    var data: T? = null

    val isSuccess: Boolean
        get() = code == 0
}
