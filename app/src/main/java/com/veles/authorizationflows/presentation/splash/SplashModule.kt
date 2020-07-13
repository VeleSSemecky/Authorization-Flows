package com.veles.authorizationflows.presentation.splash

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import com.veles.authorizationflows.di.scope.FragmentScope
import com.veles.authorizationflows.presentation.authorization.login.LoginFragment
import com.veles.authorizationflows.presentation.authorization.login.LoginModule
import com.veles.authorizationflows.presentation.authorization.register.RegisterFragment
import com.veles.authorizationflows.presentation.authorization.register.RegisterModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {

    @SplashScope
    @Binds
    abstract fun provideView(activity: SplashActivity): SplashContract.View

    @SplashScope
    @Binds
    abstract fun provideActivity(activity: SplashActivity): BaseActivity<*, *>

    @SplashScope
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideViewModel(viewModel: SplashViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun provideLogin(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun provideRegister(): RegisterFragment

}