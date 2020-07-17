package com.veles.authorizationflows.domain.weather

import com.veles.authorizationflows.data.network.service.weather.WeatherService
import com.veles.authorizationflows.model.api.WeatherNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(private val weatherService: WeatherService) :
    WeatherRepository {

    @ExperimentalCoroutinesApi
    override suspend fun apiWeather(lat: Double, lon: Double): Flow<Result<WeatherNetworkModel>> = flow{
        emit(Result.success(weatherService.apiWeather(lat, lon)))
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

}