package com.veles.authorizationflows.data.network.factory

import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return (object :Converter<ResponseBody, Any?> {
            override fun convert(body: ResponseBody): Any? {
                if (body.contentLength() == 0L) {
                    return null
                }
                return try {
                    delegate.convert(body)
                } catch (e: JsonSyntaxException) {
                    null
                }
            }
        })
    }
}