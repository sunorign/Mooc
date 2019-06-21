package com.zexinchen.myapplication.service.imp

import com.zexinchen.myapplication.common.convertObj
import com.zexinchen.myapplication.data.bean.RespRegister
import com.zexinchen.myapplication.data.net.RetrofitFactory
import com.zexinchen.myapplication.data.net.api.UserApi
import com.zexinchen.myapplication.service.UserService
import io.reactivex.Observable

class UserServiceImp : UserService {
    override fun register(): Observable<RespRegister> {

        return RetrofitFactory.instant.create(UserApi::class.java).register(
                mapOf("phone" to "17689413051",
                            "key" to "855582558e1de3e289fd3d87954360dd")).convertObj()
    }
}
