package com.veles.authorizationflows.di.module

import com.veles.authorizationflows.di.scope.AuthorizedScope
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationContract
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationModel
import com.veles.authorizationflows.presentation.main.activity.MainContract
import com.veles.authorizationflows.presentation.main.activity.MainModel
import dagger.Binds
import dagger.Module

@Module(includes = [AuthorizedActivityModule::class])
abstract class AuthorizedModule {

    @AuthorizedScope
    @Binds
    abstract fun provideKModel(categoriesModel: AuthorizationModel): AuthorizationContract.Model

    @AuthorizedScope
    @Binds
    abstract fun provideMainModel(categoriesModel: MainModel): MainContract.Model
}