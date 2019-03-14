package com.liun.example.blog

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 09:37
 *
 */
interface BlogView {

    fun bannerList(bannerBean: BannerBean)

    fun blogList(blogBean: BlogBean)
}