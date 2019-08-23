package com.ericdecanini.sendclothing.app

import android.app.Application
import android.content.Context
import com.ericdecanini.sendclothing.injection.component.AppComponent
import com.ericdecanini.sendclothing.injection.component.DaggerAppComponent
import com.ericdecanini.sendclothing.injection.module.AppModule
import com.ericdecanini.sendclothing.injection.module.NetModule

class SendClothingApp: Application() {

    var mAppComponent: AppComponent? = null

    companion object {
        fun get(context: Context): SendClothingApp = context.applicationContext as SendClothingApp
    }

    override fun onCreate() {
        super.onCreate()
        getComponent().appInitialiser().init(this)
    }

    fun getComponent(): AppComponent {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
        }
        return mAppComponent!!
    }

}