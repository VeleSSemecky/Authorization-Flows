package com.veles.authorizationflows.di.module

import com.veles.authorizationflows.di.scope.AuthorizedScope
import com.veles.authorizationflows.presentation.activity.MainContract
import com.veles.authorizationflows.presentation.activity.MainModel
import dagger.Binds
import dagger.Module

@Module(includes = [AuthorizedActivityModule::class])
abstract class AuthorizedModule {

    @AuthorizedScope
    @Binds
    abstract fun provideKModel(categoriesModel: MainModel): MainContract.Model

    @AuthorizedScope
    @Binds
    abstract fun provideMainModel(categoriesModel: MainModel): MainContract.Model
}