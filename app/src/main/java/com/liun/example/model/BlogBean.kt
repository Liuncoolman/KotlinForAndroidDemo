package com.liun.example.model

import com.liun.example.BaseBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 16:33
 *
 */

class BlogBean : BaseBean() {
    /*
        "apkLink": "",
        "author": "闲鱼",
        "chapterId": 375,
        "chapterName": "Flutter",
        "collect": false,
        "courseId": 13,
        "desc": "",
        "envelopePic": "",
        "fresh": true,
        "id": 7738,
        "link": "https://www.yuque.com/xytech/flutter",
        "niceDate": "21小时前",
        "origin": "",
        "projectLink": "",
        "publishTime": 1546598723000,
        "superChapterId": 375,
        "superChapterName": "跨平台",
        "tags": [],
        "title": "闲鱼Flutter技术合集",
        "type": 0,
        "userId": -1,
        "visible": 1,
        "zan": 0
    */

    var data: Data = Data()

    class Data {
        var datas: List<Blog> = arrayListOf()
        var curPage: Int = 0
        var offset: Int = 0
        var pageCount = 0
        var size: Int = 0
        var total = 0
        var over: Boolean = false
    }


    class Blog {
        var apkLink: String = ""
        var author: String = ""
        var chapterId: Int = 0
        var chapterName: String = ""
        var collect: Boolean = false
        var courseId: Int = 0
        var desc: String = ""
        var envelopePic: String = ""
        var fresh: Boolean = false
        var id: Int = 0
        var link: String = ""
        var niceDate: String = ""
        var origin: String = ""
        var projectLink: String = ""
        var publishTime: Long = 0L
        var superChapterId: Int = 0
        var superChapterName: String = ""
        var tags: List<Any> = arrayListOf()
        var title: String = ""
        var type: Int = 0
        var userId: Int = 0
        var visible: Int = 0
        var zan: Int = 0
    }
}