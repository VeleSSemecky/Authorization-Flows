package com.veles.authorizationflows.data.network.core

import com.veles.authorizationflows.data.local.data.DataStore
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject internal constructor(private val dataStore: DataStore) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (dataStore.accessToken.isNotEmpty()) {
            val request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer " + dataStore.accessToken)
                .build()
            chain.proceed(request)
        } else chain.proceed(chain.request())
    }

}