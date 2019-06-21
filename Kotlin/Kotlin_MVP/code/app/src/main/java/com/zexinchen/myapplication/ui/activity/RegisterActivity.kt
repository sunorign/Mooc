package com.zexinchen.myapplication.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

import com.zexinchen.myapplication.R
import com.zexinchen.mylibrary.ARoutePath
import com.zexinchen.myapplication.presenter.RegisterPresenter
import com.zexinchen.myapplication.ui.view.RegisterView
import com.zexinchen.mylibrary.FirstActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = ARoutePath.app.PATH_REGISTER)
class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterView {
    override fun onError(msg: String) {
        toast(msg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = RegisterPresenter(this)
        mTvBtn.setOnClickListener {
            mPresenter.register()
        }
    }

    override fun onRegisterResult(msg: String) {
        toast("返回结果：$msg")
        mTvContent.setText("点击跳转 mylibrary module 的第一个页面")
        mTvContent.setOnClickListener {
            startActivity<FirstActivity>()
            finish()
        }
    }

    override fun showloading() {

    }

    override fun hideloading() {

    }
}
