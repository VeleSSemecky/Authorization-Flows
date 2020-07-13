package com.veles.authorizationflows.presentation.main.activity

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import com.veles.authorizationflows.di.scope.FragmentScope
import com.veles.authorizationflows.presentation.main.user.UserFragment
import com.veles.authorizationflows.presentation.main.user.UserModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @MainScope
    @Binds
    abstract fun provideView(activity: MainActivity): MainContract.View

    @MainScope
    @Binds
    abstract fun provideActivity(activity: MainActivity): BaseActivity<*, *>

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun provideUserFragment(): UserFragment

}