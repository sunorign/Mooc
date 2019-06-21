package com.zexinchen.myapplication.common

import com.zexinchen.myapplication.ui.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseObserver<T> constructor(private var mView: BaseView) : Observer<T> {
    private lateinit var mDisposable: Disposable
    override fun onComplete() {
        unSubscribe()
    }

    override fun onSubscribe(d: Disposable) {
        mDisposable = d
    }

    override fun onNext(result: T) {
    }

    override fun onError(e: Throwable) {
        try {
            when (e) {
                is ConnectException -> mView.onError("连接异常，请检查网络")
                is SocketTimeoutException -> mView.onError("连接超时，请检查网络")
                is NullPointerException -> mView.onError("无数据，请核实")
            }
        } catch (e: IllegalStateException) {
            mView.onError("${e.message}")
        }
        unSubscribe()
    }

    private fun unSubscribe() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        mView.hideloading()
    }
}