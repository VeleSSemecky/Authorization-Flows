package com.veles.authorizationflows.data.network

import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.veles.authorizationflows.BuildConfig
import com.veles.authorizationflows.common.Keys
import com.veles.authorizationflows.data.config.NetworkConfiguration
import com.veles.authorizationflows.data.network.core.AuthInterceptor
import com.veles.authorizationflows.data.network.core.NetworkConfig.sSLSocketFactory
import com.veles.authorizationflows.data.network.core.NetworkConfig.trustAllCerts
import com.veles.authorizationflows.data.network.factory.NullOnEmptyConverterFactory
import com.veles.authorizationflows.data.network.factory.NullableTypAdapterFactory
import com.veles.authorizationflows.data.network.factory.RxJava2CallAdapterFactoryWrapper.Companion.create
import com.veles.authorizationflows.data.network.service.banner.BannerListService
import com.veles.authorizationflows.data.network.service.message.NotificationMessageService
import com.veles.authorizationflows.di.qualifier.Mapper
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.X509TrustManager

@Module
object NetworkModule {



    @Mapper
    @Provides
    @Singleton
    fun provideMapperGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapterFactory(NullableTypAdapterFactory())
            .serializeNulls()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        defaultNetworkConfig: NetworkConfiguration,
        authInterceptor: AuthInterceptor?
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(
                Keys.NetworkConstants.READ_TIMEOUT.toLong(),
                TimeUnit.MINUTES
            )
            .writeTimeout(
                Keys.NetworkConstants.WRITE_TIMEOUT.toLong(),
                TimeUnit.MINUTES
            )
        for (interceptor in defaultNetworkConfig.interceptors) {
            okHttpBuilder.addInterceptor(interceptor)
        }
        okHttpBuilder.hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true })
        val interceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i(
                        "REST_LOGGER",
                        message
                    )
                }
            })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpBuilder.addInterceptor(authInterceptor!!)
        okHttpBuilder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Accept-Language", language)
                .build()
            val response = chain.proceed(request)
            response.cacheResponse
            response
        })
        okHttpBuilder.addInterceptor(interceptor)
        okHttpBuilder.sslSocketFactory(
            sSLSocketFactory!!
            , (trustAllCerts[0] as X509TrustManager)
        )
        val cache = Cache(
            defaultNetworkConfig.cacheDirectory,
            defaultNetworkConfig.cacheSize.toLong()
        )
        return okHttpBuilder.cache(cache)
            .build()
    }

    private val language: String
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault().toLanguageTags()
        } else {
            Locale.getDefault().language
        }


    @Singleton
    @Provides
    fun provideBaseRetrofit(
        context: Context?,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                create(
                    RxJava2CallAdapterFactory.create()
                )
            )
            .build()
    }

    //banner
    @Singleton
    @Provides
    fun provideBannerListService(_retrofit: Retrofit): BannerListService {
        return _retrofit.create(BannerListService::class.java)
    }

    @Singleton
    @Provides
    fun provideNotificationMessageService(_retrofit: Retrofit): NotificationMessageService {
        return _retrofit.create(NotificationMessageService::class.java)
    }
}