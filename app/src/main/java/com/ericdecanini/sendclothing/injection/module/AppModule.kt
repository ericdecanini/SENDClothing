package com.ericdecanini.sendclothing.injection.module

import android.app.Application
import android.content.Context
import com.ericdecanini.sendclothing.app.AppInitialiser
import com.ericdecanini.sendclothing.app.IAppInitialiser
import com.ericdecanini.sendclothing.injection.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: Application) {

    @Provides
    fun provideApplication(): Application = mApplication

    @Provides
    @ApplicationContext
    fun provideContext(): Context = mApplication

    @Provides
    @Singleton
    fun provideAppInitialiser(): IAppInitialiser = AppInitialiser()

}