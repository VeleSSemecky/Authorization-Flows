package com.veles.authorizationflows.presentation.main.user

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserModule {

    @Binds
    abstract fun provideView(fragment: UserFragment):UserContract.View

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun demoViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    abstract fun provideFragModel(model: UserModel): UserContract.Model

}