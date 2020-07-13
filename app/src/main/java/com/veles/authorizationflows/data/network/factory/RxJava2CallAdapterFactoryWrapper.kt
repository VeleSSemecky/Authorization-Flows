package com.veles.authorizationflows.data.network.factory

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxJava2CallAdapterFactoryWrapper private constructor(private val rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) :
    CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        val callAdapter =
            rxJava2CallAdapterFactory[returnType, annotations, retrofit]
        return callAdapter?.let { RxJava2CallAdapterWrapper(retrofit, it as CallAdapter<Any, Any>) }
    }

    companion object {
        fun create(_rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): RxJava2CallAdapterFactoryWrapper {
            return RxJava2CallAdapterFactoryWrapper(_rxJava2CallAdapterFactory)
        }
    }

}