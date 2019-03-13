package com.liun.example.http

import com.zhouyou.http.model.ApiResult
import java.util.*

/**
 * Description:
 * Author：Liun
 * Date:2019/01/02 17:16
 *
 */
class BaseApiResult<T:Any> : ApiResult<T>() {
    /*"data": ...,
    "errorCode": 0,
    "errorMsg": ""*/

    var errorMsg :String? = null
    var data :String? = null
    // errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
    // errorCode = -1001 代表登录失效，需要重新登录。
    var errorCode :String? = null

}