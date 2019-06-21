package com.zexinchen.myapplication.common

import com.zexinchen.myapplication.data.bean.BaseResp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.execute(subscriber: BaseObserver<T>) {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}
/*
    数据转换
 */
fun <T> Observable<BaseResp<T>>.convertObj(): Observable<T> {
    return this.flatMap(BaseFunc())
}