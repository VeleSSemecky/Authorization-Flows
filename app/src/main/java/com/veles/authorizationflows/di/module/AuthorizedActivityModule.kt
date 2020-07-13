package com.veles.authorizationflows.di.module

import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationActivity
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationModule
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationScope
import com.veles.authorizationflows.presentation.main.activity.MainActivity
import com.veles.authorizationflows.presentation.main.activity.MainModule
import com.veles.authorizationflows.presentation.main.activity.MainScope
import com.veles.authorizationflows.presentation.splash.SplashActivity
import com.veles.authorizationflows.presentation.splash.SplashModule
import com.veles.authorizationflows.presentation.splash.SplashScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthorizedActivityModule {

    @AuthorizationScope
    @ContributesAndroidInjector(modules = [AuthorizationModule::class])
    abstract fun authorizationActivity(): AuthorizationActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @SplashScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

}