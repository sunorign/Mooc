package com.zexinchen.myapplication.ui.view

interface BaseView {
    fun showloading()

    fun hideloading()

    fun onError(msg:String)
}
