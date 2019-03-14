package com.liun.example.project.model

import com.liun.example.base.BaseBean
import java.io.Serializable

/**
 * Description:
 * Author：Liun
 * Date:2019/01/14 14:36
 *
 */
class ProjectListBean : BaseBean(), Serializable {
    /* {
         "apkLink": "",
         "author": "chinalwb",
         "chapterId": 294,
         "chapterName": "完整项目",
         "collect": false,
         "courseId": 13,
         "desc": "用组件化的思想实现一个玩Android APP。目前首页、项目、公众号分别以组件的形式显示到了app中。项目地址附上了实现步骤，欢迎实现您自己的组件！欢迎提出问题 欢迎讨论 欢迎赐教。",
         "envelopePic": "http://www.wanandroid.com/blogimgs/76eb003b-7db1-4e00-be3d-3076b48afc8c.png",
         "fresh": true,
         "id": 7775,
         "link": "http://www.wanandroid.com/blog/show/2483",
         "niceDate": "23小时前",
         "origin": "",
         "projectLink": "https://github.com/chinalwb/wan_android",
         "publishTime": 1547364225000,
         "superChapterId": 294,
         "superChapterName": "开源项目主Tab",
         "tags": [
         {
             "name": "项目",
             "url": "/project/list/1?cid=294"
         }
         ],
         "title": "组件化的玩Android App",
         "type": 0,
         "userId": -1,
         "visible": 1,
         "zan": 0
     }*/

    var data: DataBeanList = DataBeanList()

    inner class DataBeanList {
        var datas = arrayListOf<DataBean>()

        inner class DataBean {
            var apkLink = ""
            var author = ""
            var chapterId = 0
            var chapterName = ""
            var collect = false
            var courseId = 0
            var desc = ""
            var envelopePic = ""
            var fresh = false
            var id = 0
            var link = ""
            var niceDate = ""
            var origin = ""
            var projectLink = ""
            var publishTime: Double = 0.0
            var superChapterId = 0
            var superChapterName = ""
            var tags = arrayListOf<Tag>()
            var title = ""
            var type = 0
            var userId = 0
            var visible = 0
            var zan = 0

            inner class Tag {
                var name = ""
                var url = ""
            }
        }
    }


}