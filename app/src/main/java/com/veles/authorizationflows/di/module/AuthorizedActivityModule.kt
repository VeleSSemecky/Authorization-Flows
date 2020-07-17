package com.veles.authorizationflows.di.module

import com.veles.authorizationflows.presentation.activity.MainActivity
import com.veles.authorizationflows.presentation.activity.MainModule
import com.veles.authorizationflows.presentation.activity.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthorizedActivityModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun authorizationActivity(): MainActivity


}