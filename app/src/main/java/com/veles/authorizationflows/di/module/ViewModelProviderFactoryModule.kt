package com.veles.authorizationflows.di.module

import androidx.lifecycle.ViewModelProvider
import com.veles.authorizationflows.base.mvvm.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelProviderFactoryModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}