package com.liun.example.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liun.example.R
import com.liun.example.project.model.ProjectBean

/**
 * Description:
 * Author：Liun
 * Date:2019/01/21 16:13
 *
 */
class ArticleListAdapter:BaseQuickAdapter<ProjectBean.Data,BaseViewHolder>(R.layout.item_article) {
    override fun convert(helper: BaseViewHolder, item: ProjectBean.Data) {
        val textView = helper.getView<TextView>(R.id.textView)
        textView.text = item.name
    }
}