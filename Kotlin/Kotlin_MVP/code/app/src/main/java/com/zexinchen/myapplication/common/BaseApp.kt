package com.zexinchen.myapplication.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化路由
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }
}
