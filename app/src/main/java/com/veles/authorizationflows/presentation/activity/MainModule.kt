package com.veles.authorizationflows.presentation.activity

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import com.veles.authorizationflows.di.scope.FragmentScope
import com.veles.authorizationflows.presentation.login.LoginFragment
import com.veles.authorizationflows.presentation.login.LoginModule
import com.veles.authorizationflows.presentation.register.RegisterFragment
import com.veles.authorizationflows.presentation.register.RegisterModule
import com.veles.authorizationflows.presentation.change.ChangeFieldFragment
import com.veles.authorizationflows.presentation.change.ChangeFieldModule
import com.veles.authorizationflows.presentation.user.UserFragment
import com.veles.authorizationflows.presentation.user.UserModule
import com.veles.authorizationflows.presentation.weather.WeatherFragment
import com.veles.authorizationflows.presentation.weather.WeatherModule
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
    abstract fun provideActivity(activity: MainActivity): BaseActivity<*>

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun provideLogin(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun provideRegister(): RegisterFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun provideUser(): UserFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ChangeFieldModule::class])
    abstract fun provideChangeField(): ChangeFieldFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [WeatherModule::class])
    abstract fun provideWeather(): WeatherFragment

}