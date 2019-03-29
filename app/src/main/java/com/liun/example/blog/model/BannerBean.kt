package com.liun.example.blog.model

/**
 * Description:
 * Author：Liun
 * Date:2019/01/07 15:23
 *
 */
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)