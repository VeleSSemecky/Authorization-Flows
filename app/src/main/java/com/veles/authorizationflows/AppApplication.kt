package com.veles.authorizationflows

import android.app.Application
import com.veles.authorizationflows.di.ComponentHandler
import com.veles.authorizationflows.di.InjectionHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class AppApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var componentHandler: ComponentHandler
    override fun onCreate() {
        super.onCreate()
        InjectionHelper.init(this)
        instance = this
        componentHandler.buildRequiredComponent()
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    companion object {
        lateinit var instance: AppApplication
            private set
    }

}