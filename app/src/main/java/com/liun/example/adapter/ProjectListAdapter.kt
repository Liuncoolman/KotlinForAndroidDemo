package com.liun.example.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liun.example.R
import com.liun.example.project.ProjectListBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/14 14:48
 *
 */
class ProjectListAdapter :
    BaseQuickAdapter<ProjectListBean.DataBeanList.DataBean, BaseViewHolder>(R.layout.item_project) {
    override fun convert(helper: BaseViewHolder, item: ProjectListBean.DataBeanList.DataBean) {
        helper.setText(R.id.title, item.title)
        helper.setText(R.id.description, item.desc)
        helper.setText(R.id.date, "${item.niceDate}@${item.author}")
        helper.setText(R.id.source, "${item.superChapterName}/${item.chapterName}")
    }
}