package com.liun.example.login

/**
 * Description:
 * Author：Liun
 * Date:2019/01/03 16:50
 *
 */
public interface LoginPresenter {

    fun validateCredentials(username: String, password: String)

}