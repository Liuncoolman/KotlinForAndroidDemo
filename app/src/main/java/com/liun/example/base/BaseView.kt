package com.liun.example.base

/**
 * Description:
 * Author：Liun
 * Date:2019/03/14 14:13
 *
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}