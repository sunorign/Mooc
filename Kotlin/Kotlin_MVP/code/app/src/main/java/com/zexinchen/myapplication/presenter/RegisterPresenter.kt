package com.zexinchen.myapplication.presenter

import com.zexinchen.myapplication.common.BaseObserver
import com.zexinchen.myapplication.common.execute
import com.zexinchen.myapplication.data.bean.RespRegister
import com.zexinchen.myapplication.service.imp.UserServiceImp
import com.zexinchen.myapplication.ui.view.RegisterView

class RegisterPresenter(override var mView: RegisterView) : BasePresenter<RegisterView>() {
    private val userService = UserServiceImp()
    fun register() {
        userService.register().execute(object : BaseObserver<RespRegister>(mView) {
            override fun onNext(result: RespRegister) {
                //结果处理
                super.onNext(result)
                mView.onRegisterResult(result.toString())
            }
        })

    }
}
