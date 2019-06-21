package com.zexinchen.myapplication.common

import com.zexinchen.myapplication.data.bean.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function
import java.lang.Exception

class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        //裕福返回失败
        if (t.resultcode != "200") {
            return Observable.error(Exception("返回错误"))
        }
        //裕福返回成功
        return Observable.just(t.result)
    }
}
