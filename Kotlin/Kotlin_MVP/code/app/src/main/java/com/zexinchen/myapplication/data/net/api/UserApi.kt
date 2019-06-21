package com.zexinchen.myapplication.data.net.api

import com.zexinchen.myapplication.data.bean.BaseResp
import com.zexinchen.myapplication.data.bean.RespRegister
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface UserApi {
    @FormUrlEncoded
    @POST("mobile/get")
    fun register(@FieldMap map:Map<String,String>): Observable<BaseResp<RespRegister>>
}