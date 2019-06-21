package com.zexinchen.myapplication.service

import com.zexinchen.myapplication.data.bean.RespRegister
import io.reactivex.Observable
public interface UserService {
    fun register(): Observable<RespRegister>
}
