package com.veles.authorizationflows.presentation.weather

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel
import com.veles.authorizationflows.model.api.WeatherNetworkModel
import kotlinx.coroutines.flow.Flow

interface WeatherContract {
    interface View : IView {
        fun onSuccess(location: Location)
        fun onFailure(e: Exception)
        fun apiWeatherObserver(): Observer<WeatherNetworkModel>
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract val data: LiveData<WeatherNetworkModel>
        abstract val isProgress: LiveData<Boolean>
        abstract fun apiWeather(lat: Double, lon: Double)
        abstract val error: LiveData<String>
        abstract fun getCityName(context: Context, location: Location): String
    }
    abstract class Model : BaseModel() {
        abstract suspend fun apiWeather(lat: Double, lon: Double): Flow<Result<WeatherNetworkModel>>
    }
}