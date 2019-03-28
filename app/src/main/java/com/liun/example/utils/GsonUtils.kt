package com.liun.example.utils

import com.google.gson.Gson

object GsonUtils {

    fun toJson(t : Any) : String{
        return Gson().toJson(t)
    }

    fun <T> toBean(t : String?, type: Class<T>) : T? {
        return Gson().fromJson(t, type)
    }

}