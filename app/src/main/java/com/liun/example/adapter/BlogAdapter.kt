package com.liun.example.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liun.example.R
import com.liun.example.blog.model.Blog

/**
 * Description:
 * Author：Liun
 * Date:2019/01/05 16:29
 *
 */
class BlogAdapter :BaseQuickAdapter<Blog,BaseViewHolder>(R.layout.item_blog) {
    override fun convert(helper: BaseViewHolder?, item: Blog?) {
        if (helper==null) return
        if (item ==null) return

        helper.setText(R.id.title,item.title)
        helper.setText(R.id.date,"${item.niceDate} @${item.author}")
        helper.setText(R.id.source,"${item.superChapterName}/${item.chapterName}")
    }
}