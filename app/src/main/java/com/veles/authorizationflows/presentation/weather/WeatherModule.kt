package com.veles.authorizationflows.presentation.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.veles.authorizationflows.base.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class WeatherModule {

    @Binds
    abstract fun provideView(fragment: WeatherFragment):WeatherContract.View

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun demoViewModel(viewModel: WeatherViewModel): ViewModel

    @Binds
    abstract fun provideFragModel(model: WeatherModel): WeatherContract.Model

    companion object{
        @Provides
        fun provideLocation(context: Context): FusedLocationProviderClient {
            return LocationServices.getFusedLocationProviderClient(context)
        }
    }

}