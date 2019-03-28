package com.liun.example.login.model

/**
 * Description:
 * Author：Liun
 * Date:2019/03/27 11:29
 *
 */
data class LoginBean(
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)