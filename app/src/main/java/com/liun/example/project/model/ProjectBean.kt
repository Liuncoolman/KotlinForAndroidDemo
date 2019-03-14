package com.liun.example.project.model

import com.liun.example.base.BaseBean
import java.io.Serializable

class ProjectBean : BaseBean() {
    var data: ArrayList<Data> = arrayListOf()

    inner class Data : Serializable {
        var children: List<Data>? = null
        var courseId: Int = 0
        var id: Int = 0
        var name: String = ""
        var order: Int = 0
        var parentChapterId: Int = 0
        var userControlSetTop: Boolean = false
        var visible: Int = 0

    }
}