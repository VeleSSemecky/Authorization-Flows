package com.veles.authorizationflows.data.config

import com.veles.authorizationflows.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConfiguration @Inject internal constructor(val cacheDirectory: File) {

    val cacheSize = 10 * 1024 * 1024

    val interceptors: List<Interceptor>
        get() = listOf(authRequestInterceptor)

    //apply the below headers only for Applaud requests
    private val authRequestInterceptor: Interceptor
        get() = object :Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()

                //apply the below headers only for Applaud requests
                if (request.url.toString().startsWith(BuildConfig.BASE_URL)) {
                    request = request.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build()
                }
               return chain.proceed(request)
            }
        }

}