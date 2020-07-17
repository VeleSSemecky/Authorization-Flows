package com.veles.authorizationflows.presentation.weather

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.model.api.WeatherNetworkModel
import com.veles.authorizationflows.room.dao.UserDAO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val model: WeatherModel,
    private val userDAO: UserDAO
): WeatherContract.ViewModel(model){

    override val isProgress:LiveData<Boolean> = MutableLiveData<Boolean>()
    override val error:LiveData<String> = MutableLiveData<String>()
    override val data:LiveData<WeatherNetworkModel> = MutableLiveData<WeatherNetworkModel>()

    override fun apiWeather(lat: Double, lon: Double) {
        (isProgress as MutableLiveData).postValue(true)
        viewModelScope.launch {
            model.apiWeather(lat, lon).collect{
                when{
                    it.isFailure -> (error as MutableLiveData).postValue(it.exceptionOrNull()?.message)
                    it.isSuccess -> (data as MutableLiveData).postValue(it.getOrNull())
                }
                isProgress.postValue(false)
            }
        }
    }

    override fun getCityName(context: Context, location: Location): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> =
            geocoder.getFromLocation(location.latitude, location.longitude, 1)
        return addresses[0].getAddressLine(0)
    }


}