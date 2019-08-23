package com.ericdecanini.sendclothing.injection.component

import com.ericdecanini.sendclothing.app.IAppInitialiser
import com.ericdecanini.sendclothing.injection.module.AppModule
import com.ericdecanini.sendclothing.injection.module.NetModule
import com.ericdecanini.sendclothing.mvp.activity.ProductsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    fun appInitialiser(): IAppInitialiser

    fun inject(activity: ProductsActivity)

}