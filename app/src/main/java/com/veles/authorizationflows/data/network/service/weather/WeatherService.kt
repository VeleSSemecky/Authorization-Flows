package com.veles.authorizationflows.data.network.service.weather

import com.veles.authorizationflows.model.api.WeatherNetworkModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("onecall?exclude=minutely,hourly&appid=d229370e77fd7c19853c6e1404600a5a&units=metric")
    suspend fun apiWeather(@Query("lat") lat:Double, @Query("lon") lon:Double): WeatherNetworkModel
}