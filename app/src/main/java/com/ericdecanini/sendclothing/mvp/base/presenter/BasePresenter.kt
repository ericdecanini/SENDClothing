package com.ericdecanini.sendclothing.mvp.base.presenter

import com.ericdecanini.sendclothing.app.AppInitialiser
import com.ericdecanini.sendclothing.app.SendClothingApp
import com.ericdecanini.sendclothing.injection.module.AppModule
import com.ericdecanini.sendclothing.mvp.base.ui.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T: BaseView>: Presenter<T> {

    private var mMvpView: T? = null

    private var mDisposable = CompositeDisposable()

    override fun attachView(mvpView: T) {
        this.mMvpView = mvpView
    }

    override fun detachView() {
        this.mMvpView = null
    }

    override fun addSubscription(subscription: Disposable) {
        this.mDisposable.add(subscription)
    }

    override fun clearDisposables() {
        this.mDisposable.clear()
    }

    fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): T? {
        return this.mMvpView
    }

    fun toastRandomFact() {
        val randomFacts = arrayOf(
            "Subway footlongs aren\'t a foot long",
            "Celcius and Farenheit are the same at -40 degrees",
            "McDonald\'s once made bubblegum-flavored broccoli",
            "The first oranges weren\'t orange",
            "A cow-bison hybrid is called a beefalo",
            "Scotland has 421 words for snow",
            "Cashew nuts come from a fruit")


        val message = randomFacts[(Math.random() * randomFacts.size).toInt()]
        getMvpView()?.makeToast(message)
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    companion object {
        class MvpViewNotAttachedException :
            RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
    }



}