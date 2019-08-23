package com.ericdecanini.sendclothing.app

import android.app.Application
import android.util.Log
import timber.log.Timber

class AppInitialiser: IAppInitialiser {

    override fun init(application: Application) {
        Timber.plant(Timber.DebugTree())
    }

}