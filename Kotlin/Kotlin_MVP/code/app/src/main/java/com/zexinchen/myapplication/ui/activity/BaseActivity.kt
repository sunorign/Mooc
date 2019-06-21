package com.zexinchen.myapplication.ui.activity

import android.support.v7.app.AppCompatActivity

import com.zexinchen.myapplication.presenter.BasePresenter
import com.zexinchen.myapplication.ui.view.BaseView

abstract class BaseActivity<T : BasePresenter<*>> : AppCompatActivity(), BaseView{
    protected lateinit var mPresenter: T
}
