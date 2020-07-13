package com.veles.authorizationflows.presentation.authorization.register

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RegisterModule {

    @Binds
    abstract fun provideView(fragment: RegisterFragment):RegisterContract.View

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun demoViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    abstract fun provideFragModel(model: RegisterModel): RegisterContract.Model

}