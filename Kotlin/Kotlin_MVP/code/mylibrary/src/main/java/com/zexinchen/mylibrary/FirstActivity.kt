package com.zexinchen.mylibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*
import org.jetbrains.anko.startActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        mTvContent.setOnClickListener {
            //携带参数跳转 secondActivity
            startActivity<SecondActivity>(
                    "msg" to "你好！"
            )
        }
    }
}
