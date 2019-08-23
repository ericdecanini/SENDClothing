package com.ericdecanini.sendclothing.mvp.base.presenter

import com.ericdecanini.sendclothing.mvp.base.ui.BaseView
import io.reactivex.disposables.Disposable

interface Presenter<V: BaseView> {

    fun attachView(mvpView: V)

    fun detachView()

    fun addSubscription(subscription: Disposable)

    fun clearDisposables()

}