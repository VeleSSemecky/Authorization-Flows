package com.veles.authorizationflows.data.mapper

import com.google.gson.Gson
import com.veles.authorizationflows.di.qualifier.Mapper
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GsonDataMapper @Inject internal constructor(@param:Mapper private val gson: Gson) :
    DataMapper {
    override fun <T> fromString(_data: String?, _tClass: Class<T>?): T {
        return gson.fromJson(_data, _tClass)
    }

    override fun <T> fromString(_data: String?, _tClass: Type?): T {
        return gson.fromJson(_data, _tClass)
    }

    override fun <T> toString(_data: T): String? {
        return gson.toJson(_data)
    }

}