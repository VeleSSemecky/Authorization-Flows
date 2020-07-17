package com.veles.authorizationflows.presentation.change

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChangeFieldModule {

    @Binds
    abstract fun provideView(fragment: ChangeFieldFragment):ChangeFieldContract.View

    @Binds
    @IntoMap
    @ViewModelKey(ChangeFieldViewModel::class)
    abstract fun demoViewModel(viewModel: ChangeFieldViewModel): ViewModel

    @Binds
    abstract fun provideFragModel(model: ChangeFieldModel): ChangeFieldContract.Model

}