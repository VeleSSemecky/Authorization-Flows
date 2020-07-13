package com.veles.authorizationflows.presentation.authorization.login

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @Binds
    abstract fun provideView(fragment: LoginFragment):LoginContract.View

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun demoViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun provideFragModel(model: LoginModel): LoginContract.Model

}