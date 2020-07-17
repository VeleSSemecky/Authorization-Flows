package com.veles.authorizationflows.presentation.weather

import com.google.firebase.auth.FirebaseAuth
import com.veles.authorizationflows.domain.weather.WeatherRepository
import com.veles.authorizationflows.model.api.WeatherNetworkModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : WeatherContract.Model() {

    override suspend fun apiWeather(lat: Double, lon: Double): Flow<Result<WeatherNetworkModel>> =
        weatherRepository.apiWeather(lat, lon)


}