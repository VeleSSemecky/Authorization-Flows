package com.veles.authorizationflows.domain.weather

import com.veles.authorizationflows.model.api.WeatherNetworkModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun apiWeather(lat: Double, lon: Double): Flow<Result<WeatherNetworkModel>>
}