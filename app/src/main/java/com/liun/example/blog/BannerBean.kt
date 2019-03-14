package com.liun.example.blog

import com.liun.example.BaseBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/07 15:23
 *
 */
class BannerBean : BaseBean() {
    /*{
        "desc": "一起来做个App吧",
        "id": 10,
        "imagePath": "http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
        "isVisible": 1,
        "order": 3,
        "title": "一起来做个App吧",
        "type": 0,
        "url": "http://www.wanandroid.com/blog/show/2"
    }*/
    val data :List<Banner> = arrayListOf()

    inner class Banner {
        var desc = ""
        var id = 0
        var imagePath = ""
        var isVisible = 0
        var order = 0
        var title = ""
        var type = 0
        var url = ""
    }

}