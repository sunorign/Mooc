package com.zexinchen.mylibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //Anko dialog
        alert(intent.getStringExtra("msg"), "第一个页面传过来的参数，点击“ok”路由跳转") {
            yesButton {
                //路由跳转到 app RegisterActivity
                ARouter.getInstance().build(ARoutePath.app.PATH_REGISTER).navigation()
            }
            noButton {}
        }.show()
    }
}
